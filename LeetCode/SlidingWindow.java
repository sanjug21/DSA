package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindow {
    //3. Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s){
        int ans=0;
        int l=0;
        HashSet<Character>set=new HashSet<>();
        for(int r=0;r<s.length();r++){
            char c=s.charAt(r);
            while(set.contains(c)){
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            ans=Math.max(ans, r-l+1);

        }
        return ans;
    }
    //76. Minimum Window Substring
    public static String minWindow(String s, String t){
        
        if(t.length()==0)return "";
        HashMap<Character,Integer>tMap=new HashMap<>();
        HashMap<Character,Integer>sMap=new HashMap<>();
        int st=-1,ed=-1,len=Integer.MAX_VALUE;
        
        for(char c:t.toCharArray()){
         tMap.put(c,tMap.getOrDefault(c, 0)+1);
        }
     
        int have=0,need=tMap.size();
        int i=0;
       
        for(int j=0;j<s.length();j++){
            char c=s.charAt(j);
            
            sMap.put(c, sMap.getOrDefault(c, 0)+1);
            
            if(tMap.containsKey(c)&& sMap.get(c).intValue()==tMap.get(c).intValue()){
             
                have++;
            }
          
            while(have==need){
                if((j-i+1)<len){
                    st=i;
                    ed=j;
                    len=j-i+1;
                }
                char leftCh=s.charAt(i);
                sMap.put(leftCh, sMap.get(leftCh)-1);
                if(tMap.containsKey(leftCh)&& sMap.get(leftCh).intValue()<tMap.get(leftCh).intValue())have--;
                i++;
            }
        }
      
        return len==Integer.MAX_VALUE?"":s.substring(st,ed+1);
    }

    //424. Longest Repeating Character Replacement
    public static int characterReplacement(String s, int k){
        int res=0;
        int []dp=new int[26];
        char c[]=s.toCharArray();
        int l=0,r=0,len=c.length;
        int freq=0;
        while (r<len) {
            freq=Math.max(freq,++dp[c[r]-'A']);
            if(((r-l+1)-freq)>k){
                dp[c[l]-'A']--;
                l++;
            }
            res=Math.max(res, r-l+1);
            
        }
        return res;
    }

    //438. Find All Anagrams in a String
    public static List<Integer> findAnagrams(String s, String p){
        if(s.length()<p.length())return new ArrayList<>();
         List<Integer> list=new ArrayList<>();
         HashMap<Character,Integer>pMap=new HashMap<>();
         HashMap<Character,Integer>sMap=new HashMap<>();
         for(int i=0;i<p.length();i++){
            char c=p.charAt(i);
            char cs=s.charAt(i);
            pMap.put(c, pMap.getOrDefault(c, 0)+1);
            sMap.put(cs, sMap.getOrDefault(cs, 0)+1);
         }
       
         if(pMap.equals(sMap))list.add(0);
         int l=0;
         for(int r=p.length();r<s.length();r++){
            char c=s.charAt(r);
            char rc=s.charAt(l);
            sMap.put(c, sMap.getOrDefault(c, 0)+1);
            sMap.put(rc, sMap.get(rc)-1);
            if(sMap.get(rc)==0)sMap.remove(rc);
            l++;
            if(sMap.equals(pMap))list.add(l);

         }
        return list;
    }

    //239. Sliding Window Maximum
    public static int[] maxSlidingWindow(int[] nums, int k){
        int n=nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>(); // Use a deque to store indices

        for (int i = 0; i < n; i++) {
            if(!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i); 
            if (i >= k - 1) {
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return ans;
    }
    //1838. Frequency of the Most Frequent Element
    public static int maxFrequency(int[] nums, int k){
        Arrays.sort(nums);
        
        int l=0,r=0;
        long total=0,ans=0;
        while(r<nums.length){
            total+=nums[r];
           
            while((nums[r]*(r-l+1L))>(total+k)){
                total-=nums[l++];
            }
            ans=Math.max(ans,r-l+1L);
            r++;
        }
        return (int)ans;
    }
   //1888. Minimum Number of Flips to Make the Binary String Alternating
   public static int minFlips(String s){
    
    s=s+s;
    char c[]=s.toCharArray();
    int n=c.length;
    int ans=n;
    char alt1[]=new char[n];
    char alt2[]=new char[n];
    for(int i=0;i<n;i++){
        if(i%2==0){
            alt1[i]='0';
            alt2[i]='1';

        }else{
            alt1[i]='1';
            alt2[i]='0';
        }
    }
    int l=0,r=0;
    int diff1=0,diff2=0;
    while(r<n){
        if(c[r]!=alt1[r])diff1++;
        if(c[r]!=alt2[r])diff2++;
        if((r-l+1)>n/2){
            if(c[l]!=alt1[l])diff1--;
            if(c[l]!=alt2[l])diff2--;
            l++;
        }
        if((r-l+1)==n/2)ans=Math.min(ans, Math.min(diff1,diff2));
       
        r++;
    }
   
    return ans;
   }

   //567. Permutation in String
   public static boolean checkInclusion(String s1, String s2){
    if(s1.length()>s2.length())return false;
    char []c1=new char[26];
    char c2[]=new char[26];
    for(int i=0;i<s1.length();i++){
        c1[s1.charAt(i)-'a']++;
        c2[s2.charAt(i)-'a']++;
    }
    int match=0;
    int l=0;
    for(int i=0;i<26;i++)if(c1[i]==c2[i])match++;
    for(int i=s1.length();i<s2.length();i++){
        if(match==26)return true;
        int index=s2.charAt(i)-'a';
        c2[index]++;
        if(c2[index]==c1[index])match++;
        else if(c2[index]==c1[index]+1)match--;

        index=s2.charAt(l)-'a';
        c2[index]--;
        if(c2[index]==c1[index])match++;
        else if(c2[index]==c1[index]-1)match--;

        l++;

    }
    return match==26?true:false;
   }
   //209. Minimum Size Subarray Sum
   public static int minSubArrayLen(int target, int[] nums) {
    int ans=Integer.MAX_VALUE,sum=0,j=0,i=0;
    while(j<nums.length){
        sum+=nums[j++];
        while(sum>=target){
            ans=Math.min(ans,j-i);
            sum-=nums[i++];
        }
    }
        return ans==Integer.MAX_VALUE?0:ans;
    }

    //187. Repeated DNA Sequences
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans=new ArrayList<>();
        HashSet<String>set=new HashSet<>();
        for(int i=0;i<s.length()-9;i++){
            String str=s.substring(i, i+10);
            if(set.contains(str)){
                if(!ans.contains(str))ans.add(str);
            }
            else set.add(str);
        }
        return ans;
    }

    public static void main(String[] args) {
      
       System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
