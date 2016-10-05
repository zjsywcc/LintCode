public class Main {

    public static void main(String[] args) {
        int[] v1 = new int[]{4, 1, 1, 4};
        int[] v2 = new int[]{1, 1, 1, 1};
        int[] v3 = new int[]{4, 4, 5, 9};
        System.out.println(minCost(v1));
        System.out.println(minCost(v2));
        System.out.println(minCost(v3));
    }

    public static int minCost(int[] values) {
        if(values == null) {
            return 0;
        }
        int len = values.length;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            dp[i][i] = 0;
        }
        boolean[][] flag = new boolean[len][len];
        int[][] sum = new int[len][len];
        for(int i = 0; i < len; i++) {
            sum[i][i] = values[i];
            for(int j = i + 1; j < len; j++) {
                sum[i][j] = sum[i][j - 1] + values[j];
            }
        }
        return MemorySearch(0, len - 1, dp, flag, sum);
    }

    public static int MemorySearch(int i, int j , int[][] dp, boolean[][] flag, int[][] sum) {
        if(flag[i][j]) {
            return dp[i][j];
        }
        if(i == j) {
            dp[i][j] = 0;
        }
        int min = Integer.MAX_VALUE;
        for(int left = i; left < j; left++) {
            int temp = MemorySearch(i, left, dp, flag, sum) +
                       MemorySearch(left + 1, j, dp, flag, sum) +
                       sum[i][j];
            if(temp < min)
                min = temp;
            dp[i][j] = min;
        }
        flag[i][j] = true;
        return dp[i][j];
    }
}
