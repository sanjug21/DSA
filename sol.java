import java.util.Arrays;
import java.util.PriorityQueue;

public class sol {

   
    public static void main(String[] args) {
       int nums[]={161209470};
       int k=5,mul=39846;
       int n=nums.length;
       if(n==1){
        long temp=1;
        while(k-->0){
            temp*=mul;
            System.out.println(temp);
        }
        System.out.println(temp);
       }
       long temp[] = new long[n];
        for (int i = 0; i < n; i++) {
        temp[i] = nums[i];
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> temp[a] == temp[b] ? Long.compare(a, b): Long.compare(temp[a], temp[b]));
        for (int i = 0; i < n; i++) {
          q.add(i);
        }
        int mod = 1000000007;

        while (k-- > 0) {
            
            int idx = q.poll();
             temp[idx] *= mul;
            q.add(idx);
        }
        // System.out.println(temp[0]);
       for(int i=0;i<nums.length;i++){
        nums[i]=(int)(temp[i]%mod);
       }
       System.out.println(Arrays.toString(nums));
       




        // Scanner sc = new Scanner(System.in);
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
}
