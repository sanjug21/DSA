import java.util.*;
import java.awt.geom.*;

public class OrigamiFold {

    private static final double EPS = 1e-9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextDouble())
            return;

        double n=sc.nextDouble();
        double side=Math.sqrt(n);

        double fx1=sc.nextDouble();
        double fy1=sc.nextDouble();
        double fx2=sc.nextDouble();
        double fy2=sc.nextDouble();

        List<Point> square =new ArrayList<>();
        square.add(new Point(0, 0));
        square.add(new Point(side, 0));
        square.add(new Point(side, side));
        square.add(new Point(0, side));

        List<Point> left=clip(square, fx1, fy1, fx2, fy2, true);
        List<Point> right=clip(square, fx1, fy1, fx2, fy2, false); 

        List<Point> reflectedPoly=new ArrayList<>();
        for (Point p:left) {
            reflectedPoly.add(reflect(p, fx1, fy1, fx2, fy2));
        }

        Area staticArea=createAreaFromPoints(right);
        Area movedArea=createAreaFromPoints(reflectedPoly);

        staticArea.add(movedArea); 

        List<Point> resultPoints=getPointsFromArea(staticArea);

        Collections.sort(resultPoints);

        for (Point p : resultPoints) {
            double x=Math.abs(p.x) < 1e-4 ? 0.0 : p.x;
            double y=Math.abs(p.y) < 1e-4 ? 0.0 : p.y;
            System.out.printf(Locale.US, "%.2f %.2f%n", x, y);
        }
    }

    static class Point implements Comparable<Point> {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (Math.abs(this.x - o.x) > 1e-4) {
                return Double.compare(this.x, o.x);
            }
            return Double.compare(this.y, o.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Point))
                return false;
            Point p = (Point) o;
            return Math.abs(p.x - x) < 1e-4 && Math.abs(p.y - y) < 1e-4;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.round(x * 100), Math.round(y * 100));
        }
    }


    private static List<Point> clip(List<Point> subjectPoly, double x1, double y1, double x2, double y2,
            boolean keepLeft) {
        List<Point> newPoly=new ArrayList<>();

        for (int i=0;i<subjectPoly.size();i++) {
            Point current=subjectPoly.get(i);
            Point prev=subjectPoly.get((i - 1 + subjectPoly.size()) % subjectPoly.size());

            boolean currIn=isInside(current, x1, y1, x2, y2, keepLeft);
            boolean prevIn=isInside(prev, x1, y1, x2, y2, keepLeft);

            if (currIn) {
                if (!prevIn) {
                    newPoly.add(intersection(prev, current, x1, y1, x2, y2));
                }
                newPoly.add(current);
            } else if (prevIn) {
                newPoly.add(intersection(prev, current, x1, y1, x2, y2));
            }
        }
        return newPoly;
    }

    private static boolean isInside(Point p, double ax, double ay, double bx, double by, boolean keepLeft) {
        
        double cp=(bx - ax) * (p.y - ay) - (by - ay) * (p.x - ax);
        if (Math.abs(cp) < EPS)
            return true;
        return keepLeft ? cp > 0 : cp < 0;
    }

    private static Point intersection(Point p1, Point p2, double ax, double ay, double bx, double by) {
        
        double A1=p2.y-p1.y;
        double B1=p1.x-p2.x;
        double C1=A1*p1.x + B1*p1.y;

        double A2=by-ay;
        double B2=ax-bx;
        double C2=A2*ax + B2*ay;

        double det=A1*B2 - A2*B1;
        if (Math.abs(det) < EPS)
            return null; 

        double x=(B2*C1 - B1*C2)/det;
        double y=(A1*C2 - A2*C1)/det;
        return new Point(x, y);
    }

    private static Point reflect(Point p, double x1, double y1, double x2, double y2) {
        double dx=x2-x1;
        double dy=y2-y1;

        double a=(dx*dx - dy*dy) / (dx*dx + dy*dy);
        double b=2*dx*dy / (dx*dx + dy*dy);

        double x2_=a*(p.x-x1)+b*(p.y-y1)+x1;
        double y2_=b*(p.x-x1) - a*(p.y-y1) + y1;

        return new Point(x2_, y2_);
    }

    private static Area createAreaFromPoints(List<Point> points) {
        if (points.isEmpty())
            return new Area();
        Path2D.Double path = new Path2D.Double();
        path.moveTo(points.get(0).x, points.get(0).y);
        for (int i = 1; i < points.size(); i++) {
            path.lineTo(points.get(i).x, points.get(i).y);
        }
        path.closePath();
        return new Area(path);
    }

    private static List<Point> getPointsFromArea(Area area) {
        List<Point> points = new ArrayList<>();
        PathIterator pi = area.getPathIterator(null);
        double[] coords = new double[6];

        Point first = null;

        while (!pi.isDone()) {
            int type = pi.currentSegment(coords);
            double x = coords[0];
            double y = coords[1];

            if (type == PathIterator.SEG_MOVETO) {
                first = new Point(x, y);
                points.add(first);
            } else if (type == PathIterator.SEG_LINETO) {
                points.add(new Point(x, y));
            } else if (type == PathIterator.SEG_CLOSE) {
            }
            pi.next();
        }

        
        List<Point> unique = new ArrayList<>();
        if (!points.isEmpty())
            unique.add(points.get(0));

        for (int i = 1; i < points.size(); i++) {
            Point curr = points.get(i);
            boolean exists = false;
            for (Point u : unique) {
                if (curr.equals(u)) {
                    exists = true;
                    break;
                }
            }
            if (!exists)
                unique.add(curr);
        }

        return unique;
    }
}