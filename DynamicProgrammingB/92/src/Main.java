public class Main {

    public static void main(String[] args) {
        int m = 12;
        int[] A = new int[]{2, 3, 5, 7};
        System.out.println(backPack(m, A));
    }

    //For version dp
    public static int backPack1(int m, int[] A) {
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
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][m];
    }

    //Recurse with memory
    public static int backPack(int m, int[] A) {
        if(A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[][] dp = new int[len + 1][m + 1];
        boolean[][] flag = new boolean[len + 1][m + 1];
        return backPackHelper(len, m, A, dp, flag);
    }

    public static int backPackHelper(int i, int j, int[] A, int[][] dp, boolean[][] flag) {
        if(flag[i][j]) {
            return dp[i][j];
        }
        if(i == 0) {
            dp[i][j] = 0;
            return 0;
        }
        if(j >= A[i - 1]) {
            dp[i][j] = Math.max(backPackHelper(i - 1, j, A, dp, flag),
                                backPackHelper(i - 1, j - A[i - 1], A, dp, flag) + A[i - 1]);
        } else {
            dp[i][j] = backPackHelper(i - 1, j, A, dp, flag);
        }
        flag[i][j] = true;
        return dp[i][j];
    }
}
