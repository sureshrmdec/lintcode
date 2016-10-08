//import java.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
public class KthInArrays {
    /**
     * @param arrays a list of array
     * @param k an integer
     * @return an integer, K-th largest element in N arrays
     */

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public int compareTo(Node that) {
            return this.value - that.value;
        }
    }

    public KthInArrays(int[][] arrays, int k) {
    }

    public static int findKth(int[][] arrays, int k) {
        int count = 0;
        for (int i = 0; i < arrays.length; ++i) {
            count += arrays[i].length;
            Arrays.sort(arrays[i]);
        }

        // kth largest is same as jth smallest
        assert(k < count);
        int jth = count - k + 1;

        // fill the queue with min of each array
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        for (int i = 0; i < arrays.length; ++i) {
            if (arrays[i].length != 0)
                queue.add(new Node(i, 0, arrays[i][0]));
        }

        int i = 0;
        while (true) {
            Node node = queue.poll();
            if (++i == jth) return node.value;

            if (node.y + 1 < arrays[node.x].length)
                queue.add(new Node(node.x, node.y + 1, arrays[node.x][node.y + 1]));
        }
    }

    public static void main(String [] args) {

        int[][] matrix = {{9, 3, 2, 4, 8}, {1, 2, 3, 4, 2}};

        System.out.println("Original matrix:");
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j)
                System.out.printf("%d, ", matrix[i][j]);
        }

        System.out.println("After soft:");
        for (int i = 0; i < matrix.length; ++i) {
            Arrays.sort(matrix[i]);
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j)
                System.out.printf("%d, ", matrix[i][j]);
        }
        System.out.println();

        System.out.println(findKth(matrix, 1));
        System.out.println(findKth(matrix, 2));
        System.out.println(findKth(matrix, 3));
        
        int[][] matrix2 = {{11}, {1, 2, 3, 4, 112, 87}, {564}, {789, 12, 15}};
        System.out.println(findKth(matrix2, 7));
        
        int[][] matrix3 = {{}, {}, {1}, {1, 2, 3, 4}, {11, 10, 9, 8, 7}};
        System.out.println(findKth(matrix3, 5));
    }
}
