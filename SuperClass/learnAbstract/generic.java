package learnAbstract;

import java.util.*;
public class generic  {
    
    public static void main(String[] args) {
        Integer a[]={10,12,5,9,18,10};
        String s[]={"Sanju","Shubham","Sagar","Rajesh"};
        display(a);
        display(s);
        // Pair<String,Integer> p=new Pair<>();
       LinkedList<String> ll=new LinkedList<>();
       ll.addFirst("Sanju");
       ll.addLast("singh");
       ll.print();
    }
    public static <Type> void display(Type a[]){
        System.out.println(Arrays.toString(a));
    }
}


class Pair <N,A>{
   N name;
   A age;
    Pair(N name,A age){
        this.name=name;
        this.age=age;
    }
    Pair(){}
}
 class LinkedList<T> {
    private Node head;
    private Node tail;
    private int size;
    class Node{
        T val;
        Node next;
        Node(T val){
            this.val=val;
            this.next=null;
        }
    }
    public void addFirst(T val){
        Node node=new Node(val);
        if(size==0){
            head=node;
            tail=node;
            size++;
        }
        else{
            node.next=head;
            head=node;
            size++;
        }
    }

    public void addLast(T val){     
        if(size==0){
            addFirst(val);
        }
        else{
            Node node=new Node(val);
            tail.next=node;
            tail=node;
            size++;
        }
    }

    public void removeFirst(){
        try{
            head=head.next;
            size--;
        }
        catch(Exception e){
            System.out.println(e);
        }


    }

    public void removeLast(){
        Node temp=head;
        try{
            if(head.next==null){
                head=null;
                size--;
            }
            else{
            while(temp.next.next!=null){
                temp=temp.next;
            }
            temp.next=temp.next.next;
            tail=temp;
            size--;
        }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void print(){
        if(head!=null){
            Node temp=head;
            while(temp!=null){
            System.out.print(temp.val+" ");
            temp=temp.next;
        }
        System.out.println();}
    }
    public Node getNode(int k){
        try{
            Node temp = head;
			for (int i = 0; i < k; i++) {
				temp = temp.next;
			}
			return temp;


        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    public void createCycle(){
        tail.next=getNode(2);
    }

    public  Node meet(LinkedList<T> list){
        
        return null;
    }
  

}