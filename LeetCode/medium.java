package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class medium {
    //3164. Find the Number of Good Pairs II
    public static long numberOfPairs(int[] nums1, int[] nums2, int k) {
       long ans=0;
       HashMap<Integer,Integer> map=new HashMap<>();
       for (int num : nums2) {
        map.put(num * k, map.getOrDefault(num * k, 0) + 1);
        }
        for(int num:nums1){
            for(int i=1;i*i<=num;i++){
                if(num%i==0){
                    int r=num/i;
                    if(map.containsKey(i))ans+=map.get(i);
                    if(r!=i && map.containsKey(r))ans+=map.get(r);

                }
            }
        }

       return ans;
    }
    // 665. Non-decreasing Array
    public static boolean checkPossibility(int[] nums) {
        boolean check=false;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1])continue;
            if(check)return false;
            if(i==0 || nums[i+1]>=nums[i-1])nums[i]=nums[i+1];
            else nums[i+1]=nums[i];
            check=true;
        }
        return true;
    }

    // 1268. Search Suggestions System
     public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans=new ArrayList<>();
        Arrays.sort(products);
        int l=0,r=products.length-1;
        for(int i=0;i<searchWord.length();i++){
            char c=searchWord.charAt(i);
            while(l<=r &&(products[l].length()<i || products[l].charAt(i)!=c))l++;
            while(l<=r &&(products[r].length()<i || products[r].charAt(i)!=c))r--;
            int len=r-l+1;
            List<String>temp=new ArrayList<>();
            for(int j=0;j<Math.min(len, 3);j++){
                temp.add(products[l+j]);
            }
            ans.add(temp);

        }
        return ans;
        
    }
    // 1461. Check If a String Contains All Binary Codes of Size K
    
    public static boolean hasAllCodes(String s, int k) {
        HashSet<String> set=new HashSet<>();
        for(int i=0;i<s.length()-k+1;i++)set.add(s.substring(i, i+k));
        return set.size()==(int)Math.pow(2, k);
    }

    // 945. Minimum Increment to Make Array Unique
    public static int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i-1]>=nums[i]){
                ans+=1+nums[i-1]-nums[i];
                nums[i]=nums[i-1]+1;
            }
        }
        return ans;
        
    }
    // 1209. Remove All Adjacent Duplicates in String II
    public static String removeDuplicates(String s, int k) {
        Stack<Character> ch=new Stack<>();
        Stack<Integer> n=new Stack<>();
        for(char c:s.toCharArray()){
            if(!ch.isEmpty() && ch.peek()==c){
                n.push(n.pop()+1);
            }
            else{
                ch.push(c);
                n.push(1);
            }
            if(n.peek()==k){
                n.pop();
                ch.pop();
            }
        }
        StringBuilder sb=new StringBuilder();
        while (!ch.isEmpty()) {
            for(int i=0;i<n.peek();i++){
            sb.insert(0,ch.peek());
            }
            n.pop();
            ch.pop();
        }
        return sb.toString();
    }

    // 402. Remove K Digits
    public static String removeKdigits(String num, int k) {
     Stack<Character> s=new Stack<>();
     for(char c:num.toCharArray()){
        while(k>0 && !s.isEmpty() && c<s.peek()){
            k--;
            s.pop();
        }
        s.push(c);
     }  
     while(k--!=0)s.pop();
     StringBuilder sb=new StringBuilder();
     while(!s.isEmpty()){
        sb.insert(0, s.pop());
     }
    if(sb.length()==0)return "0";
     while(sb.charAt(0)=='0'){
        if(sb.length()==1)return sb.toString();
        sb.deleteCharAt(0);}
     return sb.toString();
    }
    // 1094. Car Pooling
    public static boolean carPooling(int[][] trips, int capacity) {
        int n=0;
        for(int i[]:trips)n=Math.max(i[2], n);
        int des[]=new int[n+1];
        for(int i[]:trips){
            des[i[1]]+=i[0];
            des[i[2]]-=i[0];
        }
        for(int i=0;i<n;i++){
            capacity-=des[i];
            if(capacity<0)return false;
        }
        return true;
    }
    // 1029. Two City Scheduling
    public static int twoCitySchedCost(int[][] costs) {
        int n=costs.length;
        int a[][]=new int[n][3];
        for(int i=0;i<n;i++){
            a[i][0]=costs[i][1]-costs[i][0];
            a[i][1]=costs[i][0];
            a[i][2]=costs[i][1];
        }
        Arrays.sort(a,(p,q)->p[0]-q[0]);
        int ans=0;
        for(int i=0;i<n;i++){
            if(i<n/2){
                ans+=a[i][2];
            }else{
                ans+=a[i][1];
            }
        }
        return ans;
    }
   
    // 881. Boats to Save People
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l=0,r=people.length-1;
        int ans=0;
        while (l<=r) {
            int rem=limit-people[r];
            r--;
            ans++;
            if(l<=r && rem>=people[l])l++;
        }
        return ans;
    }

    // 100301. Count Pairs That Form a Complete Day II
    public static long countCompleteDayPairs(int[] hours) {
       long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < hours.length; i++) {
            int rem = hours[i] % 24;
            int c = (24 - rem) % 24; 
            
            if (map.containsKey(c)) {
               ans += (long)map.get(c);
            }
          
            map.put(rem, map.getOrDefault(rem, 0) + 1);
            
        }
        
        return ans;

    }
    // 934. Shortest Bridge
    static int []X={-1,1,0,0};
    static int []Y={0,0,-1,1};
    static int N;
    public static int shortestBridge(int[][] grid) {
        N=grid.length;
        Deque <int[]>q=new ArrayDeque<>();
        firstIsland(grid, q);
        int ans=0;
        while(!q.isEmpty()){
            for(int i=0,Q=q.size();i<Q;i++){
                int a[]=q.poll();
                for(int j=0;j<4;j++){
                    int x=a[0]+X[j];
                    int y=a[1]+Y[j];
                    if(isOut(x, y))continue;
                    if(grid[x][y]==1)return ans;
                    if(grid[x][y]==0){
                        q.add(new int[]{x,y});
                        grid[x][y]=-1;
                    }

                }
            }
            ans++;
        }
        return ans;
        
    }
    public static void firstIsland(int [][]grid,Deque<int[]>q){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(grid[i][j]==1){
                    dfs(grid, q, i, j);
                    return;
                }
            }
        }

    }
    public static void dfs(int grid[][],Deque<int[]>q,int i,int j){
        if(isOut(i, j)||grid[i][j]!=1)return;
        grid[i][j]=-1; 
        q.add(new int[]{i,j});
        dfs(grid, q, i - 1, j);
        dfs(grid, q, i + 1, j);
        dfs(grid, q, i, j - 1);
        dfs(grid, q, i, j + 1);
    }
    public static boolean isOut(int i,int j){
        return i<0 || j<0 ||j==N ||i==N;
    }
    // 633. Sum of Square Numbers
    public static boolean judgeSquareSum(int c) {
        long i=0,j=(long)Math.sqrt(c);
       
        while(i<=j){
            long total=i*i+j*j;
            if(total>c){
                j-=1;
            }
            else if(total<c){
                i+=1;
            }
            else return true;
        }
        return false;
    }
    // 18. 4Sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans=new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0;i<n-3;i++){
            if(i>0 && nums[i]==nums[i-1])continue;
            for(int j=i+1;j<n-2;j++){
                if(j>i+1 && nums[j]==nums[j-1])continue; 
                int l=j+1,r=n-1;
                while(l<r){
                    long sum=(long)nums[i]+nums[j]+nums[l]+nums[r];
                    if(sum==target){
                        ans.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        l++;
                        while(l<r && nums[l]==nums[l-1])l++;
                    }
                    else if(sum<target)l++;
                    else r--;
                }
            }
            
        }
        return ans;
    }
    // 523. Continuous Subarray Sum
    public static boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(0, -1);
        int total=0;
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
            int r=total%k;
            if(!map.containsKey(r)){
                map.put(r, i);
            }else if(i-map.get(r)>1)return true;
        }
        return false;
    }
    
    // 179. Largest Number
    public static String largestNumber(int[] nums) {
        Queue <String>q=new PriorityQueue<>((a,b)->(b+a).compareTo(a+b));
        for(int i:nums)q.add(Integer.toString(i));
        StringBuilder sb=new StringBuilder();
        while(!q.isEmpty())sb.append(q.poll());
        while (sb.length()>0 &&sb.charAt(0)=='0')sb.deleteCharAt(0);
            
        return sb.length()==0?"0":sb.toString();
    }

    // 1288. Remove Covered Intervals
    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]==b[0]?b[1]-a[1]:a[0]-b[0]);
        int ans=1;
        int idx=0;
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][1]>intervals[idx][1]){
                idx=i;
                ans++;
            }
        }
        return ans;
    }
    // 826. Most Profit Assigning Work
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int ans=0;
        int n=difficulty.length;
        int a[][]=new int [n][2];
        for(int i=0;i<n;i++){
            a[i][0]=difficulty[i];
            a[i][1]=profit[i];
        }
        Arrays.sort(a,(p,q)->q[1]-p[1]);
        for(int i:worker){
            for(int j[]:a){     
                if(i>=j[0]){
                    ans+=j[1];
                    break;
                }
            }
        }
        return ans;
    }
    // 150. Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        String symbol = "+-*/";
        for(String token: tokens) {
            if(token.length() == 1 && symbol.indexOf(token.charAt(0)) >= 0) {
                int a = stack.pop(), b = stack.pop(), c = 0;
                switch(token.charAt(0)){
                    case '+':
                    c = a + b;
                    break;
                    case '-':
                    c = b - a;
                    break;
                    case '*':
                    c = a * b;
                    break;
                    case '/':
                    c = b / a;
                }
                stack.push(c);
            }else stack.push(Integer.valueOf(token));
        }
        return stack.pop();
    }
    // 2410. Maximum Matching of Players With Trainers
    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int ans=0;
        for (int i = trainers.length - 1, j = players.length - 1; i >= 0 && j >= 0; j--) {
            if (trainers[i] >= players[j]) {
                ans++;
                i--;
            }
        }
        return ans;
        
    }
    // 658. Find K Closest Elements
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l=0,r=arr.length-k;
        while(l<r){
            int mid=l+(r-l)/2;
            if((x-arr[mid])>(arr[mid+k]-x)){
                l=mid+1;
            }
            else r=mid;
        }
        List<Integer>ans=new ArrayList<>();
        for(int i=l;i<l+k;i++)ans.add(arr[i]);
        return ans;
    }
    // 752. Open the Lock
    public static int openLock(String[] deadends, String target) {
        HashSet<String> set=new HashSet<>(Arrays.asList(deadends));
        if(set.contains("0000"))return -1;
        Deque<String> q=new ArrayDeque<>();
        q.add("0000");
        int ans=0;
        while (!q.isEmpty()) {
            int size=q.size();
            while (size-->0) {
                String lock=q.poll();
                if(lock.equals(target))return ans;
                for(String s:children(lock)){
                    if(!set.contains(s)){
                        q.add(s);
                        set.add(s);
                    }
                } 
            }
            ans++;            
        }
        return -1;

    }
    public static List<String> children(String s){
        List<String> children=new ArrayList<>();
        
        for(int i=0;i<4;i++){
            StringBuilder sb=new StringBuilder(s);
            char c=sb.charAt(i);
            sb.setCharAt(i, c=='9'?'0':(char)(c+1));
            children.add(sb.toString());
            sb.setCharAt(i, c=='0'?'9':(char)(c-1));
            children.add(sb.toString());

        }
       
        return children;
    }
    // 300. Longest Increasing Subsequence
    public static int lengthOfLIS(int[] a) {
        int n=a.length;
        int arr[]=new int[n];
        Arrays.fill(arr, 1);
        int ans=1;
        for(int i=n-2;i>=0;i--){
            for(int j=i+1;j<n;j++){
                if(a[i]<a[j])arr[i]=Math.max(arr[i], 1+arr[j]);
            }
            ans=Math.max(ans, arr[i]);
        }
        return ans;
    }
    // 1052. Grumpy Bookstore Owner
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int satisfied=0;
        int currWin=0,max=0;
        int l=0;
        for(int i=0;i<customers.length;i++){
            if(grumpy[i]==0)satisfied+=customers[i];
            else currWin+=customers[i];
            if(i-l+1>minutes){
                if(grumpy[l]==1){
                    currWin-=customers[l];
                }
                l++;
            }
            max=Math.max(max, currWin);
        }
        return satisfied+max;
    }
    // 1143. Longest Common Subsequence
    public static int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length(),n=text2.length();
        int dp[][]=new int[m+1][n+1];
        char []t1=text1.toCharArray();
        char t2[]=text2.toCharArray();
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(t1[i]==t2[j])dp[i][j]=1+dp[i+1][j+1];
                else dp[i][j]=Math.max(dp[i][j+1],dp[i+1][j]);
            }
        }
        return dp[0][0];
    }
    // 200. Number of Islands
    public static int numIslands(char[][] grid) {
        if(grid.length==0)return 0;
        int ans=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    ans++;
                }
            }
        }
        return ans;
    }
    public static void dfs(char grid[][],int r,int c){
       if(r<0 || r>=grid.length ||c<0 ||c>=grid[0].length || grid[r][c]!='1')return;
        grid[r][c]='0';
        dfs(grid, r+1, c);
        dfs(grid, r-1, c);
        dfs(grid, r, c+1);
        dfs(grid, r, c-1);

    }
    // 1248. Count Number of Nice Subarrays
    public static int numberOfSubarrays(int[] nums, int k) {
        int ans=0;
        int l=0,m=0;
        int count=0;
        for(int r=0;r<nums.length;r++){
            if(nums[r]%2!=0)count++;
            while(count>k){
                if(nums[l]%2!=0)count--;
                l++;
                m=l;
            }
            if(count==k){
                while (nums[m]%2==0) {
                    m++;        
                }
                ans+=m-l+1;
            }

        }
        return ans;
    }
    // 100344. Minimum Operations to Make Binary Array Elements Equal to One I
    public static int minOperations(int[] nums) {
        int l=0;
        int ans=0;
        int len=nums.length;
        for(;l<len-3;l++){
            if(nums[l]==1)continue;
            else break;
        }
        for(int r=l;r<len;r++){
            while(l<=r &&nums[l]==1)l++;
            if(r-l+1==3){
                int n=3;
                int i=l;
                while(n-->0){
                    if(nums[i]==1)nums[i]=0;
                    else nums[i]=1;
                    i++;
                }
                ans++;
            }
           
        }
        for(int i=1;i<=3;i++){
            if(nums[len-i]==0)return -1;
        }
       
        return ans;
    }
    // 838. Push Dominoes
    public static String pushDominoes(String dominoes) {
        char a[]=dominoes.toCharArray();
       
        return new String(a);
        
    }
    public static void main(String[] args) {
      System.out.println(minOperations(new int[]{0,1,1,1,0,0}));
        
        
    }
    
}
