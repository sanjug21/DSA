import java.util.*;

public class Rubiverse {

    static class Block {
        char id;
        int r, c;
        String vals;
        int[] orient;
    }

    static int[][] dirs = {
            { 1, 0, 0 }, { -1, 0, 0 },
            { 0, 1, 0 }, { 0, -1, 0 },
            { 0, 0, 1 }, { 0, 0, -1 }
    };

    static int[] opp = { 1, 0, 3, 2, 5, 4 };

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<String> lines=new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }

        if (lines.size() < 6)
            return;

        char[][] grid = new char[4][4];
        for (int i=0;i<4;i++) {
            String row = lines.get(i);
            while (row.length() < 4)
                row += ".";
            grid[i] = row.toCharArray();
        }

        String charStr=lines.get(4).trim();
        String query=lines.get(5).trim();

        ArrayList<Block> faces=new ArrayList<>();
        HashMap<Character, Block> map=new HashMap<>();
        int count = 0;
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (grid[i][j] != '.') {
                    Block b=new Block();
                    b.id=grid[i][j];
                    b.r=i;
                    b.c=j;
                    b.vals=charStr.substring(count * 4, (count + 1) * 4);
                    b.orient=new int[3];
                    faces.add(b);
                    map.put(b.id, b);
                    count++;
                }
            }
        }

        Block start=faces.get(0);
        start.orient[0]=4;
        start.orient[1]=0;
        start.orient[2]=3;

        Queue<Block> q=new LinkedList<>();
        q.add(start);
        HashSet<Character> visited=new HashSet<>();
        visited.add(start.id);

        int[] dr ={ 0, 0, 1, -1 };
        int[] dc ={ 1, -1, 0, 0 };
        char[] moveType={ 'R', 'L', 'D', 'U' };

        while (!q.isEmpty()) {
            Block curr=q.poll();
            int norm=curr.orient[0];
            int right=curr.orient[1];
            int down=curr.orient[2];

            for (int k=0;k<4;k++) {
                int nr=curr.r+dr[k];
                int nc=curr.c+dc[k];
                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && grid[nr][nc] != '.') {
                    Block next=map.get(grid[nr][nc]);
                    if (!visited.contains(next.id)) {
                        int nN = 0, nR = 0, nD = 0;

                        if (moveType[k] == 'R') {
                            nN=right;
                            nR=opp[norm];
                            nD=down;
                        } else if (moveType[k] == 'L') {
                            nN=opp[right];
                            nR=norm;
                            nD=down;
                        } else if (moveType[k] == 'D') {
                            nN=down;
                            nR=right;
                            nD=opp[norm];
                        } else if (moveType[k] == 'U') {
                            nN=opp[down];
                            nR=right;
                            nD=norm;
                        }

                        next.orient[0]=nN;
                        next.orient[1]=nR;
                        next.orient[2]=nD;

                        visited.add(next.id);
                        q.add(next);
                    }
                }
            }
        }

        int tx=0, ty=0, tz=0;
        char[] qFaces=query.toCharArray();

        for (char c:qFaces) {
            Block b=map.get(c);
            int nIdx=b.orient[0];
            tx+=dirs[nIdx][0];
            ty+=dirs[nIdx][1];
            tz+=dirs[nIdx][2];
        }

        StringBuilder res =new StringBuilder();

        for (char c:qFaces) {
            Block b=map.get(c);
            int n=b.orient[0];
            int r=b.orient[1];
            int d=b.orient[2];

            int[] vN=dirs[n];
            int[] vR=dirs[r];
            int[] vD=dirs[d];

            if (check(tx, ty, tz, vN, vR, vD, -1, -1))
                res.append(b.vals.charAt(0));
            else if (check(tx, ty, tz, vN, vR, vD, 1, -1))
                res.append(b.vals.charAt(1));
            else if (check(tx, ty, tz, vN, vR, vD, -1, 1))
                res.append(b.vals.charAt(2));
            else if (check(tx, ty, tz, vN, vR, vD, 1, 1))
                res.append(b.vals.charAt(3));
            else
                res.append("?");
        }

        System.out.println(res.toString());
    }

    static boolean check(int tx, int ty, int tz, int[] n, int[] r, int[] d, int signR, int signD) {
        int x = n[0] + (signR*r[0]) + (signD*d[0]);
        int y = n[1] + (signR*r[1]) + (signD*d[1]);
        int z = n[2] + (signR*r[2]) + (signD*d[2]);
        return x == tx && y == ty && z == tz;
    }
}