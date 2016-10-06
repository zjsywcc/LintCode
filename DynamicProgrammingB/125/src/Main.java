public class Main {

    public static void main(String[] args) {
        int m = 10;
        int[] A = new int[]{2, 3, 5, 7};
        int[] V = new int[]{1, 5, 2, 4};
        System.out.println(backPackII(m, A, V));
    }

    public static int backPackII(int m, int[] A, int V[]) {
        if(A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[][] dp = new int[len + 1][m + 1];
        for(int i = 0; i < m + 1; i++) {
            dp[0][i] = 0;
        }
        for(int i = 1; i < len + 1; i++) {
            for(int j = 0; j < m + 1; j++) {
                if(j >= A[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][m];
    }
}
