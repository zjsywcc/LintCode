public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public int longestCommonSubsequence(String A, String B) {
        int len1 = A.length();
        int len2 = B.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i < len1 + 1; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < len2 + 1; j++) {
            dp[0][j] = 0;
        }
        for(int i = 0; i < len1 + 1; i++) {
            for(int j = 0; j < len2 + 1; j++) {
                if(A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i]
                }
            }
        }
    }
}
