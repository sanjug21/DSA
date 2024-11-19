package LeetCode;
import java.util.*;

public class leet {
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
    //question min changes
    public static int count(String s,Character curr){
        int count=0;
        for(int i=1;i<s.length();i++){
            Character temp=s.charAt(i);
            if(curr==temp){
                count++;
                curr=curr=='0'?'1':'0';
            }
            else{
                curr=temp;
            }
            }
            return count;
    }

    //question max count after splitting
 public static int count(Character c,String r){
  System.out.println(r);
  int count=0;
  for(int i=0;i<r.length();i++){
      if(r.charAt(i)==c)count++;
  }
  System.out.println(count);
  return count;
}


// permutation all question
public static void sort(int a[],int st,int ed){
    for(int i=st;i<ed;i++){
                     int key=i;
                     for(int j=i+1;j<ed;j++){
                         if(a[j]<a[key]){key=j;
                         
                     }}
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
public static int fact(int no){
    if(no==0)return 1;
    return no*fact(no-1);
}

public static int duplicate(int a []){
HashMap<Integer,Integer> map=new HashMap<>();
int mul=1;
for(int i=0;i<a.length;i++){
    if(!map.containsKey(a[i])){
        map.put(a[i], 1);
    }else{
        map.put(a[i], map.get(a[i])+1);
    }
    }
    for(int i:map.keySet()){
        mul*=fact(map.get(i));
    }
    return mul;
}

    public static ArrayList<Integer> convert(int arr[]){
        ArrayList<Integer> con=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
          con.add(arr[i]);
        }
        return con;
    }

    public static String arToStr (int a[]){
        String s="";
        for(int i=0;i<a.length;i++){
            s+=a[i];
        }
        return s;
    }

    // subset question
      @SuppressWarnings("unused")
    private static void result(int a[],List<List<Integer>> ans,List<Integer> list, int st,int ed){
        if(!ans.contains(new ArrayList<>(list))){
        ans.add(new ArrayList<>(list));
        }
       
        for(int i=st;i<ed;i++){
          list.add(a[i]);
          result(a, ans, list, i+1, ed);
          list.remove(list.size()-1);
        }
       
    }

 

    //n queen
      public static  ArrayList<String> convert(String arr[][],int n){
        ArrayList<String> con=new ArrayList<>();
       
        for(int i=0;i<n;i++){
           String s="";
           for(int j=0;j<n;j++){
               s+=arr[i][j];
           }
           con.add(s);
        }
        return con;
    }
    public static  boolean valid(int row,int col,String sol[][]){
        int n=sol.length;
        for(int i=0;i<row;i++){
            if(sol[i][col]=="Q")return false;
            int cc=col-row+i;
            if(cc>=0&&sol[i][cc]=="Q")return false;
            cc=col+row-i;
            if(cc<n&& sol[i][cc]=="Q")return false;
        }
        return true;
    }
    public static void helper(List<List<String>> ans,int t,int row,int col,String[][] sol){
        if(row==t)return;
        if(valid(row,col,sol)){
            sol[row][col]="Q";
            if(row==t-1)ans.add(convert(sol,t));
         
            else for(int i=0;i<t;i++)helper(ans,t,row+1,i,sol);
            sol[row][col]=".";

        }
    }

    //sudoku
    public  static boolean isValidSudoku(char[][] board,char item) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] != '.') {
					

					// for row
					for (int i = 0; i < 9; i++) {
						if (i != row && board[i][col] == item)
							return false;
					}

					// for col
					for (int i = 0; i < 9; i++) {
						if (i != col && board[row][i] == item)
							return false;
					}

					// for 3*3 matrix
					int i = row - row % 3;
					int j = col - col % 3;
					for (int a = i; a < i + 3; a++) {
						for (int b = j; b < j + 3; b++) {
							if ((a != row && b != col) && board[a][b] == item) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

    //
    public static void set(int [][]matrix,int a,int l,boolean b){
        if(b)
       { for(int i=0;i<l;i++){
            matrix[a][i]=0;
        }}
        else{
             for(int i=0;i<l;i++){
            matrix[i][a]=0;
        }
        }
    }
    public static boolean isPal(String s){
        int j=s.length()-1;
        int i=0;
        while(i<j){
            if(s.charAt(i++)!=s.charAt(j--))return false;
        }
        return true;
    }

    // //merge Linked List
    // public ListNode mergeKLists(ListNode[] lists) {
    //     if(lists.length==0)return null;
    //     return mergeLists(lists,0,lists.length-1);
    // }
    //  public ListNode mergeLists(ListNode[] lists,int start,int end){
    //     if(start==end)return lists[start];
    //     if(start+1==end)return merge(lists[start],lists[end]);
    //     int mid=start+(end-start)/2;
    //     ListNode left=mergeLists(lists,start,mid);
    //     ListNode right=mergeLists(lists,mid+1,end);
    //     return merge(left,right);
    // }
    // public ListNode merge(ListNode l1,ListNode l2){
    //     ListNode temp=new ListNode(0);
    //     ListNode curr=temp;
    //     while(l1!=null && l2!=null){
    //         if(l1.val<l2.val){
    //             curr.next=l1;
    //             l1=l1.next;
    //         }else{
    //             curr.next=l2;
    //             l2=l2.next;
    //         }
    //         curr=curr.next;
    //     }
    //     curr.next=(l1!=null)?l1:l2;
    //     return temp.next;
    // }
        // permutation of anagrams        
        public static int duplicate(String st){
        HashMap<Character,Integer> map=new HashMap<>();
        int mul=1;
        for(int i=0;i<st.length();i++){
            char c=st.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, 1);
            }else{
                map.put(c, map.get(c)+1);
            }
            }
            for(char i:map.keySet()){
                mul*=fact(map.get(i));
            }
            return mul;
        }

        // generate paranthesis

        public static void generate(int open,int close,int k,List<String> ans,String s){
            if(open == close && open==k){  
                ans.add(s);
                return;
            }
            if(open<k){  
                generate(open+1, close, k, ans, s+"(");
            }
            if(close<open){
                generate(open, close+1, k, ans, s+")");
            }
        }
      
        // combination

        public static void combination(int st,int ed,int k,List<List<Integer>> ans,List<Integer> temp){
            if(temp.size()==k){
                ans.add(new ArrayList<>(temp));
                return;
            }
            for(int i=st;i<=ed;i++){
                temp.add(i);
                combination(i+1,ed, k, ans, temp);
                temp.remove(temp.size()-1);
            }
        }

    // combination Sum
    public static void combinationSum(int i,int a[],int sum,int target,List<Integer>temp,List<List<Integer>> ans){
        if(sum==target){
            if(!ans.contains(temp))
           { ans.add(new ArrayList<>(temp));
            return;}
        }
        if(sum>target || a.length==i)return;
        temp.add(a[i]);
        combinationSum(i, a, sum+a[i], target, temp, ans);
        temp.remove(temp.size()-1);
        combinationSum(i+1, a, sum, target, temp, ans);
    }

    //combination sum2
     public static void combinationSum2(int st,int a[],int target,List<Integer>temp,List<List<Integer>> ans){
        if(target==0){
       ans.add(new ArrayList<>(temp));
            return;
        }
       
        for(int i=st;i<a.length;i++){
        if(i>st && a[i]==a[i-1])continue;
        if(a[i]>target)break; 
        temp.add(a[i]);
         combinationSum2(i+1, a,  target-a[i], temp, ans);
        temp.remove(temp.size()-1);
        }
       
    }
    //combination sum3
    public void combinationSum3(int []arr,int idx,int k,int sum,List<Integer> temp,List<List<Integer>> ans){
        if(k==0 && sum==0)ans.add(new ArrayList<>(temp));
        if(idx>=arr.length || k<=0 || sum<=0)return;
        temp.add(arr[idx]);
        combinationSum3(arr,idx+1,k-1,sum-arr[idx],temp,ans);
        temp.remove(temp.size()-1);
        combinationSum3(arr,idx+1,k,sum,temp,ans);
        }
    // permutation string
    public static void combO(String a,String b,ArrayList<String> ls){
        if(a.length() == 0) {
         ls.add(b);
         return;
        }
       
        for(int i=0; i<a.length(); i++) {
            char currChar = a.charAt(i);
            String newStr = a.substring(0, i) + a.substring(i+1);
            combO(newStr, b+currChar,ls);
        }
        
    }
    static int minVal=Integer.MAX_VALUE;
    public static void minPath(int sum,int grid[][],int i,int j){
        if (i >= grid.length || j >= grid[0].length) return;
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            minVal = Math.min(minVal, sum + grid[i][j]);
            return;
        }
        
        //System.out.println(sum+" "+i+" "+j);
        minPath(sum+grid[i][j],grid,i+1,j);
        minPath(sum+grid[i][j],grid,i,j+1);
        
    }


    //Letter Combinations of a Phone Number

    public static void numComb(HashMap<Character,String> map,String s,String ans){
        if(s.length()==0){
            System.out.println(ans);
            return;
        }
       String press=map.get(s.charAt(0));
       for(int i=0;i<press.length();i++){
        numComb(map, s.substring(1), ans+press.charAt(i));
       }

    }

    //or 
    static String []map={"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static void letterCombination(String ques,String ans){
        if(ques.length()==0){
            System.out.println(ans);
            return;
        }
        char ch=ques.charAt(0);
        String pressed=map[ch-'0'];
        for(int i=0;i<pressed.length();i++){
            letterCombination(ques.substring(1), ans+pressed.charAt(i));
        }
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // Maximum is i (1^2 + 1^2 + ... + 1^2)
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // lexographical
    public static void counting(int curr,int n,List<Integer>  ans){
        if(curr>n)return;
        ans.add(curr);
       
        for(int i=0;i<10;i++){
            if(curr*10+i<=n){
            counting(curr*10+i, n,ans);
            }
            else break;
        }

    }
    //410 
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
    //is subsequence
    public static boolean isSubsequence(String s,String t){
        if(s.length()==0)return true;
        if(t.length()==0)return false;
        boolean b=s.charAt(0)==t.charAt(0);
        if(b)b=isSubsequence(s.substring(1), t.substring(1));
        else b=isSubsequence(s, t.substring(1));
        return b;

        // char ch[]=s.toCharArray();
        // for(int i:ch){
        //     if(t.indexOf(i)!= -1){
        //         int idx=t.indexOf(i);
        //         t=t.substring(idx+1);
        //     }
        //     else return false;
        // }
        // return true;
    }
    public static void sudokuSolver(int grid[][],int r,int c){
        if(c==9){
            r++;
            c=0;
        }
        if(r==9){
            display(grid);
            return;
        }
        if(grid[r][c]!=0)sudokuSolver(grid, r, c+1);
        else {
            for(int i=1;i<=9;i++){
                if(isValid(grid,r,c,i)){
                    grid[r][c]=i;
                    sudokuSolver(grid, r, c+1);
                    grid[r][c]=0;
                }
            }
        }
    }
    public static boolean isValid(int grid[][],int r,int c,int ch){
        for(int i=0;i<9;i++){
            if(i!=r && grid[i][c]==ch)return false;
            if(i!=c && grid[r][i]==ch)return false;
        }
        int i=r-r%3;
        int j=c-c%3;
        for (int a = i; a < i + 3; a++) {
            for (int b = j; b < j + 3; b++) {
                if( grid[a][b]==ch)return false;
            }
        }
        return true;
    }
    public static void display(int grid[][]){
        for(int c[]:grid)System.out.println(Arrays.toString(c));
    }

    public static boolean check(char c){
        if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u')return true;
        return false;
    }
    //735
    public static int[] convert(Stack<Integer> stack) {
        int[] arr = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            arr[i] = stack.pop();
        }
        return arr;
    }
     public static void main(String[] args) {


        
    //     // 1750
    //  String s="abbbbbbbbbbbba";
    //  int j=s.length()-1;
    //  int i=0;
    //  while(i<j){
    //      char a=s.charAt(i);
    //      char b=s.charAt(j);
    //      if(a==b){
    //         i++;
    //         j--;
    //          while(a==s.charAt(i) && i<=j)i++;
    //          while(b==s.charAt(j) && i<=j)j--;
    //      }
    //      else break;
    //  }
    //  System.out.println(j-i+1);


        // //735
        // int []asteroids={10,2,-5};
        // Stack <Integer> st=new Stack<>();
        // for(int i:asteroids){
        //     if(st.isEmpty()|| i>0)st.push(i);
        //     else{
        //         while(!st.isEmpty() && st.peek()<(-i) && st.peek()>0)st.pop();
        //         if(st.isEmpty()|| st.peek()<0)st.push(i);
        //         else if(st.peek() ==-i)st.pop();
        //     }
        // }

        // System.out.println(st);


        // // 1456
        // String s="tryhard";
        // int k=1;
        // int n=s.length();
        // int dp[]=new int[n];
        // for(int i=0;i<n;i++)if(check(s.charAt(i)))dp[i]=1;
        // // System.out.println(Arrays.toString(dp));
        // int max=0;
        // int sum=0,idx=0;
        // for(int i=0;i<n;i++){
        //     if(i<k){
        //         sum+=dp[i];
        //          max=Math.max(max,sum);
        //         }
        //     else {
        //         sum=sum+dp[i]-dp[idx++];
        //         max=Math.max(max,sum);
        //         }
        // }
        // System.out.println(max);


        // sudoku
        // int[][] grid = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
		// 		         { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
		// 		         { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
        // sudokuSolver(grid, 0, 0);

        
        // is subsequence
        // String s="abc",t="ahbgdc";
        // System.out.println(isSubsequence(s, t));

        // //410
        // int nums[]={7,2,5,10,8};
        // int k=2;
        // int l=0,r=0;
        // for(int i:nums){
        //     if(i>l)l=i;
        //     r+=i;
        // }
        // int ans=r;
        // while(l<=r){
        //     int mid=(l+r)/2;
        //     if(check(nums,k,mid)){
        //         ans=mid;
        //         r=mid-1;
        //     }else l=mid+1;

        // }
        // System.out.println(ans);

        //1481
        // int arr[]={5,5,4};
        // int k=1;
        // int n=arr.length;
        
        // HashMap<Integer,Integer>map =new HashMap<>();
        // for(int i:arr){
        //     if(map.containsKey(i))map.put(i,map.get(i)+1);
        //     else map.put(i,1);
        // }
        // int freq[]=new int [n+1];
        // int ans=map.size();
        // for(int i:map.values()){
        //     freq[i]++;
        // }
        // System.out.println(Arrays.toString(freq));
        // for(int i=1;i<=n;i++){
        //     int remove=freq[i];
        //     if(k>=i*remove){
        //         k-=i*remove;
        //         ans-=remove;
        //     }
        //     else{
        //         remove=k/i;
        //         ans-=remove;
        //         break;
        //     }
        // }
        // System.out.println(ans);

        // int nums[]={5,5,10};
        // Arrays.sort(nums);
        // int n=nums.length-1;
        // int a[]=new int[n];
        // a[0]=nums[0];
        // long ans=0;
        // for(int i=1;i<n;i++)a[i]=a[i-1]+nums[i];
        // for(int i=n;i>1;i--){
        //     if(nums[i]<a[i-1]){
        //         System.out.println(nums[i]+a[i-1]);
        //         break;
        //     }
        // }
        // System.out.println(Arrays.toString(a));

        // //lexographical
        // int n=100;
        // List<Integer>  ans=new ArrayList<>();
        // for(int i=1;i<10;i++){
        //     counting(i,n,ans);
        // }

    //     //uniquepath 2
    //     int [][]obstacleGrid={{0,0},{1,1},{0,0}};
    //     int m=obstacleGrid.length,n=obstacleGrid[0].length;
    //     if(obstacleGrid[m-1][n-1]==1)System.out.println(0);;
    //     int ans[]=new int[n];
    //     ans[n-1]=1;
    //     for(int i=n-2;i>=0;i--){
    //         if(obstacleGrid[m-1][i]==0)ans[i]+=ans[i+1];
    //     }
    //     System.out.println(Arrays.toString(ans));
    //     for(int i=m-2;i>=0;i--){
    //         int temp[]=new int[n];
    //         if(obstacleGrid[i][n-1]==0)temp[n-1]=ans[n-1];
    //         for(int j=n-2;j>=0;j--){
    //             if(obstacleGrid[i][j]==0)temp[j]=temp[j+1]+ans[j];
    //         }
    //         ans=temp;
    //         System.out.println(Arrays.toString(ans));
    //     }
    //     System.out.println(ans[0]);
    //    // System.out.println(Arrays.toString(ans));


        // // palindrome substring
        // String s="abc";
        // int n=s.length();
        // int odd=0;
        // int even=0;
        // for(int ax=0;ax<n;ax++){
        //     for(int or=0;ax-or>=0 && ax+or<n;or++){
        //         if(s.charAt(ax-or)!=s.charAt(ax+or))break;
        //         odd++;
        //     }
        // }
        // for(double ax=0.5;ax<n;ax++){
        //     for(double or=0.5;ax-or>=0 && ax+or<n;or++){
        //         if(s.charAt((int)(ax-or))!=s.charAt((int)(ax+or)))break;
        //         even++;
        //     }
        // }
        // System.out.println(odd+even);

        // //perfect square
        // int n=13;
        // System.out.println(numSquares(n));
       


    //     //451 sort character by frequency
    //     String s="cccaaa"; 
    //     char ch[]=s.toCharArray();
    //     int freq[]=new int[128];
    //     for(char c:ch)freq[c]++;
    //     for(int i=0;i<ch.length;){
    //         char cmax=',';
    //         for(int j=0;j<128;j++)if(freq[j]>freq[cmax])cmax=(char)j;
    //         while(freq[cmax]!=0){
    //             ch[i++]=cmax;
    //             freq[cmax]--;
    //         }
    //     }
    //    System.out.println(Arrays.toString(ch));


       
    //     //1383 max performance
    //     int n=3;
    //     int k=2;
    //     int speed[]={2,8,2};
    //     int efficiency[]={2,7,1};
    //     PriorityQueue<Integer> pq=new PriorityQueue<>(k);
    //     long ans=0;
    //     long spd=0;
    //     int play[][]=new int [n][2];
    //     for(int i=0;i<n;i++){
    //         play[i][0]=efficiency[i];
    //         play[i][1]=speed[i];}
        
    //     Arrays.sort(play,(p1,p2)->(p2[0]-p1[0]));

    //       for(int i=0;i<n;i++){
    //           if(pq.size()>=k)spd-=pq.poll();
    //           pq.add(play[i][1]);
    //           spd+=play[i][1];
    //           ans=Math.max(ans,spd*play[i][0]);
    //       }
        
    //    System.out.println(ans);

        //Letter Combinations of a Phone Number
      //  letterCombination("23", "")


        // or
//         String s="23";
//         HashMap <Character,String> map=new HashMap<>();
//         map.put('2',"abc");
//         map.put('3',"def");
//         map.put('4',"ghi");
//         map.put('5',"jkl");
//         map.put('6',"mno");
//         map.put('7',"pqrs");
//         map.put('8',"tuv");
//         map.put('9',"wxyz");
//   numComb(map, s, "");
//     System.out.println(map.get('2'));


        //coin 2
        // int coins[]={1,2,5};
        // int amt=5;

        // int n=coins.length;
        // int a[][]=new int[n][amt+1];
        // for(int c=1;c<=amt;c++){
        //     if(c%coins[n-1]==0)a[n-1][c]+=a[n-1][c-coins[n-1]]+1;
        // }
        // for(int r=n-2;r>=0;r--){
        //     for(int c=1;c<=amt;c++){
        //         if(c%coins[r]==0){
                   
        //         }
                
        //     }
        // }



        // for(int []p:a){
        //     System.out.println(Arrays.toString(p));
        // }

        //Unique PAth 2
    //     int obstacleGrid[][]={{0,0,0},{0,1,0},{0,0,0}};
    //     int m=obstacleGrid.length;
    //     int n=obstacleGrid[0].length;
    //     int ans[]=new int [n]; 
    //     int obs=1;
    //     for(int i=n-1;i>=0;i--){
    //         if(obstacleGrid[m-1][i]==1){
    //             obs=0;
    //         }
    //         ans[i]=obs;
    //     }
    //    //System.out.println(Arrays.toString(ans));
    //     for(int i=m-2;i>=0;i--){
    //         int temp[]=new int [n];
    //         if(obstacleGrid[i][n-1]==1)temp[n-1]=0;
    //         else temp[n-1]=1;
    //         for(int j=n-2;j>=0;j--){
    //             if(obstacleGrid[i][j]==1)temp[j]=0;
    //             else temp[j]=ans[j]+temp[j+1];
    //         }
    //         ans=temp;
    //         System.out.println(Arrays.toString(ans));
    //     }
    //     System.out.println(ans[0]);
     

    //   //2482
    //   int grid[][]={{0,1,1},{1,0,1},{0,0,1}};
    //     int row=grid.length;
    //     int col=grid[0].length;
    //     int oneRow[]=new int [row];
    //     int zeroRow[]=new int[row];
    //     int oneCol[]=new int[col];
    //     int zeroCol[]=new int[col];
    //     for(int i=0;i<row;i++){
    //         int zero =0;
    //         int one=0;
    //         for(int j=0;j<col;j++){
    //             if(grid[i][j]==1)one++;
    //             else zero++;
    //         }
    //         oneRow[i]=one;
    //         zeroRow[i]=zero;
    //     }
    //     for(int i=0;i<col;i++){
    //         int zero =0;
    //         int one=0;
    //         for(int j=0;j<row;j++){
    //             if(grid[j][i]==1)one++;
    //             else zero++;
    //         }
    //         oneCol[i]=one;
    //         zeroCol[i]=zero;
    //     }
    //     for(int i=0;i<row;i++){
    //         for(int j=0;j<col;j++){
    //             grid[i][j]=oneRow[i]+oneCol[j]-zeroRow[i]-zeroCol[j];
    //         }}
        
    //         for(int a[]:grid){
    //             System.out.println(Arrays.toString(a));
    //         }


        // String contain any permutation
        // String s="abc";
        // String ch="adbc";
        // ArrayList<String> ls=new ArrayList<>();
        // combO(s, "",ls);
        // for(String temp:ls){
        //     if(ch.contains(temp))System.out.println(true);
        // }
        // System.out.println(false);


        // to find the kth permutation
        // int n=3;
        // int k=1;
        // int a[]=new int[n];
        // for(int i=1;i<=n;i++){
        //     a[i-1]=i;
        // }
        // if(k==1)System.out.println(arToStr(a));
        // for(int j=1;j<k;j++){
        // for(int i=n-1;i>=1;i--){
        // if(a[i]>a[i-1]){
        // sort(a,i,n);
        // replace(a,i-1,i,n);
        // break;
        // }
        // }
        // }
        
        // System.out.println(arToStr(a));
       
        // //combination Sum
        // int a[]={10,1,2,7,6,1,5};
        // Arrays.sort(a);
        // List<List<Integer>> ans=new ArrayList<>();
        // combination(0, 0, 0, ans,new ArrayList<>());
        // combinationSum2(0, a,8, new ArrayList<>(), ans);
        // System.out.println(ans);


        // // Combination
        // int n=4;
        // int k=2;
        // List<List<Integer>> ans=new ArrayList<>();
        // combination(1, n, k, ans,new ArrayList<>());
        // System.out.println(ans);


        
        // Generate Paranthesis
        // List<String> ans=new ArrayList();
       
        // generate(0, 0, 3, ans,"");
        // System.out.println(ans);



        // unsolved
        // String s="b okzojaporykbmq tybq zrztwlolvcyumcsq jjuowpp";
        // s.trim();
        // String []st=s.split(" ");
        // int ans=1;
        // for(String i:st){
        //     System.out.println(ans);
        //     ans*=fact(i.length())/duplicate(i);
        // }
        // System.out.println(ans);
        

        //array into 2D Array
        // int nums[]={1,2,4,1,2,3,1};
        // HashMap<Integer,Integer> map=new HashMap<>();
        // List<List<Integer>> ans=new ArrayList<>();
        // for(int i:nums ){
        //    map.put(i, map.getOrDefault(i, 0)+1);
        // }
    
        // while( !map.isEmpty()){
        //     List<Integer> temp = new ArrayList<>();
        //     Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        //     while (iterator.hasNext()) {
        //         Map.Entry<Integer, Integer> entry = iterator.next();
        //         temp.add(entry.getKey());
        //         entry.setValue(entry.getValue() - 1);
        //         if (entry.getValue() == 0) {
        //             iterator.remove();
        //         }
        //     }
        //     ans.add(temp);
        // }

        // or
        // List<List<Integer>> list = new ArrayList<>();
        // int i=0;
        // int[] arr = new int[nums.length+1];
        // for(int f: nums){
        //     arr[f]++;
        // }
        // for(int curr : arr){
        //     i = Math.max(i, curr);
        // }
        // for(int j=0; j<i; j++){
        //     list.add(new ArrayList<>());
        // }
        // for(int f : nums){
        //     for(int j=0; j<i;j++){
        //         if(!list.get(j).contains(f)){
        //             list.get(j).add(f);
        //             break;
        //         }
                
        //     }
        // }
    
        // System.out.println(ans);
    
    
        

        // //binary
        // int a[]={1,0,1};
        // ArrayList<Boolean> ans=new ArrayList<>();
        // int val=0;
        // for(int i:a){
        //     val=((val*2)+i)%5;
        //     ans.add(val==0);
        // }
        // System.out.println(ans);

        // int nums[]={1,1,1,0,1};
        // ArrayList<Boolean> ans=new ArrayList<>();
        // int val=0;
        // for(int i=0;i<nums.length;i++){
        //     val+=nums[i];
        //     System.out.println(val);
        //     if(binary(val)%5==0)ans.add(true);
        //     else ans.add(false);
        //     val*=10;

        // }
        // System.out.println(ans);



    // String s="bbbbb";
    // int ans=0;
    // int n=s.length();
    // for(int i=0;i<n;i++){
    //     int k=n;
    //     while(i<k){
    //         System.out.println(s.substring(i, k));
    //         if(isPal(s.substring(i,k)))ans=Math.max(ans,k-i);
    //         k--;
    //     }
    // }
    // System.out.println(ans);

    // String s="abcdefg";
    // int k=2;
    // char ch[]=s.toCharArray();
       
    
    // int f=0;
    // int l=0;
    // for(int i=0;i<ch.length;i+=2*k){
    // f=i;
    // l=i+k-1;
    // while(f<l){
    //     char temp=ch[f];
    //     ch[f]=ch[l];
    //     ch[l]=temp;
    //     f++;
    //     l--;
    // }}
    // String ans = new String(ch);
    // System.out.println(ans);
    //150
// String tokens[]={"4","13","5","/","+"};
//  Stack<String> s=new Stack<>();
//         String str="";
//         for(String temp:tokens){
//             if(temp=="+"){
//                 str=Integer.toString(Integer.parseInt(s.pop())+Integer.parseInt(s.pop()));
//             }
//             else if(temp=="*"){
//                 str=Integer.toString(Integer.parseInt(s.pop())*Integer.parseInt(s.pop()));
//             }
//             else if(temp=="-"){
//                 String x=s.pop();
//                 str=Integer.toString(Integer.parseInt(s.pop())-Integer.parseInt(x));
//             }
//              else if(temp=="/"){
//                 String x=s.pop();
//                 str=Integer.toString(Integer.parseInt(s.pop())/Integer.parseInt(x));
//             }
//             else str=temp;
//             s.push(str);

           
//         }
//         int res=Integer.parseInt(str);
   
//     System.out.println(res);
   

    // set 0 matrix
//   int matrix[][]={{0,1,2,0},{3,4,5,2},{1,3,1,5}};
//   int n=matrix.length;
//   int m=matrix[0].length;
//   ArrayList<Integer> r=new ArrayList<>();
//   ArrayList<Integer> c=new ArrayList<>();
//   for(int i=0;i<n;i++){
//       for(int j=0;j<m;j++){
//         //System.out.println(matrix[i][j]);
//           if(matrix[i][j]==0){
//             if(!r.contains(i))r.add(i);
//               if(!c.contains(j))c.add(j);
              
//           }
//       }
//   }
// for(int i:r){
//   set(matrix,i,m,true);
//   //System.out.println(i);
// }
// for(int i:c){
//   set(matrix,i,n,false);
//  // System.out.println(i);
// }
// for(int []i:matrix)
// {System.out.println(Arrays.toString(i));}


   

    // // maximal square
    // char ch[][]={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1',},{'1','0','0','1','0'}};
    // int r=ch.length,c=ch[0].length;
    // int min=Math.min(r,c);
    // boolean b=false;
    // if(min==r)b=true;
    // if(b){
    //     c-=min;
    // }else r-=min;
    
    // System.out.println(c+"+"+r);
    


//     //angrams
// String s[]={""};
//    HashMap<String, List<String>>map=new HashMap<>();
//    for(String temp:s){
//     char []ch=temp.toCharArray();
//     Arrays.sort(ch);
//     String sorted=new String(ch);
//     if(!map.containsKey(sorted)){
//         map.put(sorted, new ArrayList<>());
//     }
//     map.get(sorted).add(temp);
//    }
//    for(String st:map.keySet())
//    {System.out.println(map.get(st));}


    
    // // min time to aceive alternate ballon 1578
    // String colors="abaac";
    // int []time={1,2,3,4,5};
    // int n=colors.length();
    // int count=0;
    // char[] ch=colors.toCharArray();
    // int curr=time[0];
    // for(int i=1;i<n;i++){
    //     if(ch[i]!=ch[i-1]){
    //         curr=time[i];
    //     }else{
    //         count+=Math.min(curr, time[i]);
    //         curr=Math.max(curr, time[i]);
    //     }
    //     }
    //     System.out.println(count);
    
   



    // // n queen
    // List<List<String>> ans=new ArrayList<>();
    // int n=4;
    // String queen[][]=new String[n][n];
    // for(String[] row: queen){
    //     Arrays.fill(row,".");
    // }
    // for(int i=0;i<n;i++)helper(ans,n,0,i,queen);
    // System.out.println(ans);
    
       // //subsets
    // int a[]={1,2,2};
    //Arrays.sort(a);
    // List<List<Integer>> ans=new ArrayList<>();
    // List<Integer> list=new ArrayList<>();
    // result(a, ans, list, 0, a.length);
    // System.out.println(ans);
   


// //question next permutation
// int a[]={1,1,2};
// int l=a.length;
// int loop=(fact(l)/duplicate(a)-1);
// System.out.println(loop);

// for(int j=0;j<loop;j++)
// {for(int i=l-1;i>=1;i--){
//     if(a[i]>a[i-1]){
//         sort(a,i,l);
//         replace(a,i-1,i,l);
//         System.out.println(Arrays.toString(a));
//         break;
//     }
//     else if(i==1){
//         Arrays.sort(a);
//         System.out.println(Arrays.toString(a));
//     }
// }}

//question max count after splitting
//  String s="001";
//  int l=s.length()-1;
//  int ans=0;
//  int sum=0;
//  for(int i=0;i<l;i++){
//      sum=count('0',s.substring(0,i+1))+count('1',s.substring(i+1,l+1));
//      ans=Math.max(sum,ans);
//      //System.out.println(ans);
//  }

//question min changes
// String s="10010100";
// Character cur=s.charAt(0);
// int c1=count(s,cur);
// int c2=count(s,cur=='0'?'1':'0')+1;
//         System.out.println(Math.min(c1,c2));

} 
}