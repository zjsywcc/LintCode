public class Main {

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4, 3};
        int k = 4;
        int target = 6;
        System.out.println(kSum(A, k, target));
    }

    public static int kSum(int A[], int k, int target) {
        if(A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        int[][][] dp = new int[len + 1][target + 1][k + 1];
        for(int i = 0; i < len + 1; i++) {
            dp[i][0][0] = 1;
        }
        for(int i = 1; i < len + 1; i++) {
            for(int j = 0; j < target + 1; j++) {
                for(int x = 1; x < k + 1; x++) {
                    if(j >= A[i - 1]) {
                        dp[i][j][x] = dp[i - 1][j][x] +
                                        dp[i - 1][j - A[i - 1]][x - 1];
                    } else {
                        dp[i][j][x] = dp[i - 1][j][x];
                    }
                }
            }
        }
        return dp[len][target][k];
    }
}
