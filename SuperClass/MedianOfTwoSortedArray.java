public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        int a1[]={1,2,3,100};
        int a2[]={10,20,30,40,50,60};
        System.out.println(median(a1,a2));


        int a3[] = {1, 3, 8};
        int a4[] = {7, 9, 10, 11};
        System.out.println(median(a3, a4));
    }
    public static double median(int a1[],int a2[]){ 

        int lo=0;
        int hi=a1.length;
        int n=a1.length,m=a2.length;
        while(lo<=hi){
            int mid1=(lo+hi)/2;
            int mid2=(n+m+1)/2-mid1;
            int l1=mid1==0?Integer.MIN_VALUE:a1[mid1-1];
            int l2=mid2==0?Integer.MIN_VALUE:a2[mid2-1];
            int r1=mid1==n?Integer.MAX_VALUE:a1[mid1];
            int r2=mid2==m?Integer.MAX_VALUE:a2[mid2];
            if(l1<=r2 && l2<=r1){
                if((n+m)%2==0){
                    return (Math.max(l1,l2)+Math.min(r1,r2))/2.0;
                }else{
                    return Math.max(l1,l2);
                }
                
            } else if(l1>=r2){
                hi=mid1-1;
        }else{
                lo=mid1+1;
            }
        }

        return 0.0;
    }
}
