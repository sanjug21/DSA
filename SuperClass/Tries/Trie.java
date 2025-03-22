import java.util.HashMap;

public class Trie {
    class Node {
         boolean end;
         
         HashMap<Character, Node> child = new HashMap<>();
    }

    private Node root;

    public Trie() {
         root = new Node();
         
    }
    public  void insert(String word) {
      Node curr=root;
      for(char c:word.toCharArray()){
        if(curr.child.containsKey(c)){
            curr = curr.child.get(c);
        }else{
            Node nn=new Node();
            
            curr.child.put(c,nn);
            curr=nn;
        }
      }
      curr.end=true;

        
    }
    public boolean search(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.child.containsKey(c)) {
                return false;
            }
            curr = curr.child.get(c);
        }
        return curr.end;
        
    }
    public boolean startsWith(String prefix) {
        Node curr = root;
        for (char c : prefix.toCharArray()) {
            if (!curr.child.containsKey(c)) {
                return false;
            }
            curr = curr.child.get(c);
        }
        return true;
    }
    

    public static void main(String[] args) {
         Trie t = new Trie();
         
         
    }
}