public class CoinChangeII {
    public int recursive(int[] coins,int index,int target){
        if(index == 0){
            // System.out.println("f(" + index + "," + target + ")");
            // Case 1 : reject zeroth element
            if(target == 0) return 1;
            // Case 2 : Take zeroth element
            int take = target % coins[0] == 0 ? 1 : 0;
            return take;
        }

        int reject = recursive(coins,index - 1,target);
        int take = 0;
        if(coins[index] <= target){
            take = recursive(coins,index,target - coins[index]);
        }
        return take + reject;
    }

    public int memoization(int[] coins,int index,int target,int[][] dp){
        if(index == 0){
            if(target == 0){
                return dp[index][target] = 1;
            }
            return dp[index][target] = (target % coins[0] == 0 ? 1 : 0);
        }


        if(dp[index][target] != -1) return dp[index][target];

        int reject = memoization(coins, index - 1, target, dp);
        int take = 0;
        if(coins[index] <= target) {
            take = memoization(coins,index,target - coins[index],dp);
        }


        return dp[index][target] = take + reject;
    }

    public int tabulation(int[] coins,int tg){
        int n = coins.length;
        int[][] dp = new int[n][tg + 1];

        // Base case 
        dp[0][0] = 1;

        for(int i = 1;i<=tg;i++){
            if(i % coins[0] == 0){
                dp[0][i] = 1;
            }
        }

        for(int index=1;index<n;index++){
            for(int target=0;target<=tg;target++){
                int reject = dp[index - 1][target];
                int take = 0;
                if(coins[index] <= target){
                    take = dp[index][target-coins[index]];
                }
                dp[index][target] = take + reject;
            }
        }
        return dp[n-1][tg];
    }

    public int constantSpace(int[] coins,int tg){
        int n = coins.length;
        int[] dp = new int[tg + 1];
        dp[0] = 1;
        for(int i=1;i<=tg;i++){
            dp[i] = i % coins[0] == 0 ? 1 : 0;
        }

        for(int index=1;index<n;index++){
            int[] curr = new int[tg + 1];
            for(int target=0;target<=tg;target++){
                int reject = dp[target];
                int take = 0;
                if(coins[index] <= target){
                    take = curr[target-coins[index]];
                }
                curr[target] = take + reject;
            }
            dp = curr;
        }
        return dp[tg];
    }
}
