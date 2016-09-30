public class Main {

    public static void main(String[] args) {
        String str1 = "www.lintcode.com code";
        String str2 = "www.ninechapter.com code";
        System.out.println(longestCommonSubsequence(str1, str2));
    }

    public static int longestCommonSubsequence(String A, String B) {
        int len1 = A.length();
        int len2 = B.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i < len1 + 1; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < len2 + 1; j++) {
            dp[0][j] = 0;
        }
        for(int i = 1; i < len1 + 1; i++) {
            for(int j = 1; j < len2 + 1; j++) {
                if(A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(Math.max(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
