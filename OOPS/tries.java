package OOPS;

public class tries {
    static class Node{
        Node[] child =new Node[26];
        boolean eow=false;
        public Node(){
            for(int i=0;i<26;i++){
                child[i]=null;
            }
        }
    }
    public static Node root=new Node();
    public static void insert(String data){
        int len=data.length();
        int idx=0;
        Node curr=root;
        for (int i=0;i<len;i++){
            idx=data.charAt(i)-'a';
            if(curr.child[idx]==null){
                curr.child[idx]=new Node();
            }
            curr=curr.child[idx];
        }
        curr.eow=true;
    }
    public static boolean search(String key){
        int len=key.length();
        int idx=0;
        Node curr=root;
        for(int i=0;i<len;i++){
            idx=key.charAt(i)-'a';
            Node node=curr.child[idx];
            if(node==null ){
                return false;
            }
            if(i==key.length()-1 && node.eow==false){
                return false;
            }
            curr=curr.child[idx];
        }
        return true;
    }
    public static boolean wordbreak(String key){
        if(key.length()==0){
            return true;
        }
        for (int i=1;i<key.length();i++){
            if(search(key.substring(0, i)) && 
            wordbreak(key.substring(i)) ){
                return true;
            }
        }
        return false;
    }

public static void main(String args [])
{
 String word[]={"the","a","their","there"};
 for (String str:word){
    insert(str);
 }
 String str="theatheir";
 System.out.println(search("ther"));
 System.out.println(wordbreak(str));
}
}
