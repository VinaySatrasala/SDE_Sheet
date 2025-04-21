public class StocksIII {
    public int recursive(int[] prices,int index,int canBuy,int cap){
        if(cap == 0) return 0;
        if(index == prices.length) return 0;

        if(canBuy == 1){
            int buy = -prices[index] + recursive(prices, index + 1, 0, cap--);
            int skip = recursive(prices, index + 1, canBuy, cap);
            return Math.max(buy, skip);
        }else{
            int sell = prices[index] + recursive(prices, index + 1, 1, cap);
            int skip = recursive(prices, index + 1, 0, cap);
            return Math.max(sell, skip);
        }
    }
    public int memoization(int[] prices,int index,int canBuy,int cap,int[][][] dp){
        if(cap == 0) return 0;
        if(index == prices.length) return 0;
        if(dp[index][canBuy][cap] != -1) return dp[index][canBuy][cap];
        if(canBuy == 1){
            int buy = -prices[index] + memoization(prices, index + 1, 0, cap,dp);
            int skip = memoization(prices, index + 1, canBuy, cap,dp);
            return dp[index][canBuy][cap] =  Math.max(buy, skip);
        }else{
            int sell = prices[index] + memoization(prices, index + 1, 1, cap -1,dp);
            int skip = memoization(prices, index + 1, 0, cap,dp);
            return dp[index][canBuy][cap] = Math.max(sell, skip);
        }
    }
    public int tabulation(int[] prices){
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];
        for(int index=n-1;index>=0;index--){
            for(int canBuy = 0;canBuy<2;canBuy++){
                for(int cap=1;cap<3;cap++){
                    if(canBuy == 1){
                        int buy = -prices[index] + dp[index + 1][0][cap];
                        int skip = dp[index + 1][canBuy][cap];
                        dp[index][canBuy][cap] =  Math.max(buy, skip);
                    }else{
                        int sell = prices[index] + dp[index + 1][1][cap -1];
                        int skip = dp[index + 1][0][cap];
                        dp[index][canBuy][cap] = Math.max(sell, skip);
                    }
                }
            }
        }
        return dp[0][1][2];
    }

    public int constantSpace(int[] prices){
        int n = prices.length;
        int[][] dp = new int[2][3];
        int[][] curr = new int[2][3];
        for(int index=n-1;index>=0;index--){
            for(int canBuy = 0;canBuy<2;canBuy++){
                for(int cap=1;cap<3;cap++){
                    if(canBuy == 1){
                        int buy = -prices[index] + dp[0][cap];
                        int skip = dp[canBuy][cap];
                        curr[canBuy][cap] =  Math.max(buy, skip);
                    }else{
                        int sell = prices[index] + dp[1][cap -1];
                        int skip = dp[0][cap];
                        curr[canBuy][cap] = Math.max(sell, skip);
                    }
                }
            }
            dp = curr;
        }
        return dp[1][2];
    }
}
