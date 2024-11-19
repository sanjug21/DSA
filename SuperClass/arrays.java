
import java.util.Arrays;

import Algo.Sorting;

public class arrays {
    public static void swap(int a[],int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    // partition array
    public static void partitionArray(int nums[],int st,int ed){
        int idx=st;
        int item=nums[ed-1];
        for(int i=1;i<ed-1;i++ )if(nums[i]<item)swap(nums, i, idx++);
        swap(nums, ed-1,idx);
       // System.out.println(Arrays.toString(nums));
        System.out.println(idx+1);
    }

    public static boolean isPrime(int n){
        int div=2;
        while(div*div<=n)if(n%div++==0)return false;
        return true;
    }

    public static void main(String[] args) {
        int nums[]={5,7,8,1,2,6,9,3,10,13,12,15,14,4};
        //partitionArray(nums, 0, 7);
        Sorting.Quick(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
        ;
       
    }
}
