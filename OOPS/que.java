package OOPS;

public class que {
   
        static class Node{
            int data;
            Node next;
            Node(int data){
                this.data=data;
                this.next=null;
            }
        }
        static class Queue{
           static Node head=null;
            static Node tail=null;
            public  boolean isEmp(){
                return head==null && tail==null;
            }
        
        
        public void add(int data){
            Node nn=new Node(data);
            if(isEmp()){
                head=tail=nn;
            }else{
                tail.next=nn;
                tail=nn;
            }
           }
        public int remove(){
            if(isEmp()){
                return -1;
            }
            int front =head.data;
            if(head==tail){
                tail=null;
            }
            head=head.next;
            return front;
        }
        public int peek(){
            return head.data;
        }

        }
        /*
         * Queue Collection Framework
         * add(); 
         * addAll();
         * size();
         * clear();
         * remove();
         * remove(int idx);
         * remove(element);
         * isEmpty();
         * peek();
         * sort();
         */
    public static void main(String []args){
        Queue q=new Queue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        while(!q.isEmp()){
            System.out.println(q.peek());
            q.remove();

        }


    }
}
