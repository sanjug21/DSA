package LinkedList;
public class LinkedList {
    private Node head;
    private Node tail;
    private int size;
    class Node{
        int val;
        Node next;
        Node(int val){
            this.val=val;
            this.next=null;
        }
    }
    public void addFirst(int val){
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

    public void addLast(int val){     
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

    public static Node meet(LinkedList list){
        
        return null;
    }
    public static void main(String[] args) {
        LinkedList list=new LinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.createCycle();
        list.print();
       
    }

}
