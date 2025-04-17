public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int i : nums){
            total = total + i;
        }
        if(total % 2 != 0) return false;
        return constantSpace(nums,total/2);
    }

    public boolean recursive(int[] nums, int index, int total, int target) {
        if (target == total)
            return true;

        if (index == 0) {
            return total - nums[0] == target + nums[0];
        }

        // take or reject
        boolean take = recursive(nums, index - 1, total - nums[index], target + nums[index]);
        boolean reject = recursive(nums, index - 1, total, target);

        return take || reject;
    }

    // we can reduce to finding total / 2 subarray => we will reduce one varible
    public boolean recursive(int[] nums, int index, int target) {
        if (target == 0)
            return true;

        if (index == 0) {
            return target - nums[0] == 0;
        }

        // take or reject
        boolean take = recursive(nums, index - 1, target - nums[index]);
        boolean reject = recursive(nums, index - 1, target);

        return take || reject;
    }

    // memoizations
    public boolean memoization(int[] nums, int index, int target, Boolean[][] dp) {
        if (target == 0) {
            return dp[index][target] = true;
        }

        if (index == 0) {
            return dp[index][target] = target - nums[0] == 0;
        }

        // take or reject
        boolean take = memoization(nums, index - 1, target - nums[index], dp);
        boolean reject = memoization(nums, index - 1, target, dp);

        return dp[index][target] = take || reject;
    }

    // bottom
    public boolean tabulation(int[] nums, int k) {
        boolean[][] dp = new boolean[nums.length + 1][k + 1];
        // which case is true at all the index 0,1,.... and target == nums[0] set to
        // true;
        if(nums[0] <= k) dp[0][nums[0]] = true;
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        for (int index = 1; index < nums.length; index++) {
            for (int target = 0; target <= k; target++) {
                boolean reject = dp[index - 1][target];
                boolean take = false;
                if (target >= nums[index]) {
                    take = dp[index - 1][target - nums[index]];
                }

                dp[index][target] = take || reject;
            }
        }

        return dp[nums.length - 1][k];
    }

    public boolean constantSpace(int[] nums, int k) {
        int n = nums.length;
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        if(nums[0] <= k) dp[nums[0]] = true;
        for (int index = 1; index < n; index++) {
            boolean[] temp = new boolean[k + 1];
            for (int target = 0; target <= k; target++) {
                boolean reject = dp[target];
                boolean take = false;
                if (target >= nums[index]) {
                    take = dp[target - nums[index]];
                }

                temp[target] = take || reject;
            }
            dp = temp;
        }
        return dp[k];
    }
}
