import java.util.Arrays;

public class MinimumCoins {
    public static int recursive(int[] coins, int target) {
        // Base case
        if (target == 0) {
            return 0;
        }
    
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= target) {
                int rec = recursive(coins, target - coin);
                if (rec != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + rec);
                }
            }
        }
    
        return min == Integer.MAX_VALUE ? -1 : min;
    }
     
    public static int memoization(int[] coins, int target,int[] dp) {
        // Base case
        if (target == 0) {
            return dp[0] = 0;
        }
        if(dp[target] != -1) return dp[target];
    
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= target) {
                int rec = recursive(coins, target - coin);
                if (rec != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + rec);
                }
            }
        }
    
        return dp[target] = min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int tabulation(int[] coins,int tg){
        int[] dp = new int[tg + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int target = 1;target <= tg;target++){
            for (int coin : coins) {
                if (coin <= target) {
                    dp[target] = Math.min(dp[target], 1 + dp[target - coin]);
                }
            }
        }
        return dp[tg] == Integer.MAX_VALUE ? -1 : dp[tg];
    }

    public int recursive2(int[] coins,int index,int target){
        if(index == 0){
            // can write the denomination
            if(target % coins[0] == 0){
                return target / coins[0];
            }
        }
        int reject = recursive2(coins, index - 1, target);

        int take = Integer.MAX_VALUE;
        if(coins[index] <= target){
            take = 1 + recursive2(coins, index , target - coins[index]);
        }

        return Math.min(reject, take);
    }

    public int memoization2(int[] coins,int index,int target,int[][] dp){
        if(index == 0){
            // can write the denomination
            if(target % coins[0] == 0){
                dp[0][target] = target / coins[0];
                return target / coins[0];
            } 
            return dp[0][target] =  Integer.MAX_VALUE;
        }

        if(dp[index][target] != -1) return dp[index][target];
        int reject = memoization2(coins, index - 1, target,dp);

        int take = Integer.MAX_VALUE;
        if(coins[index] <= target){
            take = 1 + memoization2(coins, index , target - coins[index],dp);
        }

        return dp[index][target] = Math.min(reject, take);
    }

    public int tabulation2(int[] coins,int tg){
        int n = coins.length;
        int[][] dp = new int[n][tg + 1];
        for(int i = 0;i<=tg;i++){
            if(coins[0] <= i && i % coins[0] == 0){
                dp[0][i] = i / coins[0];
            }else{
                dp[0][i] = Integer.MAX_VALUE;
            }
        }

        for(int index = 1;index < n;index++){
            for(int target = 0;target <= tg ;target++){
                int reject = dp[index - 1][target];

                int take = Integer.MAX_VALUE;
                if(coins[index] <= target){
                    take = 1 + dp[index][target - coins[index]];
                }
        
                dp[index][target] = Math.min(reject, take);
            }
        }

        return dp[n-1][tg];
    }

    public int constantSpaceOptimized(int[] coins, int tg) {
        int[] dp = new int[tg + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // Base case: Fill first row
        for (int i = 0; i <= tg; i++) {
            if (i % coins[0] == 0) {
                dp[i] = i / coins[0];
            }
        }
    
        for (int index = 1; index < coins.length; index++) {
            for (int target = 0; target <= tg; target++) {
                int reject = dp[target];
                int take = Integer.MAX_VALUE;
                if (coins[index] <= target && dp[target - coins[index]] != Integer.MAX_VALUE) {
                    take = 1 + dp[target - coins[index]];
                }
                dp[target] = Math.min(reject, take);
            }
        }
    
        return dp[tg] == Integer.MAX_VALUE ? -1 : dp[tg];
    }
    
    public static void main(String[] args) {
        int[] coins = {9,6,5,1};
        System.out.println(recursive(coins, 11));
    }
}
