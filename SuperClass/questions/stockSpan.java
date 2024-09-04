package questions;

import java.util.Arrays;
import java.util.Stack;

public class stockSpan {
    public static void main(String[] args) {
        int []stock={30,35,40,38,35};
        int n=stock.length;
        int ans[]=new int [n];
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&stock[i]>=stock[st.peek()])st.pop();
            if(st.isEmpty())ans[i]=i+1;
            else ans[i]=i-st.peek();
            st.push(i);

        }
        System.out.println(Arrays.toString(ans));
    }
}
