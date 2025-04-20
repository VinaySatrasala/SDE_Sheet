public class LongestCommonSubstring {
    public static int lcs(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1) ==  s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max,dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }

        return max;


    }
    public static void main(String[] args) {
        System.out.println(lcs("abcds", "mbc"));
    }
}
