import java.util.*;

public class Question {
    public static void main(String[] args) {
        // wine problem
        // int wine[]={2,3,5,1,4};
        // int n=wine.length;
        // int dp[][] = new int[n][n];
        // for (int[] row : dp) Arrays.fill(row, -1);

        // System.out.println(wineProb(wine, 0, n - 1, 1, dp));



        // OptimalGameStartegy
        // int a[]={1,2,3,4};
        // int n=a.length;
        // int[][] dp = new int[n][n];
        // for (int[] row : dp) Arrays.fill(row, -1);
        
        // System.out.println(OptimalGameStrategy(a, 0, n - 1, dp));

        // mcm
        int mat[]={4,2,3,5,1};
        int n=mat.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println(MCM(mat, 0, mat.length-1,dp));
    }

    public static int MCM(int mat[],int i,int j,int dp[][]){
        if(i+1==j)return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int ans=Integer.MAX_VALUE;
        for(int k=i+1;k<j;k++){
            int val=MCM(mat, i, k,dp) + MCM(mat, k, j,dp) + (mat[i]*mat[j]*mat[k]);
            ans=Math.min(ans, val);
        }
        
        return dp[i][j]=ans;

    }
    

    public static int OptimalGameStrategy(int a[], int i, int j, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        if (i + 1 == j) return dp[i][j] = Math.max(a[i], a[j]);
        
        int f = a[i] + Math.min(OptimalGameStrategy(a, i + 2, j, dp), OptimalGameStrategy(a, i + 1, j - 1, dp));
        int l = a[j] + Math.min(OptimalGameStrategy(a, i + 1, j - 1, dp), OptimalGameStrategy(a, i, j - 2, dp));
        
        return dp[i][j] = Math.max(f, l);
    }


    public static int wineProb(int wine[], int i, int j, int y, int dp[][]) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int f = wine[i] * y + wineProb(wine, i + 1, j, y + 1, dp);
        int l = wine[j] * y + wineProb(wine, i, j - 1, y + 1, dp);

        return dp[i][j] = Math.max(f, l);
    }
}
