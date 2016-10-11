public class Main {

    public static void main(String[] args) {
        int a[] = new int[]{45, 39, 32, 11};
        int b[] = new int[]{42,30,27,93,8,34,47,64,82,76,70,79,23,5,67,9,97,29,7,61,73,3,44,85,51,49,90,59,38,55,39,62,54,81,26,50,22,71,52,41,77,32,2,96,84,20,48,17,87,94,12,13,89,24,6,74,69,78,65,35,15,28,25,16,33,63};
        int c[] = new int[]{6, 4, 20};
        int d[] = new int[]{5, 4, 3, 2, 1};
        heapify(d);
        printList(d);
    }

    public static void heapify(int[] A) {
        if(A == null || A.length == 0) {
            return;
        }
        int len = A.length;
        for(int i = len / 2; i >= 0; i--) {
            siftDown(A, i);
        }
    }

    public static void siftDown(int[] tree, int i) {
        int len = tree.length;
        while(i * 2 + 1 < len) {
            //Very ugly if
            if (tree[i] > tree[2 * i + 1] || (i * 2 + 2 < len && tree[i] > tree[2 * i + 2])) {
                if(2 * i + 2 > len - 1) {
                    int temp = tree[i];
                    tree[i] = tree[2 * i + 1];
                    tree[2 * i + 1] = temp;
                    i = 2 * i + 1;
                } else {
                    if(tree[2 * i + 1] < tree[2 * i + 2]) {
                        int temp = tree[i];
                        tree[i] = tree[2 * i + 1];
                        tree[2 * i + 1] = temp;
                        i = 2 * i + 1;
                    } else {
                        int temp = tree[i];
                        tree[i] = tree[2 * i + 2];
                        tree[2 * i + 2] = temp;
                        i = 2 * i + 2;
                    }
                }
            } else {
                break;
            }
        }
    }


    //Fine version
    public static void siftDown1(int[] A, int k) {
        while (k < A.length) {
            int smallest = k;
            if (k * 2  < A.length && A[k * 2] < A[smallest]) {
                smallest = k * 2;
            }
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) {
                smallest = k * 2 + 1;
            }
            if (smallest == k) {
                break;
            }
            int temp = A[smallest];
            A[smallest] = A[k];
            A[k] = temp;

            k = smallest;
        }
    }

    public static void printList(int[] result) {
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
