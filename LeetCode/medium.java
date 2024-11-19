package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
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
    // 995. Minimum Number of K Consecutive Bit Flips
     public static int minKBitFlips(int[] nums, int k) {
        int ans=0;
        int flip=0;
        for(int i=0;i<nums.length;i++){
            if(i-k>=0 && nums[i-k]==2)flip--;
            if((nums[i]+flip)%2==0){
                if(i+k>nums.length)return -1;
                ans++;
                flip++;
                nums[i]=2;
            }
            
            }         
       
        return ans;   
    }
    // 2439. Minimize Maximum of Array
    public static int minimizeArrayValue(int[] nums) {
        long ans=nums[0];
        long total=ans;
        for(int i=1;i<nums.length;i++){
            total+=nums[i];
            if(total>ans*(i+1)){
                ans=(total-1)/(i+1)+1;
            }
           
        }
        return (int)ans;
        
    }
    // 2405. Optimal Partition of String
    public static int partitionString(String s) {
        int ans=1;
        HashSet<Character> set=new HashSet<>();
        for(char c:s.toCharArray()){
            if(set.contains(c)){
                ans++;
                set.clear();
            }
            set.add(c);
        }
        return ans;
        
    }
    // 120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        int dp[]=new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                int n=triangle.get(i).get(j);
                dp[j]=n+Math.min(dp[j],dp[j+1]);
            }
        }
        return dp[0];
    }
    // 875. Koko Eating Bananas
    public static int minEatingSpeed(int[] piles, int h) {
        int ans=0;
        for(int i:piles){
            if(i>ans)ans=i;
        }
        int l=1,r=ans;
        while(l<=r){
            int m=(l+r)/2;
            int hours=0;
            for(int i:piles){
                hours+=Math.ceil((double)i/m);
            }
            if(hours<=h){
                ans=Math.min(ans, m);
                r=m-1;
            }else l=m+1;
            
        }
        return ans;
    }
    // 698. Partition to K Equal Sum Subsets
    public static boolean canPartitionKSubsets(int[] nums, int k) {
       
        int sum=0;
        for(int i:nums)sum+=i;
        int target=sum/k;
        
        return canPartition(nums, 0, k, 0, target);
    }
    public static boolean canPartition(int nums[],int i,int k,int sum,int target){
        if(k==0)return true;
        if(target==sum){
            return canPartition(nums, 0, k-1,0, target);
        }
        for(int j=i;j<nums.length;j++){
            if(nums[j]!=-1 && sum+nums[j]<=target){
            int a=nums[j];
            nums[j]=-1;
            
            if(canPartition(nums, j+1, k, sum+a, target))return true;
            nums[j]=a;
        }
        }
        return false;
    }
    // 93. Restore IP Addresses
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans=new ArrayList<>();
        if(s.length()>12 || s.length()<4)return ans;
        ip(ans, s.toCharArray(), 0, 0, new StringBuilder());
        return ans;
    }
    public static void ip(List<String> ans,char c[],int i,int dots,StringBuilder sb){
        if(dots==4 && i==c.length){
            
            ans.add(sb.substring(0, sb.length()-1));
            return;
        }
        if(dots>4){
            
            return;}
            int st=sb.length();
        for (int j = i; j < Math.min(c.length, (i + 3)); j++) {
            sb.append(c[j]);
            System.out.println(sb.toString());
            if (Integer.valueOf(sb.substring(sb.length() - 1 - (j - i))) < 256 && (i == j || sb.charAt(sb.length() - 1) != '0')) {
              sb.append('.');
              ip(ans, c, j + 1, dots + 1, sb);
              sb.setLength(sb.length()-1);
            }
        }
        sb.setLength(st);

    }
    // 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int ans=0;
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=arr[i];
        }
        for(int r=0;r<arr.length-k+1;r++){
            sum+=arr[r+k-1];   
            double avg=(double)sum/k;       
            if(avg>=threshold)ans++;
            sum-=arr[r];
        }
        return ans;
    }
    // 904. Fruit Into Baskets
    public static int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int l=0;
        int ans=0;
        int total=0;
        for(int r=0;r<fruits.length;r++){
            map.put(fruits[r], map.getOrDefault(fruits[r],0)+1);
            total++;
            while(map.size()>2){
                int key=fruits[l];
                map.put(key, map.get(key)-1);
                total--;
                l++;
                if(map.get(key)==0)map.remove(key);
            }
            ans=Math.max(total, ans);
        }
        return ans;
    }
    public static int bestTeamScore(int[] scores, int[] ages) {
        int n=ages.length;
        int a[][]=new int[n][2];
         int dp[]=new int[n];
        for(int i=0;i<n;i++){
           
           a[i][0]=scores[i];
           a[i][1]=ages[i];
          
        }   
        Arrays.sort(a,(p,q)->p[0]==q[0]?p[1]-q[1]:p[0]-q[0]);
        for(int i=0;i<n;i++)dp[i]=scores[i];
        int ans=0;      
        for(int i=0;i<n;i++){
           int curr=a[i][0];
           int cAge=a[i][1];
           for(int j=0;j<i;j++){
               int age=a[j][1];
               if(cAge>=age){
                   dp[i]=Math.max(dp[i],curr+dp[j]);
               }
           }
          
           ans=Math.max(ans,dp[i]);
        }
        return ans;
       }
    

    // 399. Evaluate Division
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String,HashMap<String,Double>> graph=new HashMap<>();
     for(int i=0;i<equations.size();i++){
        String s=equations.get(i).get(0);
        String d=equations.get(i).get(1);
        if(!graph.containsKey(s))graph.put(s,new HashMap<>());
        if(!graph.containsKey(d))graph.put(d,new HashMap<>());
        graph.get(s).put(d,values[i]);
        graph.get(d).put(s,1/values[i]);
     } 
     
     double []ans=new double[queries.size()];
     for(int i=0;i<ans.length;i++){
        String a = queries.get(i).get(0), b = queries.get(i).get(1);
            if(!graph.containsKey(a) || !graph.containsKey(b))ans[i]=-1.0;
            else if(a.equals(b))ans[i]=1.0;
            else ans[i] = dfs(graph,a,b,1,new HashSet<>());
     }
       return ans;
    }   
    public static double bfs( HashMap<String,HashMap<String,Double>> graph,String s,String d){
        if (!graph.containsKey(s) || !graph.containsKey(d)) return -1.0;
        Deque<Pairs> q = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>();
        q.add(new Pairs(s, 1));
        set.add(s);
        while (!q.isEmpty()) {
          Pairs temp = q.poll();
          if (temp.w == 0.0) return -1.0; 
          if (temp.s.equals(d)) return temp.w;
          for (Map.Entry<String, Double> map : graph.get(temp.s).entrySet()) {
            if (!set.contains(map.getKey())) {
              q.add(new Pairs(map.getKey(), temp.w * map.getValue()));
              set.add(map.getKey());
            }
          }
        }
        return -1.0;
    }
    
    public static double dfs(HashMap<String, HashMap<String, Double>> graph,String a,
    String b, double prod, Set<String> visited){
        if(a.equals(b))return prod;
        double val =-1.0;
        if(visited.contains(a))return val;
        visited.add(a);
        for(String key: graph.get(a).keySet()){
            val = dfs(graph,key,b,prod*graph.get(a).get(key),visited);
            if(val != -1.0)return val;
        }
        return val;
    }

    // 2542. Maximum Subsequence Score
    public static long maxScore(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        int a[][]=new int[n][2];
        for(int i=0;i<n;i++){
            a[i][0]=nums1[i];
            a[i][1]=nums2[i];
        }
        Arrays.sort(a,(p,q)->q[1]-p[1]);
        PriorityQueue<Integer>q=new PriorityQueue<>();
        long sum=0;
        long ans=0;
        for(int i=0;i<n;i++){
            sum+=a[i][0];
            q.add(a[i][0]);
            if(q.size()>k){
                int p=q.poll();
                sum-=p;
            }
            if(q.size()==k){
                ans=Math.max(ans, sum*a[i][1]);
            }
        }
        return ans;
    }
    static class Pairs{
        String s;
        double w;
        Pairs(String s,double w){
            this.s=s;
            this.w=w;
        }
    }
    //2369. Check if There is a Valid Partition For The Array
    public static boolean validPartition(int[] nums) {
        int n=nums.length-1;
        boolean two=nums[n]==nums[n-1];
        if(n+1==2)return two;
        boolean three=(nums[n]==nums[n-1] && nums[n]==nums[n-2])||(nums[n]==nums[n-1]+1 && nums[n-1]==nums[n-2]+1);
        boolean dp[]={three,two,false};
        for(int i=n-3;i>=0;i--){
            boolean curr=(nums[i]==nums[i+1])&&dp[1];
            curr=curr||((nums[i]==nums[i+1] && nums[i]==nums[i+2])||( nums[i]+1==nums[i+1] && nums[i]+2==nums[i+2]))&&dp[2];
            dp[2]=dp[1];
            dp[1]=dp[0];
            dp[0]=curr;
        }
        return dp[0];
    }
    // 207. Course Schedule
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer,List<Integer>>graph=new HashMap<>();
        for(int []i:prerequisites){
            graph.putIfAbsent(i[0], new ArrayList<>());
            graph.get(i[0]).add(i[1]);
        }
        for(int i=0;i<numCourses;i++){
            if(!dfs(graph, new HashSet<>(),i))return false;
        }
        return true;
    }
    public static boolean dfs(HashMap<Integer,List<Integer>>graph,HashSet<Integer> set,int curr){
        if(set.contains(curr))return false;
        if(graph.get(curr)==null)return true;
        set.add(curr);
        for(int i:graph.get(curr)){
            if(!dfs(graph, set, i))return false;
        }
        set.remove(curr);
        graph.put(curr, null);
        return true;
    }
    // 100351. Alternating Groups II
    public static int numberOfAlternatingGroups(int[] colors, int k) {
        int n=colors.length;
       int ans=0;
       Deque<Integer>q=new LinkedList<>();
       HashSet<Integer>set=new HashSet<>();
       
       for(int i=0;i<k+n;i++){
            int r=i%n;
            if(set.contains(r))break;
            if(!q.isEmpty()&&q.peekLast()!=colors[r]){
                q.addLast(colors[r]);
            }else{
                q.clear();
                q.addLast(colors[r]);
            }
           if(q.size()==k){
            set.add(r);
            q.poll();
            ans++;}
           

       }
       return ans;
   }
   // 100328. Generate Binary Strings Without Adjacent Zeros
   public static List<String> validStrings(int n) {
    List<String> ans=new ArrayList<>();
    help(ans, n, new StringBuilder());
        return ans;
   }
   public static void help(List<String> ans,int n,StringBuilder sb){
    if(n==0){
        ans.add(sb.toString());
        return;
    }
    if(sb.length()==0 || sb.charAt(sb.length()-1)=='1'){
        sb.append('0');
        help(ans, n-1, sb);
    sb.deleteCharAt(sb.length()-1);
    }
    sb.append('1');
    help(ans, n-1, sb);
    sb.deleteCharAt(sb.length()-1);
   }
//    1658. Minimum Operations to Reduce X to Zero
   public static int minOperations(int[] nums, int x) {
    int sum=0;
    for(int i:nums)sum+=i;
    int target=sum-x;
    int max=-1;
    int l=0;
    int curr=0;
    for(int r=0;r<nums.length;r++){
      curr+=nums[r];
      while(l<=r && curr>target){
          curr-=nums[l];
          l++;
      }
      if(curr==target){
          max=Math.max(max,r-l+1);
      }
    }  
    return max==-1?-1:nums.length-max;
  }
    //   1823. Find the Winner of the Circular Game
    public int findTheWinner(int n, int k) {
        int ans=0;
        for(int i=1;i<n+1;i++){
            ans=(ans+k)%i;
        }
        return ans+1;
        // return help(ans, k)+1;
    }
    public static int help(int n,int k){
        if(n==1)return 0;
        return (help(n-1,k)+k)%n;
    }
    // 229. Majority Element II

    public static List<Integer> majorityElement(int[] nums) {
        int m1=0,m2=0;
        int c1=0,c2=0;
        for(int i:nums){
            if(i==m1)c1++;
            else if(i==m2)c2++;
            else if(c1==0){
                m1=i;
                c1++;
            }else if(c2==0){
                m2=i;
                c2++;
            }else {
                c1--;
                c2--;
            }
        }
        c1=0;
        c2=0;
        for(int i:nums){
            if(i==m1)c1++;
            else if(i==m2)c2++;
        }
        List<Integer>ans=new ArrayList<>();
        int n=nums.length;
        if(c1>n/3)ans.add(m1);
        if(c2>n/3)ans.add(m2);
        return ans;
    }
    // 2462. Total Cost to Hire K Workers
    public static long totalCost(int[] costs, int k, int c) {
        PriorityQueue<Integer> lq=new PriorityQueue<>();
        PriorityQueue<Integer> rq=new PriorityQueue<>();
        int l=0,r=costs.length-1;
        long ans=0;
        int idx=0;
        while(l<=r && idx<c){
            lq.add(costs[l]);
            rq.add(costs[r]);
            l++;
            r--;
            idx++;

        }
       
        while(k--!=0){
            
            if(lq.peek()<=rq.peek()){
                ans+=lq.poll();
                if(l<=r){
                    lq.add(costs[l]);
                    l++;
                }
            }else{
                ans+=rq.poll();
                if(l<=r){
                    rq.add(costs[r]);
                    r--;
                }
            }
            System.out.println(lq+""+rq);
           System.out.println(ans); 
        }
        return ans;
    }
    // 1190. Reverse Substrings Between Each Pair of Parentheses
    public static String reverseParentheses(String s) {
        HashMap<Integer,Integer> pair=new HashMap<>();
         Stack<Integer> st=new Stack<>();
         char [] sb=s.toCharArray();
         for(int i=0;i<sb.length;i++){
          if(sb[i]=='('){
              st.push(i);
          }else if(sb[i]==')'){
              int j=st.pop();
              pair.put(i, j);
              pair.put(j, i);
          }
         
         }
         StringBuilder ans=new StringBuilder();
         int i=0,dir=1;
         while(i<sb.length){
          if(sb[i]=='(' || sb[i]==')'){
              i=pair.get(i);
              dir=-dir;
          }else{
              ans.append(sb[i]);
          }
          i+=dir;
         } 
         return ans.toString();  
      }
      // 1717. Maximum Score From Removing Substrings
      public static int maximumGain(String s, int x, int y) {
        int ans=0;
        StringBuilder sb=new StringBuilder(s);
        char f='a',l='b';
        if(x<y){
            f='b';
            l='a';
        }
        ans+=score(sb, f, l, Math.max(x, y))+score(sb, l,f, Math.min(x, y));
        return ans;
      }
      public static int score(StringBuilder sb,int f,int l,int scor){
        int ans=0;
        Stack<Character>s=new Stack<>();
        
        for(int i=0;i<sb.length();i++){

            if(sb.charAt(i)==l && !s.isEmpty()&& s.peek()==f){
                s.pop();
                ans+=scor;
            }else{
                s.push(sb.charAt(i));
            }
        }
        sb.setLength(0);
        while(!s.isEmpty()){
            sb.insert(0, s.pop());
        }
        return ans;
      }
    //   767. Reorganize String
    public static String reorganizeString(String s) {
        HashMap<Character,Integer>map=new HashMap<>();
        for(char c:s.toCharArray())map.put(c, map.getOrDefault(c, 0)+1);
        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->b.val-a.val);
        System.out.println(map);
        for(char c:map.keySet()){
            pq.add(new Pair(map.get(c), c));
        }
        StringBuilder sb=new StringBuilder();       
        Pair prev=null;
        while(!pq.isEmpty() || prev!=null){
            if(prev!=null && pq.isEmpty())return "";
            Pair p=pq.poll();
            sb.append(p.c);
            if(prev!=null){
                pq.add(prev);
                prev=null;
            }
            if(p.val-1!=0)prev=new Pair(p.val-1,p.c);
        }
        return sb.toString();
    }
    static class Pair {
        char c;
        int val;
        Pair(int val,char c){
            this.c=c;
            this.val=val;
        }
        
    }
    // 6 zig-zag
    public static String convert(String s, int r) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<r;i++){
            int inc=(r-1)*2;
            for(int j=i;j<s.length();j+=inc){
                sb.append(s.charAt(j));
                if(i>0 &&i<(r-1)&& (j+inc-2*i)<s.length())sb.append(s.charAt(j+inc-2*i));
            }
        }    
        return sb.toString();
        }

    // 838. Push Dominoes
    public static String pushDominoes(String dominoes) {
        char a[]=dominoes.toCharArray();
       
        return new String(a);
        
    }
    
    public static void main(String[] args) {
       

     System.out.println();
    }
       
    
}
