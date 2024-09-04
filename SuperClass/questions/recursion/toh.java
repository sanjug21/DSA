package questions.recursion;
import java.util.*;

public class toh{
public static void toH (int n, String src, String help, String dest){
if(n==0){
  // System.out.println("transfer disk "+n+" from "+src+" to "+dest);
    return;
}
toH(n-1,src,dest,help);
System.out.println("transer disk "+n+" from "+src+" to "+dest);
toH(n-1,help,src,dest);
}

public static void main(String Args[]){
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
toH(n,"S","H","D");
sc.close();
}

}