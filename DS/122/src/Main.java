import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int[] height = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea(height));
    }

    public static int largestRectangleArea(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[] nums = new int[len + 2];
        for(int i = 0; i < len + 2; i++) {
            if(i == 0 || i == len + 1) {
                nums[i] = 0;
            } else {
                nums[i] = height[i - 1];
            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int max = 0;
        for(int i = 1; i < len + 2; i++) {
            int top = stack.lastElement();
            if(nums[i] >= nums[top]) {
                stack.push(i);
            } else {
                int sum;
                while(!stack.isEmpty() && nums[stack.lastElement()] > nums[i]) {
                    int h = stack.pop();
                    int ln = stack.lastElement();
                    int rn = i;
                    sum = (rn - ln - 1) * nums[h];
                    max = Math.max(max, sum);
                }
                stack.push(i);
            }
        }
        return max;
    }
}
