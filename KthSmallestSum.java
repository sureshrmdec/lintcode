import java.util.PriorityQueue;

public class KthSmallestSum {
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    public static class Node implements Comparable<Node> {
        int x, y, value;
        public Node(int x, int y, int value) { this.x = x; this.y = y; this.value = value; }
        public int compareTo(Node that) { return this.value - that.value; }
    }

    public static int kthSmallestSum(int[] A, int[] B, int k) {
        int m = A.length;
        int n = B.length;
        assert(k <= m * n);

        boolean[][] marked = new boolean[m][n];
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(new Node(0, 0, A[0] + B[0]));
        int i = 0;
        while (true) {
            Node node = queue.poll();
            if (++i == k) return node.value;

            if (node.y + 1 < n && !marked[node.x][node.y + 1]) {
                queue.add(new Node(node.x, node.y + 1, A[node.x] + B[node.y + 1]));
                marked[node.x][node.y + 1] = true;
            }
            if (node.x + 1 < m && !marked[node.x + 1][node.y]) {
                queue.add(new Node(node.x + 1, node.y, A[node.x + 1] + B[node.y]));
                marked[node.x + 1][node.y] = true;
            }
        }
    }

    public static void main(String[] args) {
        int[] a1 = {1, 7, 11};
        int[] b1 = {2, 4, 6};
        System.out.println(kthSmallestSum(a1, b1, 3));
        System.out.println(kthSmallestSum(a1, b1, 4));
        System.out.println(kthSmallestSum(a1, b1, 8));
    }
}
