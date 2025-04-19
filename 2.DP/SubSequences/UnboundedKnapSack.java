import java.util.Arrays;

public class UnboundedKnapSack {
    public int recursive(int[] weights,int[] values,int index,int target){
        if(index == 0){
            int reject = 0;
            int take = 0;
            if(weights[index] <= target){
                take = (target / weights[index]) * values[index]; 
            }
            return Math.max(take,reject);
        }

        int reject = recursive(weights, values, index - 1, target);
        int take = 0;
        if(weights[index] <= target){
            take = values[index] + recursive(weights, values, index, target - weights[index]);
        }
        return Math.max(reject, take);
    }

    public int memoization(int[] weights,int[] values,int index,int target,int[][] dp){
        if(index == 0){
            int reject = 0;
            int take = 0;
            if(weights[index] <= target){
                take = (target / weights[index]) * values[index]; 
            }
            return dp[index][target] = Math.max(take,reject);
        }

        if(dp[index][target] != -1) return dp[index][target];

        int reject = memoization(weights, values, index - 1, target,dp);
        int take = 0;
        if(weights[index] <= target){
            take = values[index] + memoization(weights, values, index, target - weights[index],dp);
        }
        return dp[index][target] = Math.max(reject, take);
    }

    public int tabulation(int[] weights,int[] values,int tg){
        int n = weights.length;
        int[][] dp = new int[n][tg + 1];

        for(int i=0;i<=tg;i++){
            if(weights[0] <= i){
                dp[0][i] = (i / weights[0]) * values[0];
            }
        }

        for(int index=1;index<n;index++){
            for(int target=0;target <= tg;target++){

                int reject = dp[index - 1][target];
                int take = 0;
                if(weights[index] <= target){
                    take = values[index] + dp[index][target - weights[index]];
                }
                dp[index][target] = Math.max(reject, take);
            }
        }
        return dp[n-1][tg];
    }

    public int constantSpace(int[] weights,int[] values,int tg){
        int n = weights.length;
        int[] dp = new int[tg + 1];

        for(int i=0;i<=tg;i++){
            if(weights[0] <= i){
                dp[i] = (i / weights[0]) * values[0];
            }
        }

        for(int index=1;index<n;index++){
            int[] curr = new int[tg + 1];
            for(int target=0;target <= tg;target++){

                int reject = dp[target];
                int take = 0;
                if(weights[index] <= target){
                    take = values[index] + curr[target - weights[index]];
                }
                curr[target] = Math.max(reject, take);
            }
            dp = curr;
        }
        return dp[tg];
    }
    public int constantSpaceOptimized(int[] weights,int[] values,int tg){
        int n = weights.length;
        int[] dp = new int[tg + 1];
    
        for (int target = 0; target <= tg; target++) {
            if (weights[0] <= target) {
                dp[target] = (target / weights[0]) * values[0];
            }
        }
    
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= tg; target++) {
                int reject = dp[target];
                int take = 0;
                if (weights[index] <= target) {
                    take = values[index] + dp[target - weights[index]];
                }
                dp[target] = Math.max(reject, take);
            }
        }
        return dp[tg];
    }
    




    public static void main(String[] args) {
        UnboundedKnapSack obj = new UnboundedKnapSack();
        int[] weights = {2,4,6};
        int[] values = {5,11,13};
        int n = weights.length;
        int bag = 10;
        int[][] dp = new int[n][bag + 1];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }
        System.out.println(obj.recursive(weights, values, weights.length - 1, bag));
        System.out.println(obj.memoization(weights, values, n - 1, bag, dp));
        System.out.println(obj.tabulation(weights, values, bag));
        System.out.println(obj.constantSpace(weights, values, bag));
        System.out.println(obj.constantSpaceOptimized(weights, values, bag));
    }
}
