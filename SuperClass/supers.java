
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class supers {
    public static int pow(int n,int k){
       
        if(k==0)return 1;
        return n*pow(n,k-1);
    }
    public static void pd(int n){
       if(n==0)return;
        pd(n-1);
         System.out.print(n+" ");
        
    }
    public static int first(int a[],int k,int i){
        if(i==a.length)return -1;
        if(a[i]==k )return i;
        return first(a, k, ++i);
      
    }

    public static int fib(int n){
        if(n==1 || n==0)return n;
        return fib(n-1)+fib(n-2);   

    }
    public static int subSequence(String s,String ans){
        if(s.length()==0){
            
            return 1;
        }
        char c=s.charAt(0);
       
      
        return  subSequence(s.substring(1),ans)+  subSequence(s.substring(1),ans+c);
    }
    public static void toss(int n,String ans){
        if(ans.length()==n){
            System.out.println(ans);
            return;
        }
        if((ans.length()==0)||ans.charAt(ans.length()-1)!='H') toss(n,ans+"H");
        toss(n, ans+"T");
    }

    public static void dice(int i,int n,ArrayList<String> ans,String s){
        if( i>n)return;
        if( i==n){
            ans.add(s);
            return;
        }
        dice(i+1,n,ans,s+1);
        dice(i+2,n,ans,s+2);
        dice(i+3,n,ans,s+3);
        
    }
    public static void dicePath(int face,int n,int i,String s,ArrayList<String> ans){
        if(i>n)return;
        if(i==n){
            ans.add(s);
            return;
        }
        for(int curr=1;curr<=face;curr++){
            dicePath(face, n, i+curr, s+curr, ans);
        }
    }

    public static void allPaths(int a,int b,int c,int d,String s){
        if(a>=c || b>=d)return;
        if(a==c-1 && b==d-1){
            System.out.println(s);
            return;
        }
        allPaths(a+1, b, c, d, s+"H");
        allPaths(a, b+1, c, d, s+"V");
    }

    public static void permutation(String s,String ans){
        if(s.length()==0){
            System.out.println(ans);
            return;
        }
        boolean visited[]=new boolean[26];
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(visited[c-'a']==false){
                visited[c-'a']=true;
            String newStr=s.substring(0, i)+s.substring(i+1);
            permutation(newStr, ans+c);}
        }
    }
    public static void ratChaseCheese(int a[][],char ch[][],int cr,int cc,int R,int C) {
        if(cc==C-1 && cr==R-1){
            if(ch[cr][cc]!='X'){
                a[cr][cc]=1;
                for(int ar[]:a){
                    for(int i:ar){
                        System.out.print(i);
                    }
                    System.out.println();
                }
            }
            else{
                System.out.println("No Path Found");
            }
            return;
        }
        if(cc<0 ||cr<0||cc>=C||cr>=R || ch[cr][cc]=='X')return;
        a[cr][cc]=1;
        ch[cr][cc]='X';
        ratChaseCheese(a, ch, cr+1, cc, R, C);
        ratChaseCheese(a, ch, cr-1, cc, R, C);
        ratChaseCheese(a, ch, cr, cc+1, R, C);
        ratChaseCheese(a, ch, cr, cc-1, R, C);
        ch[cr][cc]='O';
        a[cr][cc]=0;
    }
   
    //sudoko solver
    // prime no
    public static boolean checkPrime(int n) {
        int div=2;
        while(div*div<=n){
            if(n%div==0)return false;
            div++;
        }
        return true;
    }
    public static void checkPrimeNo(int n){
        boolean[]prime=new boolean[n];
        prime[0]=true;
        prime[1]=true;
        for(int i=2;i*i<=n;i++){
            if(prime[i]==false){
                for(int j=2;i*j<n;j++){
                    prime[i*j]=true;
                }
            }
        }
        for(int i=2;i<n;i++){
            if(prime[i]==false)System.out.print(i+" ");
        }
    }

    public static int power(int a,int n){
        if(n==1)return a;
        int ans=pow(a, n/2);
        ans*=ans;
        if(n%2!=0)ans*=a;
        return ans;
    }
    public static void main(String []args){
        //random number generate
        int st=10, ed=100;
        Random rd=new Random();
        for(int i=0;i<10;i++){
            int val=rd.nextInt(ed-st+1)+st;
            System.out.println(val+" ");
        }


        //System.out.println(power(5, 10));


        //rat
        // // Scanner sc = new Scanner(System.in);
        // // int m=sc.nextInt();
        // // int n=sc.nextInt();
        // char ch[][]={{'O','X','O','O'},{'O','O','O','X'},{'X','O','X','O'},{'X','O','O','X'},{'X','X','O','O'}};
        // int m=5,n=4;
        // // char ch[][]=new char [m][n];
        // // for(int i=0;i<m;i++){
        // //     String s=sc.next();
        // //     for(int j=0;j<n;j++){
        // //         ch[i][j]=s.charAt(j);
        // //     }
        // // }
        // int ans[][]=new int[m][n];
        // ratChaseCheese(ans, ch, 0, 0, m, n);
        
   

        // n queen
    // int n=4;
    // char board[][]=new char[n][n];
    // for(char c[]:board)Arrays.fill(c, '.');
    //     print(board, n, 0);
    
    }

    //n queen
    public static void print(char board[][],int n,int row) {
        if(n==0){
            for(char c[]:board)System.out.println(Arrays.toString(c));
            System.out.println();
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
}


// quick sort avg and base case => n log(n)  worst case n*n  elment we correct 