
import java.util.*;
public class PowerfulPartition{
    public static int mod=100000007;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int a[]=new int[n];
        // long total=0;
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
            // total+=a[i];
        }
        long totalSubset=power(2, n);
        long sub=sumUpToK(a, n, k);
        long ans=(totalSubset-(2*sub)%mod+mod)%mod;
        System.out.println(ans);

        sc.close();
    }
private static long sumUpToK(int a[],int n, int k) {
    long dp[]=new long[k+1];    dp[0]=1;
    
    for(int i=0;i<n;i++){
        for(int j=k;j>=a[i];j--){
            dp[j]=(dp[j]+dp[j-a[i]])%mod;
        }
    }
    long ans=0;
    for(long v:dp){
        ans=(ans+v)%mod;
    
    }
    return ans;

}


    public static long power(int a,int n){
        if(n==0)return 1;
        int x=(int) power(a, n/2);
        x=(x*x)%mod;
        if(n%2!=0)x=(x*a)%mod;
        return x;
    }

}