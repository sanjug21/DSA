import java.util.*;

public class alternating {
    public static int solve(String s, int[] worth) {
        int n = s.length();
        int[][] dp = new int[n][2];
        dp[0][0] = s.charAt(0) == '0' ? worth[0] : 0;
        dp[0][1] = s.charAt(0) == '1' ? worth[0] : 0;
        int ans = 0;
        for (int w : worth) {
            ans += w;
        }
        for (int i=1;i<n;i++) {
            dp[i][0]=dp[i-1][0];
            dp[i][1]=dp[i-1][1];
            if (s.charAt(i) == '0') {
                dp[i][0]=Math.max(dp[i][0],
                    (dp[i-1][1] == 0 ? 0 : dp[i-1][1])+worth[i]);
            } else {
                dp[i][1] = Math.max(dp[i][1],
                    (dp[i-1][0] == 0 ? 0 : dp[i-1][0])+worth[i]);
            }
        }
        int max=Math.max(dp[n-1][0], dp[n-1][1]);
        return ans - max;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s= sc.nextLine().trim();
        String[] worthStrings = sc.nextLine().trim().split(" ");
        int[] worth = new int[worthStrings.length];
        for (int i=0;i<worthStrings.length;i++) {
            worth[i] = Integer.parseInt(worthStrings[i]);
        }
        System.out.print(solve(s, worth));

    }
}