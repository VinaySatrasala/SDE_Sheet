public class PolindromePartitiong2 {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n +1];
        for(int i = n-1;i>=0;i--){
            int min = Integer.MAX_VALUE;
            for(int ind = i;ind<s.length();ind++){
                if(check(i, ind, s)){
                    int cost = 1 + f2(ind+1, s,dp);
                    min = Math.min(min, cost);
                }
            }
    
            dp[i] = min;
        }
        return dp[0];
    }
    public int f(int i,int j,String s,int[][] dp){
        if(i == j) return 0;
        if(check(i,j,s)) return dp[i][j] =  0;
        int min = Integer.MAX_VALUE;
        for(int ind = i+1;ind <= j;ind++){
           int min_left = f(i, ind - 1, s,dp);
           int min_right = f(ind,j,s,dp);
           int cost = min_left + 1 + min_right;
           min = Math.min(min, cost);
        }
        return dp[i][j] = min;
    }


    // method uses front partition
    public int f2(int i,String s,int[] dp){
        if(i == s.length()) return 0;
        if(dp[i] != -1) return dp[i];
        if(check(i, s.length() - 1, s)) return dp[i] =  0;
        int min = Integer.MAX_VALUE;
        for(int ind = i;ind<s.length();ind++){
            if(check(i, ind, s)){
                int cost = 1 + f2(ind+1, s,dp);
                min = Math.min(min, cost);
            }
        }

        return dp[i] = min;
    }
    public boolean check(int i,int j,String s){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
