import java.util.Arrays;
import java.util.Scanner;

public class LPS {
    // E. Two Arrays and Sum of Functions
    public static long sumOfFunctions() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mod = 998244353;
        long[] a = new long[n];
        long[] b = new long[n];

        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            a[i] = x * (i + 1) * (n - i);
        }

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextLong();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        long result = 0;
        for (int i = 0; i < n; i++) {
            result = (result + a[i] % mod * b[n - i - 1] % mod) % mod;
        }
        sc.close();
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            // String len = sc.nextLine();
            String pat = sc.nextLine();
            String text = sc.nextLine();

            lps(pat, text);
        }
        sc.close();
    }

    public static void lps(String pat, String text) {
        String s = pat + "#" + text;
        int n = s.length();
        int[] dp = new int[n];
        int i = 1, len = 0;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                dp[i] = ++len;
                i++;
            } else {
                if (len > 0) {
                    len = dp[len - 1];
                } else {
                    dp[i] = 0;
                    i++;
                }
            }
        }

        for (int j = pat.length() + 1; j < dp.length; j++) {
            if (dp[j] == pat.length()) {
                System.out.println((j - 2 * pat.length()));
            }
        }
    }
}
