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
    public static void printSubSeq(String str){
        int n=str.length();
        for(int i=0;i<(1<<n);i++){
           Pattern(str, i);
              System.out.println();
        }
    }
    public static void Pattern(String str, int i){
       int pos=0;
       while(i>0){
           if((i&1)==1)System.out.print(str.charAt(pos));
           i>>=1;
           pos++;
    }
    public static void PreparingOlympiad(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        int x = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < (1 << n); i++) {
            if(CountBits(i)>=2 && isItPossible(a, l, r, x, i)){
                ans++;
            }
        }
        System.out.println(ans);
        sc.close();
    }
    public static boolean isItPossible(int a[],int l,int r,int x,int i){
        int sum=0;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int pos=0;
        while (i>0) {
            if((i & 1 )!=0){
                sum+=a[pos];
                min=Math.min(min,a[pos]);
                max=Math.max(max,a[pos]);
            }
            i>>=1;
            pos++;
        }
        return sum>=l && sum<=r && max-min>=x;
    }
    public static int CountBits(int n){
        int count=0;
        while(n!=0){
            count++;
            n&=(n-1);
        }
        return count;
    }

    public static void EhabAndXorcist(){
        Scanner sc = new Scanner(System.in);
        
            int u=sc.nextInt();
            int v=sc.nextInt();
            if (u > v || (u % 2 != v % 2)) {
                System.out.println(-1);
            }
            else if (u == v) {
                if (u == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                    System.out.println(u);
                }
            }
            else{
                int x = (v - u) / 2;
                if ((u & x) == 0) {
                    System.out.println(2);
                    System.out.println((u ^ x) + " " + x);
                } else {
                    System.out.println(3);
                    System.out.println(u + " " + x + " " + x);
                }
            }
       
    }
    public static void main(String[] args) {
        PreparingOlympiad();
        //  printSubSeq("abcd");
       


        // Egg Drop
        // int n=9,e=3;
        // int[][] dp = new int[n+1][e+1];
        // for (int[] row : dp) Arrays.fill(row, -1);
        // System.out.println(EggDrop(n, e, dp));


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
