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

    public static void main(String[] args) {
        int[] coins = {9,6,5,1};
        System.out.println(recursive(coins, 11));
    }
}
