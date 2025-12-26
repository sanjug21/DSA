import java.util.Scanner;

public class PalindromeCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        countGoodString(s);
        sc.close();
    }

    public static void countGoodString(String s) {
        long[][] dp = new long[2][2];
        long odd = 0;
        long even = 0;
        for (int i = 1; i <= s.length(); i++) {
            odd++;
            if (i % 2 != 0) {
                char c = s.charAt(i - 1);
                odd += dp[c - 'a'][0]; // odd-odd+1=odd
                dp[c - 'a'][0]++;
                even += dp[c - 'a'][1]; // odd-even+1=even
            } else {
                char c = s.charAt(i - 1);
                odd += dp[c - 'a'][1]; // even-even+1=odd
                dp[c - 'a'][1]++;
                even += dp[c - 'a'][0]; // odd-even+1=even
            }
        }
        System.out.println(even + " " + odd);
    }
}
   

//      odd   even
//a     0     0
//b     0     0