package LeetCode;

import java.util.Arrays;

public class backtrack {
    // 473. Matchsticks to Square
    public static boolean makesquare(int[] matchsticks) {
        int sum=0;
        for(int i:matchsticks)sum+=i;
        if(sum%4!=0)return false;
        int a[]=new int[4];
        Arrays.sort(matchsticks);
        return matchStick(a,matchsticks,matchsticks.length-1,sum/4);
    }
    public static boolean matchStick(int []a,int matchStick[],int i,int s){
        if(i<0)return true;
        for(int j=0;j<4;j++){
            if(a[j]+matchStick[i]<=s){
                a[j]+=matchStick[i];
                if(matchStick(a, matchStick, i-1, s))return true;
                a[j]-=matchStick[i];
            }
        }
    return false;
    }
 public static void main(String[] args) {
    
 }   
}
