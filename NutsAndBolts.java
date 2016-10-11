import java.util.Collections;
import java.util.Arrays;

public class NutsAndBolts {
    // http://www.lintcode.com/en/problem/nuts-bolts-problem/

    /**
     * public class NBCompare {
     *     public int cmp(String a, String b);
     * }
     * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
     * if "a" is bigger than "b", it will return 1, else if they are equal,
     * it will return 0, else if "a" is smaller than "b", it will return -1.
     * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
     */
    public static class NBComparator {
        public int cmp(String a, String b) { return a.toLowerCase().compareTo(b.toLowerCase()); }
    }

    public static class Solution {
        /**
         * @param nuts: an array of integers
         * @param bolts: an array of integers
         * @param compare: a instance of Comparator
         * @return: nothing
         */
        public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {

            int n = nuts.length;
            int m = bolts.length;
            if (n == 0 || m == 0 || n != m) return;

            sort(nuts, bolts, 0, n - 1, compare);
        }

        private void sort(String[] nuts, String[] bolts, int start, int end, NBComparator compare) {

            //System.out.println("nuts: " + Arrays.toString(nuts) + ", bolts: " + Arrays.toString(bolts) + ", start: " + start + ", end: " + end);

            if (end <= start) return;

            int mid = partitionBolts(nuts[start], bolts, start, end, compare);
            //System.out.println("after partition: nuts[start]: " + nuts[start] + ", bolts: " + Arrays.toString(bolts) + ", start: " + start + ", end: " + end);
            partitionNuts(bolts[mid], nuts, start, end, compare);
            //System.out.println("after partition: bolts[mid]: " + bolts[mid] + ", nuts: " + Arrays.toString(nuts) + ", start: " + start + ", end: " + end);

            sort(nuts, bolts, start, mid - 1, compare);
            sort(nuts, bolts, mid + 1, end, compare);
        }

        private int partitionNuts(String bolt, String[] nuts, int start, int end, NBComparator compare) {
            for (int k = start; k <= end; ++k)
                if (compare.cmp(nuts[k], bolt) == 0) {
                    exch(nuts, start, k);
                    break;
                }

            int i = start, j = end + 1;
            while (i < j) {
                while (compare.cmp(nuts[++i], bolt) < 0)
                    if (i == end) break;

                while (compare.cmp(nuts[--j], bolt) > 0)
                    if (j == start) break;

                if (i >= j) break;
                exch(nuts, i, j);
            }

            exch(nuts, start, j);
            return j;
        }

        private int partitionBolts(String nut, String[] bolts, int start, int end, NBComparator compare) {
            for (int k = start; k <= end; ++k)
                if (compare.cmp(nut, bolts[k]) == 0) {
                    exch(bolts, start, k);
                    break;
                }

            int i = start, j = end + 1;
            while (i < j) {
                while (compare.cmp(nut, bolts[++i]) > 0)
                    if (i == end) break;

                while (compare.cmp(nut, bolts[--j]) < 0)
                    if (j == start) break;

                if (i >= j) break;
                exch(bolts, i, j);
            }
            
            exch(bolts, start, j);
            return j;
        }

        private void exch(String[] A, int i, int j) {
            String tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
    };

    public static void main(String[] args) {

        String[] nuts = {"ab","bc","dd","gg"};
        String[] bolts = {"AB","GG","DD","BC"};

        Solution s = new Solution();
        System.out.println("Original nuts:");
        System.out.println(Arrays.toString(nuts));
        System.out.println("Original bolts:");
        System.out.println(Arrays.toString(bolts));

        s.sortNutsAndBolts(nuts, bolts, new NBComparator());

        System.out.println("sorted nuts:");
        System.out.println(Arrays.toString(nuts));
        System.out.println("sorted bolts:");
        System.out.println(Arrays.toString(bolts));
    }
}
