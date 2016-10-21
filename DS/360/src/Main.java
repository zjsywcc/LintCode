import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 7, 8, 5};
        ArrayList<Integer> list  = medianSlidingWindow(nums, 3);
        printList(list);
    }

    public static ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result  = new ArrayList<Integer>();
        if(nums == null || nums.length == 0 || k == 0 || k > nums.length) {
            return result;
        }
        int len = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(len,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                }
        );
        for(int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
            minHeap.offer(maxHeap.poll());
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        result.add(maxHeap.peek());
        for(int i = 1, j = k; j < len; i++, j++) {
            if(maxHeap.contains(nums[i - 1])) {
                maxHeap.remove(nums[i - 1]);
            } else {
                minHeap.remove(nums[i - 1]);
            }
            maxHeap.offer(nums[j]);
            minHeap.offer(maxHeap.poll());
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            result.add(maxHeap.peek());
        }
        return result;
    }

    public static void printList(ArrayList<Integer> list) {
        for(int i : list) {
            System.out.print(String.format("%s ", i));
        }
        System.out.println();
    }
}
