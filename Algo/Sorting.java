package Algo;

import java.util.Arrays;

public class Sorting {
    // Insertion Sort
    public static void Insertion(int []nums){
        for(int i=1;i<nums.length;i++){
            int j=i-1;
            int key=nums[i];
            while(j>=0 && key<nums[j]){
                nums[j+1]=nums[j];
                j--;
            }
            nums[j+1]=key;
        }

     // System.out.println(Arrays.toString(nums));
    }
    

    public static void Selection(int nums[]){
        int len=nums.length;
        for(int i=0;i<len;i++){
            int key=i;
            for(int j=i+1;j<len;j++){
                if(nums[j]<nums[key])key=j;
            }
            int temp=nums[key];
            nums[key]=nums[i];
            nums[i]=temp;
        }

     // System.out.println(Arrays.toString(nums));
    }


    public static void Bubble(int []nums){
        int len=nums.length;
        boolean swapped=false;
        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-i-1;j++){
                swapped=false;
                if(nums[j]>nums[j+1]){
                    int temp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=temp;
                    swapped=true;
                }
            }
            if(swapped==false)break;  
        }

     // System.out.println(Arrays.toString(nums));    
    }


    public static int[] merge(int nums[],int st,int ed){
        if(st==ed){
            int bs[]={nums[st]};
            return bs;
        }
        int mid=(st+ed)/2;
        int []f=merge(nums, st, mid);
        int []l=merge(nums, mid+1, ed);
        return mergeTwoArray(f, l);
        
    }
    public static int[] mergeTwoArray(int a[],int b[]){
        int l1=a.length,l2=b.length;
        int ans[]=new int[l1+l2];
        int idx=0,i=0,j=0;
        while (i<l1 && j<l2) {
            if(a[i]<b[j])ans[idx++]=a[i++];
            else ans[idx++]=b[j++];
        }
        while (i<l1)ans[idx++]=a[i++];
        while (j<l2)ans[idx++]=b[j++];                
        return ans;
    }

    public static void  Quick(int[] nums,int st,int ed){
        if(st<ed){
        int idx=partition(nums, st, ed);
        Quick(nums,st,idx-1);
        Quick(nums, idx+1, ed);
}
    }
    public static int partition(int nums[],int s,int ed){
        // below three line are for randomized sort to improve time complexity to n log n
        // Random rd=new Random();
        // int ii=rd.nextInt(ed-s+1)+s;
        // swap(nums, ii, ed);
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


    // counting sort
    public static void countingSort(int nums[]){
        
        int max=Arrays.stream(nums).max().getAsInt();
        int fre[]=new int[max+1];
        for(int i:nums)fre[i]++;
        for(int i=1;i<fre.length;i++)fre[i]+=fre[i-1];
        int arr[]=new int[fre[max]];
        for(int i=nums.length-1;i>=0;i--){
            int num=nums[i];
            arr[fre[num]-1]=num;
            fre[num]--;
            
        }
       System.out.println(Arrays.toString(arr));


    }
public static void main(String[] args) {
    
}

   // lambda function
//Arrays.sort(a, (p,q)->(q[col]-p[col]));
}
