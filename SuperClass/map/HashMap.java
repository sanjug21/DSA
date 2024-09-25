package map;
import java.util.*;
public class HashMap <K,V>{
    class Node{
        K key;
        V value;
        Node next;
    }
    ArrayList<Node> bkt;
    private int size=0;
    public HashMap(){
        this(4);
    }
    public HashMap(int n){
        bkt=new ArrayList<>();
        while(n--!=0)bkt.add(null);
    }
    public void put(K key,V val){
        int idx=hashFun(key);
        Node tmp=bkt.get(idx);
        while(tmp!=null){
            if(tmp.key.equals(key)){
                tmp.value=val;
                return;
            }
            tmp=tmp.next;
        }
        tmp=bkt.get(idx);
        Node nn= new Node();
        nn.key=key;
        nn.value=val;
        nn.next=tmp;
        bkt.set(idx, nn);
        size++;
    }
    public boolean containsKey(K key){
        int idx=hashFun(key);
        Node tmp=bkt.get(idx);
        while(tmp!=null){
            if(tmp.key.equals(key))return true; 
            tmp=tmp.next;
        }
        return false;
    }
    public int hashFun(K key){
        int bn=key.hashCode()%bkt.size();
        if(bn<0)bn+=bkt.size();
        return bn;
    }
}
