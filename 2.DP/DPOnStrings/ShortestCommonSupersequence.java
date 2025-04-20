public class ShortestCommonSupersequence {
    public static String tabulation(String s1, String s2) {
        // Base cases for i = 0 what ever j is put is as 0
        // Base case for j = 0 what ever i put it as 0
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                sb.append(s1.charAt(i - 1));
                i--;
            } else {
                sb.append(s2.charAt(j - 1));
                j--;
            }
        }
        while (i > 0) {
            sb.append(s1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(s2.charAt(j - 1));
            j--;
        }

        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        System.out.println(tabulation("abc", "def"));
    }
}