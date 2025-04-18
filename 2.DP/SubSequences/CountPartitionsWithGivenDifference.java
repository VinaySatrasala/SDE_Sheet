public class CountPartitionsWithGivenDifference {

    int total;
    int diff;
    // we find s1,s2 whose abs(s1-s2) = diff
    // and we know s1 + s2 = total
    // // so s1 = (total + diff) / 2
    // find subset with k = total + diff / 2
    public int recursive(int[] nums, int index, int target) {
        // Base cases
        // For index = 0
        if (index == 0){
            int reject = nums[0] == target ? 1 : 0;
            int take = target - nums[0] == 0 ? 1 : 0;
            return reject + take;
        }

        int reject = recursive(nums, index - 1, target);
        int take = 0;
        if (nums[index] <= target) {
            take = recursive(nums, index - 1, target - nums[index]);
        }
        return take + reject;
    }
    public int memoization(int[] nums, int index, int target, int[][] dp) {
        // Base cases

        // For index = 0
        if (index == 0){
            int reject = nums[0] == target ? 1 : 0;
            int take = target - nums[0] == 0 ? 1 : 0;
            return dp[0][target] = reject + take;
        }

        if (dp[index][target] != -1) return dp[index][target];

        int reject = memoization(nums, index - 1, target, dp);
        int take = 0;
        if (nums[index] <= target) {
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
            dp[0][nums[0]] += 1;
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
    public int constantSpace(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[k + 1];

        // base case: with index 0
        dp[0] = 1;  // empty subset makes sum 0

        if (nums[0] <= k) {
            dp[nums[0]] += 1;
        }

        for (int index = 1; index < n; index++) {
            for (int target = k; target >= nums[index]; target--) {
                dp[target] += dp[target - nums[index]];
            }
        }
        return dp[k];
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1};
        int diff = 1;
        CountPartitionsWithGivenDifference obj = new CountPartitionsWithGivenDifference();
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        obj.total = total;
        obj.diff = diff;
        System.out.println(obj.recursive(nums, nums.length - 1, (total + diff) / 2));
        int[][] dp = new int[nums.length][(total + diff) / 2 + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < (total + diff) / 2 + 1; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(obj.memoization(nums, nums.length - 1, (total + diff) / 2, dp));
        System.out.println(obj.tabulation(nums, (total + diff) / 2));
        System.out.println(obj.constantSpace(nums, (total + diff) / 2));
    }
}
