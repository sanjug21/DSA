import java.util.*;

public class sol {

   
    public static void main(String[] args) {

      HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>();
      graph.put(0, new HashMap<>());
      graph.get(0).put(1, 2);
      graph.get(0).put(3, 6);
      graph.put(1, new HashMap<>());
      graph.get(1).put(0, 2);
      graph.get(1).put(2, 4);
      graph.get(1).put(4, 5);
      graph.put(2, new HashMap<>());
      graph.get(2).put(1, 4);
      graph.get(2).put(4, 7);
      graph.put(3, new HashMap<>());
      graph.get(3).put(0, 6);
      graph.get(3).put(4, 11);
      graph.put(4, new HashMap<>());
      graph.get(4).put(1, 5);
      graph.get(4).put(2, 7);
      graph.get(4).put(3, 11);

      prims(graph);








      //  Scanner sc=new Scanner(System.in);
      //  sc.close();
       

        // int t = sc.nextInt();
        // sc.nextLine(); 

        // while (t-- > 0) {
        //     String s = sc.next();
        //     String a = "";
        //     for (int i = 0; i < s.length(); i++) {
        //         if (s.charAt(i) == '0') {
        //             a += '0';
        //         } else {
        //             break;
        //         }
        //     }
        //     String b = s.substring(a.length());
        //     long x = a.isEmpty() ? 0 : Long.parseLong(a);
        //     long y = b.isEmpty() ? 0 : Long.parseLong(b);
        //     if (y > x) {
        //         System.out.println(x + " " + y);
        //     } else {
        //         System.out.println(-1);
        //     }
        // }
       
    }

    public boolean Valid_Tree(int n,int [][]edges){
      HashMap<Integer,List<Integer>>map=new HashMap<>();
      for(int i=0;i<n;i++){
          map.put(i,new ArrayList<>());

      }
      for(int i=0;i<edges.length;i++){
          int a=edges[i][0];
          int b=edges[i][1];
          map.get(a).add(b);
          map.get(b).add(a);
      }
      return DFT(map);
  }
  public boolean DFT(HashMap<Integer,List<Integer>>map){
    
    return false;
  }
 
  public static void prims(HashMap<Integer,HashMap<Integer,Integer>>map){
    PriorityQueue<int []>q=new PriorityQueue<>((a,b)->a[0]-b[0]);
    HashSet<Integer> vis=new HashSet<>();
    q.add(new int[]{0,0});
    while(!q.isEmpty()){
      int tmp[]=q.poll();
      if(vis.contains(tmp[0]))continue;
      vis.add(tmp[0]);
      for(int i:map.get(tmp[0]).keySet()){
        if(!vis.contains(i))q.add(new int []{i,tmp[1]+map.get(tmp[0]).get(i)});
      }
    }
  }


}
