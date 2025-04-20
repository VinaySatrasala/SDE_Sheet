public class DeleteOperationforTwoStrings {
    public int minDistance(String s1, String s2) {
        // Base cases for i = 0 what ever j is put is as 0
        // Base case for j = 0 what ever i put it as 0
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = 1 + dp[j - 1];
                } else {
                    curr[j] = Math.max(curr[j - 1], dp[j]);
                }
            }
            dp = curr;
        }
        int lcs = dp[n];
        return s1.length() + s2.length() - 2 * lcs;
    }
}
