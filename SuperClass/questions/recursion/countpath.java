package questions.recursion;

public class countpath {
public static int countPath(int a,int b,int c,int d){
    if ((a==c-1)||(b==d-1))
    {
        return 1;
    }
return countPath(a+1,b,c,d) + countPath(a,b+1,c,d);

}
    public static void main(String args[]){
        int m=3,n=5;
        System.out.println(countPath(0,0,m,n));
    }
}
