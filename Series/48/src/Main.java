import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>(){{
            add(3);
            add(1);
            add(2);
            add(3);
            add(2);
            add(3);
            add(3);
            add(4);
            add(4);
            add(4);
        }};
        System.out.println(majorityNumber(nums, 3));
    }

    //reduce the space using doesn't worth to spend time on this
    public static int majorityNumber(ArrayList<Integer> nums, int k) {
        // count at most k keys.
        HashMap<Integer, Integer> counters = new HashMap<Integer, Integer>();
        for (Integer i : nums) {
            if (!counters.containsKey(i)) {
                counters.put(i, 1);
            } else {
                counters.put(i, counters.get(i) + 1);
            }

            if (counters.size() >= k) {
                removeKey(counters);
            }
        }

        // corner case
        if (counters.size() == 0) {
            return Integer.MIN_VALUE;
        }

        // recalculate counters
        for (Integer i : counters.keySet()) {
            counters.put(i, 0);
        }
        for (Integer i : nums) {
            if (counters.containsKey(i)) {
                counters.put(i, counters.get(i) + 1);
            }
        }

        // find the max key
        int maxCounter = 0, maxKey = 0;
        for (Integer i : counters.keySet()) {
            if (counters.get(i) > maxCounter) {
                maxCounter = counters.get(i);
                maxKey = i;
            }
        }

        return maxKey;
    }

    private static void removeKey(HashMap<Integer, Integer> counters) {
        Set<Integer> keySet = counters.keySet();
        List<Integer> removeList = new ArrayList<>();
        for (Integer key : keySet) {
            counters.put(key, counters.get(key) - 1);
            if (counters.get(key) == 0) {
                removeList.add(key);
            }
        }
        for (Integer key : removeList) {
            counters.remove(key);
        }
    }

    public static int majorityNumber1(ArrayList<Integer> nums, int k) {
        if(nums == null || nums.size() == 0) {
            return -1;
        }
        int len = nums.size();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i : nums) {
            if(map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for(int key : map.keySet()) {
            if(map.get(key) > len / k) {
                return key;
            }
        }
        return -1;
    }
}
