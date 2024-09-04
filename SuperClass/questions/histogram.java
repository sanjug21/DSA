package questions;

import java.util.Stack;

public class histogram {
    public static void main(String[] args) {
        int heights[]={1,2,3,4};
        int ans=0;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<heights.length;i++){
            while (!st.isEmpty() && heights[i]<heights[st.peek()]) {
                int h=heights[st.pop()];
                int r=i;
            if(!st.isEmpty()){
                int l=st.peek();
                ans=Math.max(ans, h*(r-l-1));
            }else ans=Math.max(ans, h*r);
        }
        st.push(i);
        }
        int r=heights.length;
        while(!st.isEmpty()){
            int h=heights[st.pop()];
            if(!st.isEmpty()){
                int l=st.peek();
                ans=Math.max(ans, h*(r-l-1));
            }else ans=Math.max(ans, h*r);

        }

        System.out.println(ans);
    }
}
