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
        double lf=(1.0*size)/bkt.size();
        double thf=2.0;
        if(lf>thf)rehashing();
    }
    private void rehashing(){
        ArrayList<Node>newBkt=new ArrayList<>();
        for(int i=0;i<bkt.size()*2;i++)newBkt.add(null);
        ArrayList<Node>oldBkt=bkt;
        bkt=newBkt;
        size=0;
        for(Node n:oldBkt){
            while(n!=null){
                put(n.key, n.value);
                n=n.next;
            }
        }
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
    public V get(K key){
        int idx=hashFun(key);
        Node tmp=bkt.get(idx);
        while(tmp!=null){
            if(tmp.key.equals(key))return tmp.value;
            tmp=tmp.next;
        }
        return null;
    }
    public V remove(K key){
        int idx=hashFun(key);
        Node curr=bkt.get(idx);
        Node prev=null;
        while(curr!=null){
            if(curr.key.equals(key))break;
            prev=curr;
            curr=curr.next;
        }
        if(curr==null)return null;
        if(prev==null)bkt.set(idx,curr.next);
        else prev.next=curr.next;
        size--;
        return curr.value;

    }
    public int hashFun(K key){
        int bn=key.hashCode()%bkt.size();
        if(bn<0)bn+=bkt.size();
        return bn;
    }
    public String toString(){
        String s="{";
        for(Node n:bkt){
            while(n!=null){
                s=s+n.key+":"+n.value;
                n=n.next;
            }
        }
        return s+"}";
    }
}
