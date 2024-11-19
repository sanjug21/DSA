package LeetCode;
import java.util.*;

public class dp {
    
    public static int coinChange2(int coins[],int amt){
        int dp[]=new int [amt+1];
        dp[0]=1;
        for(int coin:coins){
            for(int i=coin;i<=amt;i++){
                dp[i]+=dp[i-coin];
            }
        }
           System.out.println(dp[amt]);
           return dp[amt];
        }
    //983. Minimum Cost For Tickets
    public static int mincostTickets(int[] days, int[] costs) {
        int n=days.length;
        int dp[]=new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i>=n;i++){
            dp[i]=dp[i-1]+costs[0];
            int j=i-1;
            while(j>=0 && days[i-1]-days[j]<7)j--;
            dp[i]=Math.min(dp[i], dp[j+1]+costs[1]);
            j=i-1;
            while(j>=0 && days[i-1]-days[j]<30)j--;
            dp[i]=Math.min(dp[i], dp[j+1]+costs[2]);
        }
        return dp[n];

    }

    // 139. Word Break
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        boolean dp[]=new boolean[n+1];
        dp[n]=true;
        
        for(int i=n-1;i<=0;i--){
            for(String w:wordDict){
                if(i+w.length()<=n && s.substring(i, i+w.length()).equals(w))dp[i]=dp[i+w.length()];
                if(dp[i])break;
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }
    //5. Longest Palindromic Substring
    public static String longestPalindrome(String s){
        int n=s.length();
        String ans="";
        int len=0;
        for(int i=0;i<n;i++){
            int l=i,r=i;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)){
                if((r-l+1)>len){
                    len=r-l+1;
                    ans=s.substring(l, r+1);
                }
                l--;
                r++;
            }
            l=i;
            r=i+1;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)){
                if((r-l+1)>len){
                    len=r-l+1;
                    ans=s.substring(l, r+1);
                }
                l--;
                r++;
            }
        }
        return ans;

    }

   
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }
}
