import java.util.*;

public class Circulatory {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        sc.nextLine();

        List<String> rings=new ArrayList<>();
        for (int i=0;i<N;i++) {
            rings.add(sc.nextLine().trim());
        }

        if (N == 0) {
            System.out.print(0);
            return;
        }

        Deque<int[]> queue=new ArrayDeque<>();
        Set<String> visited=new HashSet<>();

        int outerLayer=N-1;
        int outerRingLen=rings.get(outerLayer).length();

        boolean validStartFound=false;
        for (int i=0;i<outerRingLen;i++) {
            if (rings.get(outerLayer).charAt(i)=='0') {
                queue.add(new int[] { outerLayer, i, 1 });
                visited.add(outerLayer + "," + i);
                validStartFound = true;
            }
        }

        if (!validStartFound) {
            System.out.print(0);
            return;
        }

        int minRooms = -1;

        while (!queue.isEmpty()) {
            int[] curr=queue.poll();
            int currLayer=curr[0];
            int currIdx=curr[1];
            int steps=curr[2];

            if (currLayer == 0) {
                minRooms=steps;
                break;
            }

            int currRingLen=rings.get(currLayer).length();
            List<int[]> nextMoves=new ArrayList<>();

            nextMoves.add(new int[] { currLayer, (currIdx + 1) % currRingLen });
            nextMoves.add(new int[] { currLayer, (currIdx - 1 + currRingLen) % currRingLen });

            if (currLayer > 0) {
                nextMoves.add(new int[] { currLayer - 1, currIdx / 2 });
            }

            if (currLayer < N - 1) {
                nextMoves.add(new int[] { currLayer + 1, currIdx * 2 });
                nextMoves.add(new int[] { currLayer + 1, currIdx * 2 + 1 });
            }

            for (int[] move:nextMoves) {
                int nLayer=move[0];
                int nIdx=move[1];
                String key=nLayer + "," + nIdx;

                if (!visited.contains(key)) {
                    if (rings.get(nLayer).charAt(nIdx) == '0') {
                        visited.add(key);
                        queue.add(new int[] { nLayer, nIdx, steps + 1 });
                    }
                }
            }
        }

        System.out.print(minRooms);
    }
}
