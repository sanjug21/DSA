package OOPS;

public class stac {
       static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            next=null;
        }
    }
    
    static class Stack
{
        public static Node head;
        public boolean isEmpty(){
            return head==null;
         }
    public  void push(int data)
        {
         Node nn=new Node(data);
            if(isEmpty()){head=nn; 
                return;    
        }
            nn.next=head;
            head=nn;
        }
        public  int pop(){
            int val=head.data;
            head=head.next;
            return val;
        }
        public  int see(){
            return head.data;
        }
}

/*
 * Stack Frame work collection 
 * push();
 * pop();
 * peek();
 * empty();
 * search();
 */
    

    public static void main(String[]args){
        
        Stack st=new Stack();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        while(!st.isEmpty()){
            System.out.println(st.see());
            st.pop();
        }
    }

   
    
}

