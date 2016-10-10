import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{-3, 1, 2, -3, 4};
        int[] nums1 = new int[]{0};
        ArrayList<Integer> list = subarraySum(nums1);
        for(int i : list) {
            System.out.print(i + " ");
        }
    }

    public static ArrayList<Integer> subarraySum(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            int sum = nums[i];
            if(sum == 0) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(i);
                return list;
            }
            for(int j = i + 1; j < len; j++) {
                sum += nums[j];
                if(sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    return list;
                }
            }
        }
        return null;
    }
}
