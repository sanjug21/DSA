package questions.recursion;



public class occ {
 public static int first=-1;
 public static int last=-1;
 public static void occranceChar(String str, int idx, char element){
if (idx==str.length()){
    System.out.println("The first occurance is at "+first);
    System.out.println("The last occurance is at "+last);
    return;
}

    char current=str.charAt(idx);
    if (current==element){
        if (first==-1){
            first=idx+1;}
        else 
        last=idx+1;
        }
    
    occranceChar(str,idx+1,element);}
 
 
 

 public static void main(String arg[]){
String sc="aabchegeaadkda";
occranceChar(sc, 0, 'a');
 }
}
