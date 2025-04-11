public class AdjacentPatternHouseRobber {
    public int rob(int[] nums,int index) {
        if(index >= nums.length - 1){
            return 0;
        }
        int take = nums[index] + rob(nums, index + 2);
        int reject = rob(nums, index + 1);
        return Math.max(take, reject);
    }   
    int[] dp;
    public int robMemo(int[] nums,int index){
        if(index >= nums.length - 1){
            return 0;
        }
        if(dp[index] != -1) return dp[index];

        int take = nums[index] + rob(nums, index + 2);
        int reject = rob(nums, index + 1);

        return dp[index] = Math.max(take, reject);
    }

    public int robTab(int[] nums){
        int[] dp = new int[nums.length + 2];
        // f(n) = Math.max(f(n + 1) , f(n+2) + nums[n])
        // if i do + 1 then -1 also same;
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        return dp[nums.length-1];
    }

    public int robConstant(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        // f(n) = Math.max(f(n + 1) , f(n+2) + nums[n])
        // if i do + 1 then -1 also same;
        int prev1 = nums[0];
        int prev2 = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
