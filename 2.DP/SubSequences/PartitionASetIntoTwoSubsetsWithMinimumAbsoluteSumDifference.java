public class PartitionASetIntoTwoSubsetsWithMinimumAbsoluteSumDifference {
    int total;

    public int recursive(int[] nums, int index, int curr) {
        if (index == 0) {
            return Math.abs(total - 2 * curr);
        }

        int take = recursive(nums, index - 1, curr + nums[index]);
        int reject = recursive(nums, index - 1, curr);

        return Math.min(take, reject);
    }

    public int memoization(int[] nums, int index, int curr, int[][] dp) {
        if (index == 0) {
            return dp[index][curr] = Math.abs(total - 2 * curr);
        }

        if (dp[index][curr] != -1) return dp[index][curr];

        int take = Integer.MAX_VALUE;
        if (curr + nums[index] <= total)
            take = memoization(nums, index - 1, curr + nums[index], dp);
        int reject = memoization(nums, index - 1, curr, dp);

        return dp[index][curr] = Math.min(take, reject);
    }

    public int tabulation(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][total + 1];

        // base case
        for (int i = 0; i <= total; i++) {
            dp[0][i] = Math.abs(total - 2 * i);
        }

        for (int index = 1; index < n; index++) {
            for (int curr = 0; curr <= total; curr++) {
                int take = Integer.MAX_VALUE;
                if (curr + nums[index] <= total) {
                    take = dp[index - 1][curr + nums[index]];
                }
                int reject = dp[index - 1][curr];
                dp[index][curr] = Math.min(take, reject);
            }
        }

        return dp[n - 1][0];
    }

    public int constantSpace(int[] nums) {
        int n = nums.length;
        int[] dp = new int[total + 1];

        // base case
        for (int i = 0; i <= total; i++) {
            dp[i] = Math.abs(total - 2 * i);
        }

        for (int index = 1; index < n; index++) {
            int[] temp = new int[total + 1];
            for (int curr = 0; curr <= total; curr++) {
                int take = Integer.MAX_VALUE;
                if (curr + nums[index] <= total) {
                    take = dp[curr + nums[index]];
                }
                int reject = dp[curr];
                temp[curr] = Math.min(take, reject);
            }
            dp = temp;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 11, 5};
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        PartitionASetIntoTwoSubsetsWithMinimumAbsoluteSumDifference obj = new PartitionASetIntoTwoSubsetsWithMinimumAbsoluteSumDifference();
        obj.total = total;

        int n = nums.length;

        // Brute Force
        System.out.println("Recursive: " + obj.recursive(nums, n - 1, 0));

        // Memoization
        int[][] dp = new int[n][total + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, -1);
        System.out.println("Memoization: " + obj.memoization(nums, n - 1, 0, dp));

        // Tabulation
        System.out.println("Tabulation: " + obj.tabulation(nums));
    }
}
