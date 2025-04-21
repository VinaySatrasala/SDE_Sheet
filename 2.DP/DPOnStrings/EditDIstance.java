public class EditDistance {
    // match i-1,j-1
    // replace 1 + i-1,j-1
    // insert 1 + i,j-1
    // delete 1 + i-1,j 

    public int recursive(String s1,String s2,int i,int j){
        if(i < 0){
            return j + 1;
        }
        if(j < 0){
            return i + 1;
        }
        

        if(s1.charAt(i) == s2.charAt(j)){
            return recursive(s1, s2, i - 1, j - 1);
        }

        int delete = 1 + recursive(s1, s2, i - 1, j);
        int update = 1 + recursive(s1, s2, i - 1, j - 1);
        int insert = 1 + recursive(s1, s2, i, j - 1);

        return Math.min(delete, Math.min(update, insert));
    }

    // index shifing to -1 to 0
    public int memoization(String s1,String s2,int i,int j,int[][] dp){
        if(i == 0){
            return j;
        }
        if(j == 0){
            return i;
        }
        
        if(dp[i][j] != -1) return dp[i][j];

        if(s1.charAt(i - 1) == s2.charAt(j - 1)){
            return dp[i][j] = memoization(s1, s2, i - 1, j - 1,dp);
        }

        int delete = 1 + memoization(s1, s2, i - 1, j,dp);
        int update = 1 + memoization(s1, s2, i - 1, j - 1,dp);
        int insert = 1 + memoization(s1, s2, i, j - 1,dp);

        return dp[i][j] = Math.min(delete, Math.min(update, insert));
    }

    public int tabulation(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n+1];
        // Base case i == 0 and will be left string => j
        for(int j=0;j<=n;j++){
            dp[0][j] = j;
        }

        for(int i=0;i<=m;i++){
            dp[i][0] = i;
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
        
                int delete = 1 + dp[i - 1][j];
                int update = 1 + dp[i - 1][j - 1];
                int insert = 1 + dp[i][j - 1];
        
                dp[i][j] = Math.min(delete, Math.min(update, insert));
            }
        }
        return dp[m][n];
    }

    public int constantSpce(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];

        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            curr[0] = i;
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = dp[j - 1];
                } else {
                    int delete = 1 + dp[j];
                    int insert = 1 + curr[j - 1];
                    int replace = 1 + dp[j - 1];
                    curr[j] = Math.min(delete, Math.min(insert, replace));
                }
            }
            dp = curr;
        }
        return dp[n];
    }

    public int constantSpaceOptimized(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];

        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            dp[0] += i;
            for (int j = n; j > 0; j--) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[j] = dp[j - 1];
                } else {
                    int delete = 1 + dp[j];
                    int insert = 1 + dp[j - 1];
                    int replace = 1 + dp[j - 1];
                    dp[j] = Math.min(delete, Math.min(insert, replace));
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        System.out.println(ed.recursive("horse","ros",4,2));
    }
}
