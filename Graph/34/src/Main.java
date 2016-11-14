import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println(totalNQueens(1));
    }

    public static int totalNQueens(int n) {
        if(n <= 0) {
            return 0;
        }
        int[] sum = new int[1];
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = i;
        }
        helper(sum, list, nums);
//        printLists(results);
        return sum[0];
    }

    public static void helper(int[] sum, ArrayList<Integer> list, int[] nums) {
        if(list.size() == nums.length) {
            sum[0] += 1;
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(list.size() != 0){
                if(list.contains(nums[i])) {
                    continue;
                }
                int crtX = list.size();
                int crtY = nums[i];
                boolean flag = false;
                for(int j = 0; j < list.size(); j++) {
                    if(j + list.get(j) == crtX + crtY || j - list.get(j) == crtX - crtY) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    continue;
                }
            }
            list.add(nums[i]);
            helper(sum, list, nums);
            list.remove(list.size() - 1);
        }
    }
}
