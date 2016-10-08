public class Heapify {
    // Heapify
    // http://www.lintcode.com/en/problem/heapify/

    public class Solution {
        /**
         * @param A: Given an integer array
         * @return: void
         */

        // convert 1-based to 0-based in less/exch as in algs4
        private class Heap {
            private int[] A;

            // 0-based array
            public Heap(int[] A) {
                this.A = A;
                for (int k = A.length/2; k >= 1; --k)
                    sink(k);
            }

//            private void swim(int p) {
//                while (p != 1) {
//                    if (less(p, p/2)) { exch(p, p/2); p = p/2; }
//                }
//            }

            private void sink(int p) {
                while (2 * p <= A.length) {
                    int j = 2 * p;
                    if (j + 1 <= A.length && less(j + 1, j)) j++;
                    if (!less(j, p)) break;
                    exch(j, p);
                    p = j;
                }
            }

            private boolean less(int i, int j) { return A[i-1] < A[j-1]; }

            private void exch(int i, int j) {
                int tmp = A[i-1];
                A[i-1] = A[j-1];
                A[j-1] = tmp;
            }
        }

        public void heapify(int[] A) {
            Heap heap = new Heap(A);
        }
    }

    public class Solution2 {
        private class Heap {
            private int[] A;
            public Heap(int[] A) { 
                this.A = A;
                for (int i = A.length/2 - 1; i >= 0; --i)
                    sink(i);
            }

            private void sink(int p) {
                while (2 * p + 1 < A.length) {
                    int j = 2 * p + 1;
                    if (j + 1 < A.length && A[j + 1] < A[j]) j++;
                    if (A[j] < A[p]) {
                        exch(j, p);
                        p = j;
                    }
                    else break;
                }
            }

            private void exch(int i, int j) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }


        public void heapify(int[] A) {
            Heap heap = new Heap(A);
        }
        
    }
}
