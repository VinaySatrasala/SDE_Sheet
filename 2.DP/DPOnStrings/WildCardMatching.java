import java.util.Arrays;

public class WildCardMatching {
    // - 1 to 0 indexing
    public boolean memoization(String s, String p, int i, int j, Boolean[][] dp) {
        if (i == 0) {
            for (int k = 0; k < j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }
        if (j == 0) return false;

        if (dp[i][j] != null) return dp[i][j];

        if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
            return dp[i][j] = memoization(s, p, i - 1, j - 1, dp);
        }

        if (p.charAt(j - 1) == '*') {
            return dp[i][j] = memoization(s, p, i, j - 1, dp) || memoization(s, p, i - 1, j, dp);
        }

        return dp[i][j] = false;
    }


    public boolean tabulation(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m][n];

        // when i =0 then for all j until char != '*' put as true;
        dp[0][0] = true; // empty string matches empty pattern

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1]; // '*' can match empty
            }
        }

        // Base 2 for all i when j = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false; // non-empty string can't match empty pattern
        }
        
        
        for(int i = 1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }else{
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        Boolean[][] dp = new Boolean[m][n];
        return memoization(s, p, m - 1, n - 1, dp);
    }


    public static void main(String[] args) {
        WildCardMatching wc = new WildCardMatching();
        System.out.println(wc.isMatch("adceb", "*a*b"));
    }
}
