public class Main {

    public static void main(String[] args) {
        int[] v1 = new int[]{3, 2, 2};
        int[] v2 = new int[]{1, 2, 4};
        int[] v3 = new int[]{1, 20, 4};
        System.out.println(firstWillWin(v3));
    }

    public static boolean firstWillWin(int[] values) {
        if(values == null) {
            return false;
        }
        int len = values.length;
        boolean[][] flag = new boolean[len][len];
        int[][] dp = new int[len][len];
        int sum = 0;
        for(int i = 0; i < len; i++) {
            sum += values[i];
        }
        return MemorySearch(0, len - 1, dp, flag, values) > sum / 2;
    }

    public static int MemorySearch(int i, int j, int[][] dp, boolean[][] flag, int[] values) {
        if(flag[i][j]) {
            return dp[i][j];
        }
        if(i > j) {
            dp[i][j] = 0;
        } else if(i == j){
            dp[i][j] = values[i];
        } else if(j == i + 1) {
            dp[i][j] = Math.max(values[i], values[j]);
        } else {
            int pickLeft = Math.min(MemorySearch(i + 2, j, dp, flag, values),
                                    MemorySearch(i + 1, j - 1, dp, flag, values)) +
                                    values[i];
            int pickRight = Math.min(MemorySearch(i, j - 2, dp, flag, values),
                                     MemorySearch(i + 1, j - 1, dp, flag, values)) +
                                     values[j];
            dp[i][j] = Math.max(pickLeft, pickRight);
        }
        flag[i][j] = true;
        return dp[i][j];
    }
}
