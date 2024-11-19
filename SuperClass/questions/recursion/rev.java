package questions.recursion;
import java.util.*;


public class rev {
public static void revStr(String str,int index){
    if (index<0){
        return;
    }
    System.out.print(str.charAt(index));
    revStr(str,index-1);
}


    public static void main(String arg[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the String :");
        String sr=sc.nextLine();
        System.out.print("The reverse of the string is : ");
        revStr(sr,sr.length()-1);
        sc.close();


    }
}
