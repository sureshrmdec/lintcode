import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;

public class TwoSum {
    public class Solution {
        /*
         * @param numbers : An array of Integer
         * @param target : target = numbers[index1] + numbers[index2]
         * @return : [index1 + 1, index2 + 1] (index1 < index2)
         */

        private class Node {
            public int index, value;
            public Node(int i, int v) { index = i; value = v; }
        }

        private class NodeComparator implements Comparator<Node> {
            public int compare(Node n1, Node n2) { return n1.value - n2.value; }
        }

        public int[] twoSum(int[] numbers, int target) {

            int[] ans = new int[2];
            if (numbers.length <= 1) return ans;

            ArrayList<Node> nodes = new ArrayList<Node>();
            for (int i = 0; i < numbers.length; ++i) {
                nodes.add(new Node(i + 1, numbers[i]));
            }
            
            Collections.sort(nodes, new NodeComparator());

            int left = 0, right = nodes.size() - 1;
            while (left < right) {
                if (nodes.get(left).value + nodes.get(right).value < target)
                    left++;
                else if (nodes.get(left).value + nodes.get(right).value > target)
                    right--;
                else break;
            }

            ans[0] = nodes.get(left).index;
            ans[1] = nodes.get(right).index;

            Arrays.sort(ans);
            return ans;
        }
    }
}
