package OOPS;

import java.util.*;


public class Graph {
    static class Edge{
        int src;
        int des;
     
        public Edge(int s,int d){
            this.src=s;
            this.des=d;
            
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));
    }
   // bfs(Breadth First Search)
    public static void bfs(ArrayList<Edge> graph[],int v,boolean vis[],int idx){
        Queue<Integer> q=new LinkedList<>();
        
        q.add(idx);

        while (!q.isEmpty()) {
            int curr=q.remove();
            if(vis[curr]==false){
                System.out.println(curr);
                vis[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    Edge e=graph[curr].get(i);
                    q.add(e.des);
                }
            }
            
        }
    }

    // dfs(Depth First Search)

    public static void dfs(ArrayList<Edge> graph[],int cur,boolean vis[]){
        System.out.println(cur+" ");
        vis[cur]=true;
        for(int i=0;i<graph[cur].size();i++){
            Edge e=graph[cur].get(i);
            if(vis[e.des]==false)
            {dfs(graph,e.des,vis);}
        }
    }

    //paths
    public static void path(ArrayList<Edge> graph[],boolean vis[],int curr,String path, int tar ){
        if(curr==tar){
            System.out.println(path);
            return;
        }
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(vis[curr]==false){
                vis[curr]=true;
                path(graph, vis, e.des, path+e.des, tar);
                vis[curr]=false;
            }
        }

    }
     public static void main(String[] args) {
        int v=7;

        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[]=new ArrayList[v];

        createGraph(graph);
        boolean vis[]=new boolean[v];
        // path(graph, vis, 0, "0", 5);



        for(int i=0;i<v;i++){
            if(vis[i]==false){
               //  bfs(graph, v,vis,i);
                 dfs(graph,i,vis);
            }
        }

        
        // for(int i=0;i<graph[2].size();i++){
        //     Edge e=graph[2].get(i);
        //     System.out.println(e.des);
        // }
     }
}