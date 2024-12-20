import java.util.*;

public class Question {
    
    public static int wineProb(int wine[], int i, int j, int y, int dp[][]) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        int f = wine[i] * y + wineProb(wine, i + 1, j, y + 1, dp);
        int l = wine[j] * y + wineProb(wine, i, j - 1, y + 1, dp);

        return dp[i][j] = Math.max(f, l);
    }


    public static int OptimalGameStrategy(int a[], int i, int j, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        if (i + 1 == j) return dp[i][j] = Math.max(a[i], a[j]);
        
        int f = a[i] + Math.min(OptimalGameStrategy(a, i + 2, j, dp), OptimalGameStrategy(a, i + 1, j - 1, dp));
        int l = a[j] + Math.min(OptimalGameStrategy(a, i + 1, j - 1, dp), OptimalGameStrategy(a, i, j - 2, dp));
        
        return dp[i][j] = Math.max(f, l);
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


    public static int mixture(int mix[],int i,int j){
        if(i==j)return 0;
        int ans=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int val=mixture(mix, i, k)+mixture(mix, k+1, j)+colors(mix, i, k)*colors(mix, k+1, j);
            ans=Math.min(ans, val);
        }
        return ans;
    }
    public static int colors(int mix[],int i,int j){
        int sum=0;
        for(int k=i;k<=j;k++)sum+=mix[k];
        return sum;        
    }


    public static int EggDrop(int n,int e,int dp[][]){
        if(n==0|| n==1||e==1)return n;
        if(dp[n][e]!=-1)return dp[n][e];
        int ans=Integer.MAX_VALUE;
        for(int f=1;f<=n;f++){
            int b=EggDrop(f-1, e-1, dp);
            int ub=EggDrop(n-f, e, dp);
            int t=Math.max(b,ub)+1;
            ans=Math.min(ans, t);
        }
        return dp[n][e]=ans;
    }

    public int EggDrop2(int n, int e, int dp[][]) {
        if (n == 0 || n == 1 || e == 1) return n;
        if (dp[n][e] != -1) return dp[n][e];

        int ans = Integer.MAX_VALUE;
        int low = 1, high = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            int breakCount = EggDrop(mid - 1, e - 1, dp);
            int notBreakCount = EggDrop(n - mid, e, dp);

            int worstCase = Math.max(breakCount, notBreakCount) + 1;
            ans = Math.min(ans, worstCase);

            if (breakCount > notBreakCount) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return dp[n][e] = ans;
    }
    
    public static void main(String[] args) {

        // Egg Drop
        int n=9,e=3;
        int[][] dp = new int[n+1][e+1];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println(EggDrop(n, e, dp));


        // mixture problem
        // int mix[]={40,60,20,10};
        // int n=mix.length;
        // System.out.println(mixture(mix, 0, n-1));


        // mcm
        // int mat[]={4,2,3,5,1};
        // int n=mat.length;
        // int[][] dp = new int[n][n];
        // for (int[] row : dp) Arrays.fill(row, -1);
        // System.out.println(MCM(mat, 0, mat.length-1,dp));


        // OptimalGameStartegy
        // int a[]={1,2,3,4};
        // int n=a.length;
        // int[][] dp = new int[n][n];
        // for (int[] row : dp) Arrays.fill(row, -1);
        // System.out.println(OptimalGameStrategy(a, 0, n - 1, dp));


        // wine problem
        // int wine[]={2,3,5,1,4};
        // int n=wine.length;
        // int dp[][] = new int[n][n];
        // for (int[] row : dp) Arrays.fill(row, -1);

        // System.out.println(wineProb(wine, 0, n - 1, 1, dp));
        
    }
}
