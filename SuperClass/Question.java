

public class Question {
    public static void main(String[] args) {
        int wine[]={2,3,5,1,4};
        System.out.println(wineProb(wine, 0, wine.length-1, 1));
        int n=wine.length;
        int y=n;
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++)dp[i][i]=wine[i]*y;
        y--;
        for(int gap=1;gap<n;gap++){
            for(int j=gap;j<n;j++){
                int i=j-gap;
                int f=wine[i]*y+dp[i+1][j];
                int l=wine[j]*y+dp[i][j-1];
                dp[i][j]=Math.max(f, l);
            }
            y--;

        }
        System.out.println(dp[0][n-1]);

    }
    public static int wineProb(int []wine,int i,int j,int y){
        if(i>j)return 0;
        int f=wine[i]*y+wineProb(wine, i+1, j, y+1);
        int l=wine[j]*y+wineProb(wine, i, j-1, y+1);
        return Math.max(f, l);
    }
}
