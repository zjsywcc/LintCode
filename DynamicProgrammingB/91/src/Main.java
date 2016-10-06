import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(4);
        A.add(2);
        A.add(3);
        int target = 1;
        System.out.println(MinAdjustmentCost(A, target));
    }

    //TODO to do this problem twice
    public static int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        if(A == null || A.size() == 0) {
            return 0;
        }
        int[][] dp = new int[A.size()][101];

        int size = A.size();

        for(int i = 0; i < size; i++) {
            for(int j = 1; j <= 100; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if( i == 0) {
                    dp[i][j] = Math.abs(j - A.get(i));
                } else {
                    for(int k = 1; k <= 100; k++) {
                        if(Math.abs(j - k) > target) {
                            continue;
                        }
                        int dif = Math.abs(j - A.get(i)) + dp[i - 1][k];
                        dp[i][j] = Math.min(dp[i][j], dif);
                    }
                }
            }
        }
        int ret = Integer.MAX_VALUE;
        for(int i = 1; i <= 100; i++) {
            ret = Math.min(ret, dp[size - 1][i]);
        }
        return ret;
    }
}
