public class SubsetSum {
    public boolean recursive(int[] nums,int target,int index){
        // Base case 
        if(target == 0){
            return true;
        }
        if(index == 0){
            return target == nums[0];
        }

        boolean reject = recursive(nums, target, index - 1);
        boolean take = false;
        if(target >= nums[index]){
            take = recursive(nums, target - nums[index], index - 1);
        }
        
        return take || reject;
    }

    public boolean memoization(int[] nums,int target,int index,Boolean[][] dp){
        // Base
        if(target == 0){
            return true;
        }
        if(index == 0){
            return dp[index][target] = target == nums[0];
        }

        if(dp[index][target] != null) return dp[index][target];

        boolean reject = memoization(nums, target, index - 1,dp);
        boolean take = false;
        if(target >= nums[index]){
            take = memoization(nums, target - nums[index], index - 1,dp);
        }

        return dp[index][target] = take || reject;
    }

    // bottom 
    public boolean tabulation(int[] nums,int k){
        boolean[][] dp = new boolean[nums.length +  1][k + 1];
        // which case is true at all the  index 0,1,.... and target == nums[0] set to true;
        dp[0][nums[0]] = true;
        for(int i=0;i<nums.length;i++){
            dp[i][0] = true; 
        }
        for(int index=1;index<nums.length;index++){
            for(int target=0;target<=k;target++){
                boolean reject = dp[index-1][target];
                boolean take = false;
                if(target >= nums[index]){
                    take = dp[index-1][target - nums[index]];
                }
        
                dp[index][target] = take || reject;
            }
        }

        return dp[nums.length - 1][k];
    }

    public boolean constantSpace(int[] nums,int k){
        int n = nums.length;
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        dp[nums[0]] = true;
        for(int index=1;index<n;index++){
            boolean[] temp = new boolean[k + 1];
            for(int target=0;target<=k;target++){
                boolean reject = dp[target];
                boolean take = false;
                if(target >= nums[index]){
                    take = dp[target - nums[index]];
                }
        
                temp[target] = take || reject;
            }
            dp = temp;
        }
        return dp[k];
    }
}
