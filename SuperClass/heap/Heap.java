package heap;
import java.util.*;

public class Heap {
    private ArrayList<Integer> list=new ArrayList<>();
    public void add(int item){
        list.add(item);
        upHeapify(list.size()-1);

    }
    private void upHeapify(int ci){
        int pi=(ci-1)/2;
        if(list.get(pi)>list.get(ci)){
            swap(ci, pi);
            upHeapify(pi);
        }

    }
    public int remove(){
        int rm=list.get(0);        
        swap(0, list.size()-1);
        list.remove(list.size()-1);
        downHeapify(0);
        return rm;
    }
    private void downHeapify(int pi){
        int lci=2*pi+1;
        int rci=2*pi+2;
        int min=pi;
        if(lci<list.size()&&list.get(min)>list.get(lci))min=lci;
        if(rci<list.size()&&list.get(min)>list.get(rci))min=rci;
        if(min!=pi){
            swap(pi, min);
            downHeapify(min);
        }

    }
    public int get(int idx){
        return list.get(idx);
    }
    public void display(){
        System.out.println(list);
    }
    private void swap(int i,int j){
        int f=list.get(i),s=list.get(j);
        list.set(i, s);
        list.set(j, f);
    }
    
    public static void main(String[] args) {
        Heap q=new Heap();
        q.add(1);
        q.add(5);
        q.add(3);
        q.add(7);
        q.add(9);
        q.add(8);
        q.add(6);
        q.display();
       q.remove();
       q.display();
    }
}
