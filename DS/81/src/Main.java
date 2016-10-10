import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4, 5};
        int[] nums2 = new int[]{4, 5, 1, 3, 2, 6, 0};
        int[] nums3 = new int[]{2, 20, 100};
        printList(medianII(nums1));
        printList(medianII(nums2));
        printList(medianII(nums3));
    }



    public static int[] medianII(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(len,
            new Comparator<Integer>() {
                @Override
                public int compare(Integer i1, Integer i2) {
                    return i2 - i1;
                }
            });
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len);

        int[] result = new int[len];
        for(int i = 0; i < len; i++) {
            maxHeap.offer(nums[i]);
            minHeap.offer(maxHeap.poll());
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            result[i] = maxHeap.peek();

        }
        return result;
    }

    public static void printList(int[] result) {
        for(int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printHeap(PriorityQueue<Integer> heap) {
        if(!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }
        System.out.println();
    }
}
