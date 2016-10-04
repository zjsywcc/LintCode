public class Main {

    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 2, 4};
        System.out.println(firstWillWin(values));
    }

    // For version:
    public static boolean firstWillWin1(int[] values) {
        if(values == null) {
            return false;
        }
        int n = values.length;
        int[] dp = new int[n + 1];
        if(n <= 0) {
            return false;
        } else if(n == 1) {
            return true;
        } else if(n == 2) {
            return true;
        }
        dp[0] = 0;
        dp[1] = values[n - 1];
        dp[2] = values[n - 2] + values[n - 1];
        dp[3] = values[n - 3] + values[n - 2];
        int sum = 0;
        for(int j = 0; j < 4 && j < n; j++) {
            sum += values[j];
        }
        for(int i = 4; i < n + 1; i++) {
            if (i != n) {
                sum += values[i];
            }
            dp[i] = Math.max(Math.min(dp[i - 4], dp[i - 3]) + values[n - i]
                    + values[n - i + 1], Math.min(dp[i - 3], dp[i - 2]) + values[n - i]);
        }
        return dp[n] > sum / 2;
    }

    // Reverse with memory version:
    public static boolean firstWillWin(int[] values) {
        int[] dp = new int[values.length + 1];
        boolean[] flag = new boolean[values.length + 1];
        int sum = 0;
        for(int crt : values) {
            sum += crt;
        }
        return MemorySearch(values.length, dp, flag, values) > sum / 2;
    }

    public static int MemorySearch(int i, int[] dp, boolean[] flag, int[] values) {
        if(flag[i]) {
            return dp[i];
        }
        flag[i] = true;
        int len = values.length;
        if(i == 0) {
            dp[i] = 0;
        } else if(i == 1) {
            dp[i] = values[len - 1];
        } else if(i == 2) {
            dp[i] = values[len - 1] + values[len - 2];
        } else if(i == 3) {
            dp[i] = values[len - 2] + values[len - 3];
        } else {
            dp[i] = Math.max(
                    Math.min(MemorySearch(i - 2, dp, flag, values),
                            MemorySearch(i - 3, dp, flag, values)) + values[len - i],
                    Math.min(MemorySearch(i - 3, dp, flag, values),
                            MemorySearch(i - 4, dp, flag, values)) + values[len - i] + values[len - i + 1]);

        }
        return dp[i];
    }
}
