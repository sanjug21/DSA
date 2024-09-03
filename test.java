import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class test{
public static int[] resultsArray(int[][] queries, int k) {
        // PriorityQueue<Integer> q=new PriorityQueue<>();
        ArrayList<Integer>q = new ArrayList<>();
        int n=queries.length;
        int ans[]=new int[n];
        q.add(3);
        System.out.println(q);
        q.add(7);
        System.out.println(q);
        q.add(5);
        System.out.println(q);
        q.add(3);
        System.out.println(q);
        // for(int i=0;i<n;i++){
        //     int dis=Math.abs(queries[i][0])+Math.abs(queries[i][1]);
        //     q.add(dis);
        //     System.out.println(q);
        //     if(q.size()<k){
        //         ans[i]=-1;
        //     }
        //     else{
        //         List<Integer> tmp=new ArrayList<>(q);
        //         ans[i]=tmp.get(k-1);
        //     }
        // }
        return ans;
    }


    public static void main(String[] args) {
        int ans[]=resultsArray(new int[][]{{1,2},{3,4},{2,3},{-3,0}}, 2);
    }
}