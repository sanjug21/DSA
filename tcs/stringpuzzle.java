import java.util.*;

public class stringpuzzle {
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        HashSet<String> all = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            set.add(s[1]);
            all.add(s[0]);
            all.add(s[1]);
            if (map.containsKey(s[0])) map.get(s[0]).add(s[1]);
            else {
                map.put(s[0], new ArrayList<>());
                map.get(s[0]).add(s[1]);
            }
        }
        
        String[] que = sc.nextLine().split(" ");
        String head = "";
        for (String str : all) {
            if (!set.contains(str)) {
                head = str;
                break;
            }
        }
        
        if (head.equals("")) {
            System.out.println("-1");
            return;
        }
        
        HashMap<String, Integer> level = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.add(head);
        level.put(head, 1);
        while (!q.isEmpty()) {
            String curr = q.poll();
            int l = level.get(curr);
            ArrayList<String> neighbors = map.getOrDefault(curr, new ArrayList<>());
            for (String nei : neighbors) {
                if (!level.containsKey(nei)) {
                    level.put(nei, l + 1);
                    q.add(nei);
                }
            }
        }
        
        int ans = 0;
        for (String s : que) {
            ans += level.getOrDefault(s, -1);
        }
        System.out.print(ans);
    }
}