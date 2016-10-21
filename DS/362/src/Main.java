import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 7, 7, 8};
        int[] nums = new int[]{1734, 806, 1929, 1057, 1117, 14, 474, 67, 278};
        ArrayList<Integer> list  = maxSlidingWindow(nums, 6);
        printList(list);
    }

    public static class Tuple {
        int index;
        int num;

        public Tuple(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    public static ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(nums == null || nums.length == 0 || k > nums.length) {
            return result;
        }
        int len = nums.length;
        ArrayDeque<Tuple> deque = new ArrayDeque<Tuple>();
        for(int i = 0; i < k; i++) {
            while(!deque.isEmpty() && nums[i] > deque.peekFirst().num) {
                deque.removeFirst();
            }
            deque.offerFirst(new Tuple(nums[i], i));
        }
        result.add(deque.peekLast().num);
        for(int i = 1, j = k; j < len; i++, j++) {
            if(deque.peekLast().index < i) {
                deque.removeLast();
            }
            while(!deque.isEmpty() && nums[j] > deque.peekFirst().num) {
                deque.removeFirst();
            }
            deque.offerFirst(new Tuple(nums[j], j));
            result.add(deque.peekLast().num);
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
