import java.util.*;

public class IshaAndNisha {
    static int N, M;
    static String[][] grid;
    static Map<Integer, List<int[]>> clues = new HashMap<>();
    static String targetWord;
    static int minFaultyClues = Integer.MAX_VALUE;
    static boolean pathFound = false;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Set<String> visited = new HashSet<>();

    static int countClueViolations(int time, int r, int c) {
        int violations = 0;
        if (clues.containsKey(time)) {
            for (int[] rect : clues.get(time)) {
                int r1 = rect[0], c1 = rect[1], r2 = rect[2], c2 = rect[3];
                if (r1 <= r && r <= r2 && c1 <= c && c <= c2) {
                    violations++;
                }
            }
        }
        return violations;
    }

    static void dfs(int r, int c, int idx, int currentFaults) {
        if (currentFaults >= minFaultyClues) return;

        int currentTime = idx + 1;
        int stepViolations = countClueViolations(currentTime, r, c);
        int totalFaultsNow = currentFaults + stepViolations;

        if (totalFaultsNow >= minFaultyClues) return;

        if (idx == targetWord.length() - 1) {
            pathFound = true;
            minFaultyClues = Math.min(minFaultyClues, totalFaultsNow);
            return;
        }

        visited.add(r + "," + c);
        char nextChar = targetWord.charAt(idx + 1);

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (!visited.contains(nr + "," + nc) && grid[nr][nc].charAt(0) == nextChar) {
                    dfs(nr, nc, idx + 1, totalFaultsNow);
                }
            }
        }

        visited.remove(r + "," + c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;

        N = sc.nextInt();
        M = sc.nextInt();
        grid = new String[N][M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                grid[r][c] = sc.next();
            }
        }

        int I = sc.nextInt();
        for (int i = 0; i < I; i++) {
            int t = sc.nextInt();
            int r1 = sc.nextInt() - 1;
            int c1 = sc.nextInt() - 1;
            int r2 = sc.nextInt() - 1;
            int c2 = sc.nextInt() - 1;
            clues.putIfAbsent(t, new ArrayList<>());
            clues.get(t).add(new int[]{r1, c1, r2, c2});
        }

        targetWord = sc.next();

        char startChar = targetWord.charAt(0);
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (grid[r][c].charAt(0) == startChar) {
                    dfs(r, c, 0, 0);
                }
            }
        }

        if (!pathFound) {
            System.out.print("Impossible");
        } else if (minFaultyClues == 0) {
            System.out.print("All clues are correct");
        } else {
            System.out.print(minFaultyClues);
        }
    }
}
