public class Main {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 2, 3, 4, 5}, {16,17,24,23,6}, {15,18,25,22,7}, {14,19,20,21,8}, {13,12,11,10,9}};
        System.out.println(getNum(nums));
    }

    public static int getNum(int[][] nums) {
        int row = nums.length;
        int col;
        if(row == 0) {
            return 0;
        } else {
            col = nums[0].length;
        }
        int[][] dp = new int[row][col];
        int max = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(dp[i][j] == 0) {
                    max = Math.max(max, getNumHelper(nums, i, j, dp));
                }
            }
        }
        return max;
    }

    public static int getNumHelper(int[][] nums, int i, int j, int[][] dp) {
        if(dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = 0, down = 0, right = 0, left = 0;
        if(i > 0 && nums[i - 1][j] > nums[i][j]) {
            up = getNumHelper(nums, i - 1, j, dp);
        }
        if(i < nums.length - 1 && nums[i + 1][j] > nums[i][j]) {
            down = getNumHelper(nums, i + 1, j, dp);
        }
        if(j < nums[0].length - 1 && nums[i][j + 1] > nums[i][j]) {
            right = getNumHelper(nums, i, j + 1, dp);
        }
        if(j > 0 && nums[i][j - 1] > nums[i][j]) {
            left = getNumHelper(nums, i, j - 1, dp);
        }
        dp[i][j] = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        return dp[i][j];
    }
}
