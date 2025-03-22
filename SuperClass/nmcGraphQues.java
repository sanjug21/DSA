import java.util.*;

import OOPS.stac;


public class nmcGraphQues {
    public static class Pair{
        int e;
        long d;
        Pair(int e,long d){
            this.e=e;
            this.d=d;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)arr[i]=sc.nextInt();



        
        // int c1=sc.nextInt(),c2=sc.nextInt();
        //for 1st part



      // for 2nd part  
    //     long A[]=dijkstra(arr, c1);
    //     long B[]=dijkstra(arr, c2);
    //     long dis=Long.MAX_VALUE;
    //     int ans=-1;
    //     for(int i=0;i<n;i++){
    //         if(A[0]==Long.MAX_VALUE || B[0]==Long.MAX_VALUE)continue;
    //         if(dis>A[i]+B[i]){
    //             ans=i;
    //             dis=A[i]+B[i];
    //         }
    //     }
    //    System.out.println(ans);
    }

    // public static int nearestMeeting(int arr[],int n,int c1,int c2){
    //     long A[]=distance(arr, n, c1);
    //     long B[]=distance(arr, n, c2);
    //     long dis=Long.MAX_VALUE;
    //     int ans=-1;
    //     for(int i=0;i<n;i++){
    //         if(A[0]==Long.MAX_VALUE || B[0]==Long.MAX_VALUE)continue;
    //         if(dis>A[i]+B[i]){
    //             ans=i;
    //             dis=A[i]+B[i];
    //         }
    //     }
    //     return ans;
    // }


    // public static long[] distance(int arr[],int n,int c){
    //     long A[]=new long[n];
    //     Arrays.fill(A, Long.MAX_VALUE);
    //     int d=0;
    //     Queue<Integer> q=new LinkedList<>();
    //     q.add(c);
    //     while (!q.isEmpty()) {
    //         int r=q.poll();
    //         if(A[r]>d){
    //             A[r]=d;
    //         }
    //         int nei=arr[r];
    //         if(){
    //             q.add(nei);
    //             d++;
    //         }
    //     }
    //     return A;
    // }



    public static long[] dijkstra(int arr[],int c){
    
        long ans[]=new long[arr.length];
        Arrays.fill(ans, Long.MAX_VALUE);
        ans[c]=0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.d, b.d));
        pq.add(new Pair(c, 0));
        while (!pq.isEmpty()) {
            Pair p=pq.poll();
            int edge =p.e;
            long dis =p.d;

            if (dis < ans[edge]) {
                ans[edge]=dis;
            }
            int nei=arr[edge];
            if(ans[nei]!=-1 && ans[nei]==Long.MAX_VALUE){
                pq.add(new Pair(nei, dis+1));
            }
        }
        return ans;
    }
}
