package questions;
import java.util.*;
class ratChases {
    public static boolean t;
    public static void main(String args[]) {
        t=true;
        Scanner sc = new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        char ch[][]=new char [m][n];
        for(int i=0;i<m;i++){
            String s=sc.next();
            ch[i]=s.toCharArray();
        }
        int ans[][]=new int[m][n];
        ratChaseCheese(ans,ch,0,0,m,n);
        if(t)System.out.println("NO PATH FOUND");
        sc.close();
        
    }
    public static void ratChaseCheese(int a[][],char ch[][],int cr,int cc,int R,int C) {
        if(cc==C-1 && cr==R-1){
            if(ch[cr][cc]!='X'){
                t=false;
                a[cr][cc]=1;
                for(int ar[]:a){
                    for(int i:ar){
                        System.out.print(i+" ");
                    }
                    System.out.println();
                }
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
}