
import java.util.Arrays;

public class stack {
    private int a[];
    private int i;
    stack(){
        a=new int[5];
    }
    stack(int n){
        a=new int[n];
    }

    public boolean isEmpty(){
        return i==0;
    }
    public void push(int item)throws Exception{
        if(i==a.length)throw new Exception("Stack is full");
        a[i]=item;
        i++;
    }
    public int pop()throws Exception{
        if(isEmpty())throw new Exception("Stack is Empty");
        i--;
        int ans=a[i];
        a[i]=0;
        return ans;      
    }
    public int peek()throws Exception{
        if(isEmpty())throw new Exception("Stack is Empty");
        
        return a[i-1];
    }
    public void display(){
        System.out.println(Arrays.toString(a));
    }
    public static void main(String []args)throws Exception{
        stack st=new stack();
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        st.display();
        st.pop();
        st.pop();
        st.pop();
        st.pop();
        st.pop();
        st.peek();
        st.display();
    }
    
}
