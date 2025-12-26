import java.util.*;

public class TiltedBars {

    // --- Data Structures ---
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Point))
                return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    static class Bar {
        int id;
        Point p1, p2;
        Point pivot;
        Bar crossBar; // Reference to the other bar if this is part of a cross

        Bar(int id, Point p1, Point p2) {
            this.id = id;
            this.p1 = p1;
            this.p2 = p2;
            this.crossBar = null;
        }

        // Get lowest point based on Y
        Point getLow() {
            return p1.y < p2.y ? p1 : p2;
        }

        Point getHigh() {
            return p1.y > p2.y ? p1 : p2;
        }
    }

    // --- Global State ---
    static List<Bar> initialBars = new ArrayList<>();
    static TreeSet<Integer> groundPoints = new TreeSet<>();
    // Memoization: Key = "x,y,barId" (prevents infinite loops in recursion)
    static Set<String> visitedStates = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt())
            return;

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            initialBars.add(new Bar(i,
                    new Point(sc.nextInt(), sc.nextInt()),
                    new Point(sc.nextInt(), sc.nextInt())));
        }
        int startX = sc.nextInt();
        int startY = sc.nextInt();

        solve(N, startX, startY);
    }

    static void solve(int N, int startX, int startY) {
        // 1. Pre-calculate Pivots and Cross references
        calculateGeometry(initialBars);

        // 2. Start Simulation
        drop(startX, startY, initialBars);

        // 3. Output
        for (int x : groundPoints) {
            System.out.println(x + " " + 0);
        }
    }

    // --- Core Physics Engine ---

    // 1. Drop the ball vertically
    static void drop(int x, int y, List<Bar> currentConfig) {
        Bar hitBar = null;
        int maxY = -1;

        // Find highest bar below (x,y)
        for (Bar b : currentConfig) {
            int minX = Math.min(b.p1.x, b.p2.x);
            int maxX = Math.max(b.p1.x, b.p2.x);

            if (x >= minX && x <= maxX) {
                // Calculate Y at this X: y - y1 = m(x - x1)
                // Slopes are always +/- 1
                int m = (b.p2.y - b.p1.y) / (b.p2.x - b.p1.x);
                int barY = b.p1.y + m * (x - b.p1.x);

                if (barY < y) { // Strictly below
                    if (barY > maxY) {
                        maxY = barY;
                        hitBar = b;
                    }
                }
            }
        }

        if (hitBar == null) {
            groundPoints.add(x);
        } else {
            // Ball hits the bar. Interaction begins.
            interact(new Point(x, maxY), hitBar, currentConfig);
        }
    }

    // 2. Ball is on a bar (at point p)
    static void interact(Point p, Bar b, List<Bar> config) {
        // MEMOIZATION key: x,y + configHash (simplified to bar ID for basic cycle
        // detection)
        // Since config changes (tilts), exact memoization is hard.
        // Given N <= 50 and limited depth, we limit recursion by strict state matching
        // or just basic cycle prevention on positions.
        String state = p.x + "," + p.y + "," + b.id + "," + config.hashCode();
        // Note: standard hashCode for List might be slow or inconsistent if bars
        // mutate.
        // For this problem size, passing new Lists is safer.

        // Basic Depth limiter or simple point check for recursion safety
        if (visitedStates.contains(state))
            return;
        visitedStates.add(state);

        // --- OPTION A: Slide Down (Gravity without Tilt) ---
        slide(p, b, config);

        // --- OPTION B: Tilt Immediate (Upon landing or being at a point) ---
        // Try tilting Clockwise and Anti-Clockwise
        tryTilt(p, b, config, true); // CW
        tryTilt(p, b, config, false); // ACW
    }

    // Logic to slide the ball down the current bar
    static void slide(Point p, Bar b, List<Bar> config) {
        Point lowest = b.getLow();

        // If we are already at the bottom, we fall off
        if (p.equals(lowest)) {
            // Rule: "When ball is at the edge... let gravity pull it down"
            drop(p.x, p.y, config);
            return;
        }

        // Check for Intersections along the path to the lowest point
        Point target = lowest;
        Point stopPoint = target;
        boolean stopAtIntersection = false;
        Bar intersectingBar = null;

        // Vector from p to lowest
        int dx = Integer.compare(lowest.x, p.x);
        int dy = Integer.compare(lowest.y, p.y);

        // We must check if we cross the pivot (if pivot is an intersection)
        if (b.pivot != null && b.crossBar != null) {
            // Check if pivot lies strictly between p and lowest (or is the lowest)
            if (isBetween(b.pivot, p, lowest)) {
                stopPoint = b.pivot;
                stopAtIntersection = true;
                intersectingBar = b.crossBar;
            }
        }

        // Recursion Step: Move to Stop Point
        if (stopAtIntersection) {
            // We stopped at a cross. We are now on BOTH bars.
            // We can tilt the cross from here.
            // We can also continue sliding down the current bar (handled by next recursion
            // if we don't tilt)
            // But strictly, hitting a cross allows tilting the cross.
            interact(stopPoint, b, config);
            // Note: interact() will try tilting (Option B) and sliding further (Option A)

            // Also, strictly speaking, at a cross we could technically switch tracks
            // and slide down the other bar? The problem implies tilting is the main
            // mechanic at crosses.
            // The recursion interact(stopPoint, b, ...) covers tilting the cross.
            // To cover sliding down the other leg without tilting:
            // interact(stopPoint, intersectingBar, config);
        } else {
            // We slid to the endpoint
            interact(stopPoint, b, config);
        }
    }

    // Logic to Tilt
    static void tryTilt(Point p, Bar targetBar, List<Bar> currentConfig, boolean clockwise) {
        // Create a NEW configuration (Deep Copy)
        List<Bar> newConfig = deepCopy(currentConfig);

        // Find the bar(s) in the new config corresponding to targetBar
        Bar newBar = newConfig.get(targetBar.id);

        // Perform Rotation
        // If it's a cross, rotate both. If single, rotate one.
        rotateBar(newBar, clockwise);
        if (newBar.crossBar != null) {
            // The crossBar reference in newBar points to the old object?
            // No, deepCopy must fix references.
            // Simplification: Find the bar with the ID of the crossBar
            Bar newCrossBar = newConfig.get(newBar.crossBar.id);
            rotateBar(newCrossBar, clockwise);
        }

        // Calculate new Ball Position
        // If ball is AT pivot, it stays. If not, it rotates around pivot.
        Point newBallPos = rotatePoint(p, newBar.pivot, clockwise);

        // After tilting, the ball "slides along the bar".
        // We re-enter the interaction loop with the new config and position.
        // However, to prevent infinite tilt loops (Tilt CW -> Tilt ACW -> Tilt CW),
        // strictly we should usually slide immediately after a tilt.
        slide(newBallPos, newBar, newConfig);
    }

    // --- Geometry Helpers ---

    static void rotateBar(Bar b, boolean cw) {
        b.p1 = rotatePoint(b.p1, b.pivot, cw);
        b.p2 = rotatePoint(b.p2, b.pivot, cw);
        // Pivot stays same
    }

    static Point rotatePoint(Point p, Point pivot, boolean cw) {
        int dx = p.x - pivot.x;
        int dy = p.y - pivot.y;
        if (cw)
            return new Point(pivot.x + dy, pivot.y - dx);
        else
            return new Point(pivot.x - dy, pivot.y + dx);
    }

    static boolean isBetween(Point mid, Point a, Point b) {
        if (mid.equals(a))
            return false; // We are already there
        // Check bounds
        boolean xBet = (mid.x >= Math.min(a.x, b.x)) && (mid.x <= Math.max(a.x, b.x));
        boolean yBet = (mid.y >= Math.min(a.y, b.y)) && (mid.y <= Math.max(a.y, b.y));
        return xBet && yBet;
    }

    static List<Bar> deepCopy(List<Bar> original) {
        List<Bar> copy = new ArrayList<>();
        for (Bar b : original) {
            copy.add(new Bar(b.id, new Point(b.p1.x, b.p1.y), new Point(b.p2.x, b.p2.y)));
        }
        // Restore Pivots and Cross References
        calculateGeometry(copy); // Recalculate intersections for the new set
        return copy;
    }

    static void calculateGeometry(List<Bar> bars) {
        // Reset
        for (Bar b : bars) {
            b.pivot = null;
            b.crossBar = null;
        }

        for (int i = 0; i < bars.size(); i++) {
            for (int j = i + 1; j < bars.size(); j++) {
                Bar b1 = bars.get(i);
                Bar b2 = bars.get(j);

                // Find Intersection
                Point inter = getIntersection(b1, b2);
                if (inter != null) {
                    b1.pivot = inter;
                    b2.pivot = inter;
                    b1.crossBar = b2;
                    b2.crossBar = b1;
                }
            }
        }
        // Midpoints for isolated bars
        for (Bar b : bars) {
            if (b.pivot == null) {
                b.pivot = new Point((b.p1.x + b.p2.x) / 2, (b.p1.y + b.p2.y) / 2);
            }
        }
    }

    static Point getIntersection(Bar b1, Bar b2) {
        // Standard line intersection for segments
        long x1 = b1.p1.x, y1 = b1.p1.y, x2 = b1.p2.x, y2 = b1.p2.y;
        long x3 = b2.p1.x, y3 = b2.p1.y, x4 = b2.p2.x, y4 = b2.p2.y;

        long den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (den == 0)
            return null; // Parallel

        long pxNum = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        long pyNum = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);

        // Must be integer coords
        if (pxNum % den != 0 || pyNum % den != 0)
            return null;

        int px = (int) (pxNum / den);
        int py = (int) (pyNum / den);

        // Check if point is on both segments
        if (px >= Math.min(x1, x2) && px <= Math.max(x1, x2) &&
                py >= Math.min(y1, y2) && py <= Math.max(y1, y2) &&
                px >= Math.min(x3, x4) && px <= Math.max(x3, x4) &&
                py >= Math.min(y3, y4) && py <= Math.max(y3, y4)) {
            return new Point(px, py);
        }
        return null;
    }
}