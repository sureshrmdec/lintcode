import java.util.PriorityQueue;

public class KthSmallest {
    // http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix/

    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */

    public static class Node implements Comparable<Node> {
        int x, y, value;

        public Node(int x, int y, int value) {this.x = x; this.y = y; this.value = value;}

        public int compareTo(Node that) { return this.value - that.value; }
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        assert(k <= m * n);

        // bad when it's a vertical vector
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        for (int i = 0; i < m; i++) {
            queue.add(new Node(i, 0, matrix[i][0]));
        }

        int i = 0;
        while (true) {
            Node node = queue.poll();
            if (++i == k) return node.value;

            if (node.y + 1 < n) {
                queue.add(new Node(node.x, node.y + 1, matrix[node.x][node.y + 1]));
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
            {1 ,5 ,7},
            {3 ,7 ,8},
            {4 ,8 ,9},
        };

        System.out.println(kthSmallest(matrix1, 4));
    }
}
