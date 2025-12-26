package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class hard {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
             }
         }

    // 149. Max Points on a Line
    public static int maxPoints(int[][] points) {
       int ans=1;
       for(int i=0;i<points.length;i++){
        int p1=points[i][0];
        HashMap<String,Integer>map=new HashMap<>();
        
        for(int j=i+1;j<points.length;j++){
            int p2 = points[j][0];
            int dy = points[j][1] - points[i][1];
            int dx = p2 - p1;
            String slope;
            if(p1==p2){
                slope="inf";
            }else {
                int gcd = gcd(dy, dx);
                slope= (dy / gcd) + "/" + (dx / gcd);
            }
            map.put(slope, map.getOrDefault(slope, 0)+1);
            ans=Math.max(ans, map.get(slope)+1);

        }
       }
       return ans; 
    }
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    // 1383. Maximum Performance of a Team
     public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int play[][]=new int [n][2];
        for(int i=0;i<n;i++){
            play[i][0]=efficiency[i];
            play[i][1]=speed[i];
        }
        Arrays.sort(play,(p1,p2)->(p2[0]-p1[0]));
        PriorityQueue<Integer> pq=new PriorityQueue<>(k);
        long ans=0;
        long spd=0;
          for(int i=0;i<n;i++){
           
              if(pq.size()>=k)spd-=pq.remove();
              pq.add(play[i][1]);
              spd+=play[i][1];
              ans=Math.max(ans,spd*play[i][0]);
             
          }
        return (int)(ans%1000000007);
    }

    // 1220. Count Vowels Permutation
    public static int countVowelPermutation(int n) {
        if(n==1)return 5;
        long dp[]={1,1,1,1,1};
        long mod=1000000007;
        // a=0  e=1 i=2 o=3 u=4
        for(int i=0;i<n-1;i++){
            long temp[]=new long[5];
            temp[0]=(dp[1]+dp[2]+dp[4])%mod;
            temp[1]=(dp[0]+dp[2])%mod;
            temp[2]=(dp[1]+dp[3])%mod;
            temp[3]=dp[2];
            temp[4]=(dp[2]+dp[3])%mod;
            dp=temp;
        }
        long ans=0;
        for(long i:dp)ans=(ans+i)%mod;
        return (int) ans;
    }

    //51 N-Queens
    public static List<List<String>> ans;
    public static List<List<String>> solveNQueens(int n) {
     ans=new ArrayList<>();
    char queen[][]=new char[n][n];
    for(char[] row: queen){
        Arrays.fill(row,'.');
    };
    print(queen,n,0);
    return ans;


    }
    public static void print(char board[][],int n,int row) {
        if(n==0){
            ans.add(convert(board));
            return;
        }
        for(int col=0;col<board[0].length;col++){
        if(isValid(board,row,col)){
            board[row][col]='Q';
            print(board, n-1, row+1);
            board[row][col]='.';

        }
    }
        
    }
     public static boolean isValid(char board[][],int row,int col){
        int r=row;
        while(r>=0){
            if(board[r][col]=='Q')return false;
            r--;
        }
        r=row;
        int c=col;
        while(r>=0 && c>=0){
            if(board[r][c]=='Q')return false;
            r--;
            c--;
        }
        r=row;
        c=col;
        while(r>=0 && c<board[0].length){
            if(board[r][c]=='Q')return false;
            r--;
            c++;
        }
        return true;
    }
     public static ArrayList<String> convert(char arr[][]){
        ArrayList<String> con=new ArrayList<>();
        int n=arr.length;
        for(int i=0;i<n;i++){
           String s="";
           for(int j=0;j<n;j++){
               s+=arr[i][j];
           }
           con.add(s);
        }
        return con;
    }

    //410. Split Array Largest Sum
    public static int splitArray(int[] nums, int k) {
        int l=0,r=0;
        for(int i:nums){
            if(i>l)l=i;
            r+=i;
        }
        int ans=r;
        while(l<=r){
            int mid=(l+r)/2;
            if(check(nums,k,mid)){
                ans=mid;
                r=mid-1;
            }else l=mid+1;

        }
        return ans;
    }
    public static boolean check(int []nums,int m,int target){
        int subArray=1;
        int currSum=0;
        for(int i:nums){
            currSum+=i;
            if(currSum>target){
                subArray++;
                currSum=i;
            }
        }
        return subArray<=m;
    }

    //329. Longest Increasing Path in a Matrix
    public static int longestIncreasingPath(int[][] matrix) {
        int ans=0;
        int m=matrix.length,n=matrix[0].length;
        int a[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                ans=Math.max(ans, dfs(matrix, i, j, Integer.MIN_VALUE, a));
            }
        }
        return ans;
    }
    public static int dfs(int [][]m,int i,int j,int prev,int a[][]){
        if(i<0 || j<0 || i>=m.length || j>=m[0].length)return 0;
        if(m[i][j]<=prev)return 0;
        if(a[i][j]>0)return a[i][j];
        int curr=m[i][j];
        int maxPath=1+(Math.max(dfs(m, i-1, j, curr, a),Math.max(dfs(m, i+1, j, curr, a), Math.max(dfs(m, i, j-1, curr, a), dfs(m, i, j+1, curr, a)))));
        a[i][j]=maxPath;
        return maxPath;
    }

    // 124. Binary Tree Maximum Path Sum
    private static int res;
    public static int maxPathSum(TreeNode root){
        res=root.val;
        dfs(root);
        return res;
    }
    public static int dfs(TreeNode root){
        if(root==null)return 0;
        int left=dfs(root.left);
        int right=dfs(root.right);
        left=Math.max(left, 0);
        right=Math.max(0, right);
        res=Math.max(res, root.val+left+right);

        return root.val+Math.max(left, right);
    }
     // 502. IPO
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int len = capital.length;
        List<int[]> projects = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            projects.add(new int[]{capital[i], profits[i]});
        }
        Collections.sort(projects, (p1, p2) -> p1[0] - p2[0]);
        int i=0;
        Queue<Integer>q=new PriorityQueue<>((x,y)->y-x);
        for(int j=0;j<Math.min(k,len);j++){
            while (i < len && projects.get(i)[0] <= w) {
                q.add(projects.get(i)[1]);
                i++;
            }
            if(q.isEmpty())break;
            w+=q.poll();

        }
        return w;
    }
    // 330. Patching Array
    public static int minPatches(int[] nums, int n) {
        long miss=1;
        int ans=0;
        int i=0;
        int len=nums.length;
        while(miss<=n){
            if(i<len && nums[i]<=miss){
                miss+=nums[i];
                i++;
            }else{
                miss*=2;
                ans++;
            }
        }
       return ans; 
    }
    // 1857. Largest Color Value in a Directed Graph
    public static int largestPathValue(String colors, int[][] edges) {
        HashMap<Integer,List<Integer>>g=new HashMap<>();
        for(int i[]:edges){
            g.putIfAbsent(i[0], new ArrayList<>());
            g.get(i[0]).add(i[1]);
        }
        int n=colors.length();
        int [][]map=new int[n][26];
        HashSet<Integer> vis=new HashSet<>();
        HashSet<Integer> path=new HashSet<>();
        int ans=0;
        for(int i=0;i<n;i++){
            ans=Math.max(ans, dfs(g, vis, path, map, colors, i));
          
            
        }
        
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    public static int dfs( HashMap<Integer,List<Integer>>g,HashSet<Integer> vis,HashSet<Integer> path,int [][]map,String colors,int curr){
        if(path.contains(curr))return Integer.MAX_VALUE;
        if(vis.contains(curr))return 0;
        vis.add(curr);
        path.add(curr);
        int colIdx=colors.charAt(curr)-'a';
        map[curr][colIdx]=1;
        if(g.get(curr)!=null){
            for(int nei:g.get(curr)){
                if(dfs(g, vis, path, map, colors, nei)==Integer.MAX_VALUE){
                  
                    return Integer.MAX_VALUE;}
                for(int i=0;i<26;i++){
                    int add=i==colIdx?1:0;
                    map[curr][i]=Math.max(map[curr][i],add+map[nei][i]);
                }
            }
        }
        path.remove(curr);
        int ans=0;
        for(int i=0;i<26;i++){
            ans=Math.max(ans, map[curr][i]);
        }
        return ans;
    }
    
    // 1392. Longest Happy Prefix

    public static String longestPrefix(String s) {
        int n=s.length();
        int dp[]=new int [n];  
        int i=1,len=0;
        while(i<n){
          if(s.charAt(i)==s.charAt(len)){
              dp[i]=++len;
              i++;
          }
          else{
              if(len>0){
                  len=dp[len-1];
              }else{
                  i++;
              }
          }
        }
        return s.substring(0,len);
      }
  

    public static void main(String[] args) {
       System.out.println();
       
    }  
}
