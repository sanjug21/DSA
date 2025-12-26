import java.util.*;

public class FunWithClock {
    
    static long getCost(int deg, int p1, int p2) {
        if (deg <= 90)
            return (long) deg*p1;
        return (long) 90*p1 + (long)(deg - 90)*p2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] t=sc.next().split(":");
        int curH=(Integer.parseInt(t[0]) % 12) * 30;
        int curM=(Integer.parseInt(t[1])) * 6;

        int n=sc.nextInt();
        int cw=sc.nextInt();
        int acw=sc.nextInt();

        int ht1=sc.nextInt();
        int ht2=sc.nextInt();
        int mt1=sc.nextInt();
        int mt2=sc.nextInt();

        long total=0;

        while (n-- > 0) {
            int ang=sc.nextInt();
            long best=Long.MAX_VALUE;
            int nextH=-1, nextM=-1;

            
            for (int i=0;i<12;i++) {
                int h=i*30;

                int[] mOpts={ (h + ang)%360, (h - ang + 360)%360 };

                for (int m:mOpts) {
                    int h_cw=(h - curH + 360)%360;
                    int h_acw=(curH - h + 360)%360;

                    int m_cw=(m - curM + 360)%360;
                    int m_acw=(curM - m + 360)%360;

                    int gap_cw=(curH == curM) ? 360 : (curM - curH + 360)%360;
                    int gap_acw=(curH == curM) ? 360 : (curH - curM + 360)%360;

                    
                    if (h_cw + m_acw <= gap_cw) {
                        long cost=getCost(h_cw, ht1, ht2)*cw + getCost(m_acw, mt1, mt2)*acw;
                        if (cost < best) {
                            best=cost;
                            nextH=h;
                            nextM=m;
                        }
                    }
                    
                    if (h_acw + m_cw <= gap_acw) {
                        long cost=getCost(h_acw, ht1, ht2)*acw + getCost(m_cw, mt1, mt2)*cw;
                        if (cost < best) {
                            best=cost;
                            nextH=h;
                            nextM=m;
                        }
                    }
                }
            }

            total+=best;
            curH=nextH;
            curM=nextM;
        }

        System.out.print(total);
    }
}