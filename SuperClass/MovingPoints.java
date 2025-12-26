import java.util.*;

public class MovingPoints {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Pair [] pts = new Pair[n];
        for(int i=0; i<n; i++){
            int x=sc.nextInt();
            pts[i] = new Pair(x, 0);
        }
        for(int i=0; i<n; i++){
            int v=sc.nextInt();
            pts[i].v = v;
        }

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(Pair p : pts){
           map.putIfAbsent(p.v, new ArrayList<>());
           map.get(p.v).add(p.x);           
        }
        long ans=0;
        
        for(int v : map.keySet()){
            List<Integer> list = map.get(v);
            Collections.sort(list);
            long pre=0;
            for(int i=0; i<list.size(); i++){
                int x=list.get(i);
                ans+=(x - pre)*(i + 1);
                pre = x;
            }
        }

        // or

        Set<Integer> speed= map.keySet();
        List<Integer> sortedSpeed= new ArrayList<>(speed);
        Collections.sort(sortedSpeed);
        Map<Integer, Integer> speedWithIndex = new HashMap<>();
        for(int i=0; i<sortedSpeed.size(); i++){
            speedWithIndex.put(sortedSpeed.get(i), i+1);
        }
        Arrays.sort(pts, (a, b) -> Integer.compare(a.x, b.x));
        FenwickTree ftX = new FenwickTree(n);
        FenwickTree ftSum=new FenwickTree(n);
        for(Pair p : pts){
            int index = speedWithIndex.get(p.v);
            long countX = ftX.query(index-1);
            long sumX = ftSum.query(index-1);
            ans += countX * p.x - sumX;
            ftX.update(index, 1);
            ftSum.update(index, p.x);
        }


        System.out.println(ans);
        sc.close();
    }
    static class Pair{
        int x;
        int v;
        Pair(int x, int v) {
            this.x = x;
            this.v = v;
        }
    }
}