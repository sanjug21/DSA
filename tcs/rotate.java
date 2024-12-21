import java.util.*;
public class rotate {
    static char[][] mat;
    static int n;
    static void rotateLayer(int r, int c, int sz, int layerNum) {
        if (sz <= 1) return;
        List<Character> layer = extractLayer(r, c, sz);
        List<Character> rotated = rotateAndReplaceLayer(layer, layerNum);
        updateLayer(r, c, sz, rotated);
    }
    static List<Character> extractLayer(int r, int c, int sz) {
        List<Character> layer = new ArrayList<>();
        for (int j = c; j < c + sz; j++) 
            layer.add(mat[r][j]);
        for (int i = r + 1; i < r + sz; i++) 
            layer.add(mat[i][c + sz - 1]);
        for (int j = c + sz - 2; j >= c; j--) 
            layer.add(mat[r + sz - 1][j]);
        for (int i = r + sz - 2; i > r; i--) 
            layer.add(mat[i][c]);
        return layer;
    }
    static List<Character> rotateAndReplaceLayer(List<Character> layer, int layerNum) {
        int rot = layerNum;
        List<Character> rotated = new ArrayList<>(layer.size());
        if (layerNum % 2 == 1) {
            Collections.rotate(layer, -rot);
            for (char ch : layer) 
                rotated.add(prevChar(ch));
        } else {
            Collections.rotate(layer, rot);
            for (char ch : layer) 
                rotated.add(nextChar(ch));
        }
        return rotated;
    }

    static void updateLayer(int r, int c, int sz, List<Character> rotated) {
        int idx =0;
        for (int j=c;j<c+sz;j++) 
            mat[r][j] = rotated.get(idx++);
        for (int i=r+1;i<r+sz;i++) 
            mat[i][c+sz-1]=rotated.get(idx++);
        for (int j=c+sz-2;j>=c;j--) 
            mat[r+sz-1][j]=rotated.get(idx++);
        for (int i=r+sz-2;i>r;i--) 
            mat[i][c]=rotated.get(idx++);
    }
    static char prevChar(char ch) {
        return ch == 'A' ? 'Z' : (char)(ch - 1);
    }
    static char nextChar(char ch) {
        return ch == 'Z' ? 'A' : (char)(ch + 1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        mat = new char[n][n];
        for (int i = 0; i < n; i++) {
            String[] row = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) 
                mat[i][j] = row[j].charAt(0);
        }
        int q = sc.nextInt();
        sc.nextLine();
        for (int k = 0; k < q; k++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int sz = sc.nextInt();
            for (int layer = 1; layer <= sz / 2; layer++) {
                rotateLayer(r + layer - 1, c + layer - 1, sz - 2 * (layer - 1), layer);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) 
                res.append(mat[i][j]);
        System.out.print(res);
    }
}