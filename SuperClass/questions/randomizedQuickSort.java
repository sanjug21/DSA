package questions;

import java.util.Arrays;
import java.util.Random;

public class randomizedQuickSort {
    public static void  Quick(int[] nums,int st,int ed){
        if(st<ed){
        int idx=partition(nums, st, ed);
        Quick(nums,st,idx-1);
        Quick(nums, idx+1, ed);
}
    }
    public static int partition(int nums[],int s,int ed){
        Random rd=new Random();
        int ii=rd.nextInt(ed-s+1)+s;
        swap(nums, ii, ed);
        int idx=s;
        int item=nums[ed];
        for(int i=s;i<ed;i++ )if(nums[i]<item)swap(nums, i, idx++);
        swap(nums, ed,idx);
        return idx;
    }
    public static void swap(int a[],int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void main(String[] args) {
        int nums[]={5,7,3,1,8,2,9,6,0};
        Quick(nums, 0, 8);
        System.out.println(Arrays.toString(nums));
    }
    
}
