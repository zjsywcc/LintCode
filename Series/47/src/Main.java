import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Integer[] nums = new Integer[]{1, 2, 1, 2, 1, 3, 3};
        Integer[] nums = new Integer[]{1,1,1,1,2,2,3,3,4,4,4};
        List<Integer> list = Arrays.asList(nums);
        ArrayList<Integer> arrayList = new ArrayList<>(list);
        System.out.println(majorityNumber1(arrayList));
    }

    public static int majorityNumber(ArrayList<Integer> nums) {
        int x1 = 0, x2 = 0, count1 = 0, count2 = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(count1 == 0) {
                x1 = nums.get(i);
                count1++;
            } else if(count2 == 0) {
                x2 = nums.get(i);
                count2++;
            } else if(x1 != nums.get(i) && x2 != nums.get(i)) {
                count1--;
                count2--;
            } else if(x1 == nums.get(i)){
                count1++;
            } else if(x2 == nums.get(i)) {
                count2++;
            }
        }
        System.out.println(x1 + " " + x2);
        int countx1 = 0, countx2 = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(nums.get(i) == x1) {
                countx1++;
            }
            if(nums.get(i) == x2) {
                countx2++;
            }
        }
        return countx1 > countx2 ? x1 : x2;
    }

    public static int majorityNumber1(ArrayList<Integer> nums) {
        int x1 = 0, x2 = 0, count1 = 0, count2 = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(x1 == nums.get(i)) {
                count1++;
            } else if(x2 == nums.get(i)) {
                count2++;
            }
            else if(count1 == 0) {
                x1 = nums.get(i);
                count1++;
            } else if(count2 == 0) {
                x2 = nums.get(i);
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        System.out.println(x1 + " " + x2);
        int countx1 = 0, countx2 = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(nums.get(i) == x1) {
                countx1++;
            }
            if(nums.get(i) == x2) {
                countx2++;
            }
        }
        return countx1 > countx2 ? x1 : x2;
    }
}
