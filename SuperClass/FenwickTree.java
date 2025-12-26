public class FenwickTree {
    long []bit;
    int n;
    public FenwickTree(int n) {
        this.n = n;
        bit = new long[n + 1];
    }
    public void update(int i, long delta) {
        while (i <= n) {
            bit[i] += delta;
            i += i & -i;
        }
    }
    public long query(int i) {
        long sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }
}
