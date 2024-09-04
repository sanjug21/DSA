
import java.util.*;

public class test {
    public static void insertBottom(Stack<Integer> st,int n){
        if(st.isEmpty()){
            st.push(n);
            return;
        }
        int item=st.pop();
        insertBottom(st, n);
        st.push(item);

    }
    public static void reverse(Stack<Integer> st){
        if(st.isEmpty())return;
        int item=st.pop();
        reverse(st);
        insertBottom(st, item);

    }
    static class Trie{
        Trie []children;
        boolean eow;
        Trie(){
            children=new Trie[26];
            eow=false;
        }
    }
    public static Trie root=new Trie();
 
    public static void main(String[] args) {
        // Stack<Integer> st=new Stack<>();
        // st.push(10);
        // st.push(20);
        // st.push(30);
        // st.push(40);
        // reverse(st);
        // System.out.println(st);
        int nums[]={1,1,1,0,0,0,0,};
        int diff=0;
        int ans=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0, -1);
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0)diff--;
            else diff++;
            if(map.containsKey(diff))ans=Math.max(ans, i-map.get(diff));
            else map.put(diff, i);
        }
        System.out.println(ans);










        
        }
        public static void insert(String word){
            Trie node=root;
            for(char c:word.toCharArray()){
                int i=c-'a';
                if(node.children[i]==null){
                    node.children[i]=new Trie();
                }
                node=node.children[i];
            }
            node.eow=true;
        }
        public static String search(String word){
            Trie node=root;
            int i=0;
            for(char c:word.toCharArray()){
                int idx=c-'a';
                i++;
                if(node.children[idx]==null)return word;
                else if(node.children[idx].eow)return word.substring(0, i);
                else node=node.children[idx];
            }
            return word;
        }
      
}

