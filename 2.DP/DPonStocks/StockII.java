public class StockII {
    public int maxProfit(int[] prices) {
        return 0;
    }
    public int recursive(int[] prices,int index,int canBuy){
        if(index == prices.length){
            return 0;
        }
        int profit = 0;
        if(canBuy == 1){
            profit = Math.max( - prices[index] + recursive(prices, index + 1, 0), 0 + recursive(prices, index + 1, canBuy));
        }else{
            profit = Math.max( +prices[index] + recursive(prices, index + 1, 1), 0 + recursive(prices, index + 1, canBuy));
        }
        return profit;
    }

    public int memoization(int[] prices,int index,int canBuy,int[][] dp){
        if(index == prices.length){
            return 0;
        }
        if(dp[index][canBuy] != -1) return dp[index][canBuy];
        int profit = 0;
        if(canBuy == 1){
            profit = Math.max( - prices[index] + recursive(prices, index + 1, 0), 0 + recursive(prices, index + 1, canBuy));
        }else{
            profit = Math.max( +prices[index] + recursive(prices, index + 1, 1), 0 + recursive(prices, index + 1, canBuy));
        }
        return dp[index][canBuy] = profit;
    }

    public int tabulation(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n][2];
        for(int index=n-1;index>=0;index--){
            dp[index][1] = Math.max( - prices[index] + dp[index+1][0], 0 + dp[index +1][1]);
            dp[index][0] = Math.max( +prices[index] + dp[index + 1][1], 0 + dp[index + 1][0]);
        }
        return dp[0][1];
    }

    public int constantSpace(int[] prices){
        int n = prices.length;
        int[] dp = new int[2];
        int[] curr = new int[2];
        for(int index=n-1;index>=0;index--){
            curr[1] = Math.max( - prices[index] + dp[0], 0 + dp[1]);
            curr[0] = Math.max( +prices[index] + dp[1], 0 + dp[0]);
            dp = curr;
        }
        return dp[1];
    }
    public static void main(String[] args) {
        
    }
}
