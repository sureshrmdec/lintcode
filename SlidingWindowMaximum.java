import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;

public class SlidingWindowMaximum {
    // Sliding Window Maximum
    // http://www.lintcode.com/en/problem/sliding-window-maximum/

    public static class Solution {
        /**
         * @param nums: A list of integers.
         * @return: The maximum number inside the window at each moving.
         */


        private class MaxHashHeap {
            private int[] A;
            private int N;
            private HashMap<Integer, Integer> out2in;
            private HashMap<Integer, Integer> in2out;

            public MaxHashHeap(int size) {
                A = new int[size];
                N = 0;
                out2in = new HashMap<Integer, Integer>();
                in2out = new HashMap<Integer, Integer>();
            }

            public void add(int foreighid, int v) {
                //System.out.println("Before add: "); print();
                if (out2in.containsKey(foreighid)) {
                    //System.out.println("duplicate key: " + foreighid);
                    return;
                }

                A[N++] = v;
                in2out.put(N - 1, foreighid);
                out2in.put(foreighid, N - 1);
                swim(N-1);
                //System.out.println("After add: "); print();
            }

            public int peek() { return A[0]; }

            private void print() {
                //System.out.print("N = " + N + ", A = ");
                //for (int i = 0; i < N; ++i)
                    //System.out.print(A[i] + " ");
                //System.out.println();
                //System.out.println("in2out: " + in2out);
                //System.out.println("out2in: " + out2in);
            }

            private void delete(int foreighid) {
                //System.out.println("Before delete: "); print();
                int i = out2in.get(foreighid);
                exch(i, --N);

                // keeping should be fine though clean them should be fine
                in2out.remove(N);
                out2in.remove(foreighid);

                swim(i);
                sink(i);
                //System.out.println("After delete: "); print();
            }

            private void swim(int i) {
                while ((i - 1) / 2 >= 0) {
                    int j = (i - 1) / 2;
                    if (A[i] > A[j]) {
                        exch(i, j);
                        i = j;
                    }
                    else break;
                }
            }

            private void sink(int i) {
                while (2 * i + 1 <= N - 1) {
                    int j = 2 * i + 1;

                    if (j + 1 <= N - 1 && A[j + 1] > A[j]) j++;
                    if (A[i] < A[j]) {
                        exch(i, j);
                        i = j;
                    }
                    else break;
                }
            }

            private void exch(int i, int j) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                
                //System.out.println("Before exch out2in: " + i + ", " + j + ", "+ out2in);
                out2in.put(in2out.get(i), j);
                out2in.put(in2out.get(j), i);
                //System.out.println("After exch out2in: " + out2in);

                //System.out.println("Befor exch in2out: " + in2out);
                tmp = in2out.get(i);
                in2out.put(i, in2out.get(j));
                in2out.put(j, tmp);
                //System.out.println("After exch in2out: " + in2out);
            }
        }

        private ArrayList<Integer> results;

        public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
            results = new ArrayList<Integer>();
            if (nums.length < k) return results;

            MaxHashHeap heap = new MaxHashHeap(k + 1);
            for (int i = 0; i < nums.length; ++i) {
                heap.add(i, nums[i]);
                if (i >= k) heap.delete(i - k);

                if (i >= k - 1) results.add(heap.peek());
            }

            return results;
        }
    }

    public static void main(String[] args) {
        int nums1[] = {1,2,7,7,2,10,3,4,5};
        int k1 = 2;

        Solution s = new Solution();
        System.out.println(s.maxSlidingWindow(nums1, k1));
    }
}
