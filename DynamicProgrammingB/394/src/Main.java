public class Main {

    public static void main(String[] args) {
        System.out.println(firstWillWin(5));
    }

    // For version:
    public static boolean firstWillWin1(int n) {
        if(n == 0 || n == 3) {
            return false;
        }
        if(n == 1 || n == 2 || n == 4) {
            return true;
        }
        boolean[] dp = new boolean[n + 1];
        dp[1] = true;
        dp[2] = true;
        dp[3] = false;
        dp[4] = true;
        for(int i = 5; i < n + 1; i++) {
            dp[i] = (dp[i - 4] && dp[i - 3]) || (dp[i - 3] && dp[i - 2]);
        }
        return dp[n];
    }

    // Recursive with memory version:
    public static boolean firstWillWin(int n) {
        boolean[] dp = new boolean[n + 1];
        boolean[] flag = new boolean[n + 1];
        return MemorySearch(n, dp, flag);
    }

    public static boolean MemorySearch(int i, boolean[] dp, boolean[] flag) {
        if(flag[i]) {
            return dp[i];
        }
        if(i == 0) {
            dp[i] = false;
        } else if(i == 1) {
            dp[i] = true;
        } else if(i == 2) {
            dp[i] = true;
        } else if(i == 3){
            dp[i] = false;
        } else {
            if((MemorySearch(i - 2, dp, flag) && MemorySearch(i - 3, dp, flag)) ||
                    (MemorySearch(i - 3, dp, flag) && MemorySearch(i - 4, dp, flag) )) {
                dp[i] = true;
            } else {
                dp[i] = false;
            }
        }
        flag[i] = true;
        return dp[i];
    }
}
