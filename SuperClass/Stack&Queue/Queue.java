

public class Queue {
    private int a[];
    private int head;
    private int size;
    Queue(){
        a=new int [5];
    }
    Queue(int n){
        a=new int [n];
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean isFull(){
        return size==a.length;
    }
    public void enqueue(int item)throws Exception{
        if(isFull())throw new Exception("Queue is full");
        int idx=(head+size)%a.length;
        a[idx]=item;
        size++;
    }
    public int dequeue(){
        int element=a[head];
        head=(head+1)%a.length;
        size--;
        return element;
    }
    public int get()throws Exception{
        if(isEmpty())throw new Exception("Queue is empty");
        return a[head];
    }
    public void display(){
        for(int i=0;i<size;i++){
            System.out.print(a[(head+i)%a.length]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args)throws Exception {
        Queue q=new Queue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.display();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.display();
        q.enqueue(60);
        q.enqueue(70);
        q.enqueue(80);
        q.display();
    }
}