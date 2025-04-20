import java.util.Arrays;

public class DistinctSubsequences {
    public int recursive(String s,String t,int i,int j){
        if(j < 0){
            return 1;
        }
        if(i < 0){
            return 0;
        }

        if(s.charAt(i) == t.charAt(j)){
            return recursive(s, t, i - 1, j-1) + recursive(s, t, i-1, j);
        }
        return recursive(s, t, i-1, j);
    }

    public int memoization(String s,String t,int i,int j,int[][] dp){
        if(j < 0){
            return 1;
        }
        if(i < 0){
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];
        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] =  memoization(s, t, i - 1, j-1,dp) + memoization(s, t, i-1, j,dp);
        }
        return dp[i][j] = memoization(s, t, i-1, j,dp);
    }
    public int tabulation(String s,String t){
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1]; // accomedating -1 case

        // for all j when i = 0 then arr[i][0] = 0 default in java
        // for all i when j = 0 put 1
        for(int i=0;i<=n;i++){
            dp[i][0] = 1;
        } 

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] =  dp[i - 1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][m];
    }
    public int constantSpce(String s,String t){
        int n = s.length();
        int m = t.length();
        int[] dp = new int[m + 1]; // accomedating -1 case

        // for all j when i = 0 then arr[i][0] = 0 default in java
        // for all i when j = 0 put 1
        dp[0] = 1;

        for(int i=1;i<=n;i++){
            int[] curr = new int[m+1];
            curr[0] = 1;
            for(int j=1;j<=m;j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    curr[j] =  dp[j-1] + dp[j];
                }else{
                    curr[j] = dp[j];
                }
            }
            dp = curr;
        }
        return dp[m];
    }

    public int constantSpaceOptimized(String s,String t){
        int n = s.length();
        int m = t.length();
        int[] dp = new int[m + 1]; // accomedating -1 case

        // for all j when i = 0 then arr[i][0] = 0 default in java
        // for all i when j = 0 put 1
        dp[0] = 1;

        for(int i=1;i<=n;i++){
            for(int j=m;j>0;j--){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[j] =  dp[j-1] + dp[j];
                }else{
                    dp[j] = dp[j];
                }
            }
        }
        return dp[m];
    }
}
