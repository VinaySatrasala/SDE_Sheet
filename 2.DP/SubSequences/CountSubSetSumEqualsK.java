public class CountSubSetSumEqualsK {

    // Will do strver top - bottom of starting n-1 -> 0
    public int recursive(int[] nums,int index,int target){
        // Base cases
        
        // For index = 0
        if(index == 0) return nums[0] == target ? 1 : 0;

        int reject = recursive(nums, index - 1, target);
        int take = 0;
        if(nums[index] <= target){
            take = recursive(nums, index - 1, target - nums[index]);
        } 
        int curr = target == 0  ? 1 : 0;
        return take + reject + curr;
    }

    public int memoization(int[] nums,int index,int target,int[][] dp){
        // Base cases
        // For target = 0
        if(target == 0) return 1;
        
        // For index = 0
        if(index == 0) return dp[0][target] =  nums[0] == target ? 1 : 0;

        if(dp[index][target] != -1) return dp[index][target];

        int reject = memoization(nums, index - 1, target, dp);
        int take = 0;
        if(nums[index] <= target){
            take = memoization(nums, index - 1, target - nums[index], dp);
        }
        return dp[index][target] = take + reject;
    }

    public int tabulation(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];
    
        // base case: with index 0
        dp[0][0] = 1;  // empty subset makes sum 0
    
        if (nums[0] <= k) {
            dp[0][nums[0]] = 1;
        }
    
        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= k; target++) {
                int reject = dp[index - 1][target];
                int take = 0;
                if (nums[index] <= target) {
                    take = dp[index - 1][target - nums[index]];
                }
                dp[index][target] = take + reject;
            }
        }
        return dp[n - 1][k];
    }

    

    public static void  main(String[] args) {
        CountSubSetSumEqualsK obj = new CountSubSetSumEqualsK();
        // very big test case
        int[] nums = {0,0,1};

        System.err.println("Recursive: " + obj.recursive(nums, nums.length - 1, 1));
    }   
}
