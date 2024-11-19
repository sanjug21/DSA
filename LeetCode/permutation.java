package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class permutation {
    public static List<List<Integer>> Permutation(int nums[]){
        List<List<Integer>> ans=new ArrayList<>();
        int n=nums.length;
        ans.add(convertToList(nums));
        int loop=factorial(n)-1;
        for(int i=0;i<loop;i++){
            for(int j=n-1;j>0;j--){
                if(nums[j]>nums[j-1]){
                    sort(nums, j, n);
                    replace(nums, j-1, j, n);
                    ans.add(convertToList(nums));
                    break;
                }
            }
        }
        //System.out.println(ans);
        return ans;
    }
    public static int[] nextPermutation(int nums[]){
        int n=nums.length;
        for(int i=n-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                sort(nums, i, n);
                replace(nums, i-1, i, n);
                break;
            }
            else if(i==1)Arrays.sort(nums);
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }


    public static List<Integer> convertToList(int nums[]){
        List<Integer> list=new ArrayList<>();
        for(int i:nums)list.add(i);
        return list;
    }
    public static int factorial(int num){
        if(num==0)return 1;
        return num*factorial(num-1);
    }
    public static void sort(int a[],int st,int ed){
        for(int i=st;i<ed;i++){
            int key=i;
            for(int j=i+1;j<ed;j++){
                if(a[j]<a[key])key=j;
            }
            int temp=a[key];
            a[key]=a[i];
            a[i]=temp;
        }
    }
    public static void replace(int a[],int idx,int st,int ed){
        for(int i=st;i<ed;i++){
            if(a[idx]<a[i]){
                int temp=a[idx];
                a[idx]=a[i];
                a[i]=temp;
                return;
    
            }
        }
    }
    
    public static void main(String[] args) {
        Permutation(new int[]{1,2,3});
        nextPermutation(new int[]{3,1,2});
    }
}
