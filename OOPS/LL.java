package OOPS;

class LL {
    Node head;
    int size=1;
     public class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public void addFirst(int data){
        Node nn=new Node(data);
        nn.next=head;
        head=nn;
    }public void printList(){
        Node nn=head;
        while(nn!=null){
            System.out.println(nn.data);
            nn=nn.next;
        }

    }
    public void addLast(int data){
        Node nn=new Node(data);
        if(head==null){
            head=nn;
           return;
        }
        Node ln=head;
        while(ln.next!=null){
            ln=ln.next;
        }
        ln.next=nn;

    }
        public void remove(){
        if(head.next==null){
            head=null;
            return;
        }
        Node nn=head;
        while(nn.next.next!=null){
            nn=nn.next;
        }
        nn.next=null;
    }
    public void remove(int idx){
        if(idx==1){
            head=head.next;
            return;
        }
        Node nn=head;
        while(size+1<idx){
            nn=nn.next;
            size++;
        }
        nn.next=nn.next.next;
        
    }
    public int sizeOf(){
        Node ls=head;
        int i=0;
        while(ls !=null){
            i++;
            ls=ls.next;
        }
        return i;
    }

        public static void main(String []args){
            LL list=new LL();
            list.addFirst(3);
            list.addFirst(2);
            list.addFirst(1);
            list.addLast(4);
            list.addLast(5);
            list.remove(5);
            list.printList();
           System.out.println(list.sizeOf());
           
        
        }
}
