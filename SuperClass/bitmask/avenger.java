public class avenger {
    
    public static void main(String[] args) {
        int arr[] = { 2, 3, 7};
        Count(arr, 1000);
    }

    public static int Count(int arr[],int n){
        int l=arr.length;
        int ans=0;
        for(int i=0;i<(1<<l);i++){
           if(CountBits(i)%2==0){
              ans-=CountDivisible(arr,i,n);
           }else{
              ans+=CountDivisible(arr,i,n);
           }
        }
        return ans;
    }
    public static int CountBits(int n){
        int count=0;
        while(n>0){
            count++;
            n=n&(n-1);
        }
        return count;
    }
    public static int CountDivisible(int arr[],int i,int n){
        
       int p=1;
       int idx=0;
       while(i>0){
           if((i&1)==1){
               p=p*arr[idx];
           }
           idx++;
           i=i>>1;
       }
         return n/p;
    }
}
