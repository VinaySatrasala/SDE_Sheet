import java.util.Arrays;

public class MCM {
    public int recursive(int i,int j,int[] nums){
        // Base case 
        if(i == j) return 0;

        int steps = Integer.MAX_VALUE;
        // Making partitions
        for(int k=i;k<j;k++){
            int partition_1 = recursive(i, k, nums);
            int partition_2 = recursive(k+1, j, nums);
            // if the computed now steps will be
            // dimension of p1 = nums[i-1] * nums[k];
            // dimension of p2 = nums[k] * nums[j] -> for k+1 left dimensiaon will be k-1
            // so over all steps = nums[i-1] * nums[k] * nums[j]
            // that will be currenct partion steps sum up you will get
            int current = nums[i-1] * nums[k] * nums[j];
            int step = partition_1 + partition_2 + current;
            steps = Math.min(steps, step);
        }
        return steps;
    }
    public int memoization(int i,int j,int[] nums,int[][] dp){
        // Base case 
        if(i == j) return dp[i][j] =  0;
        if(dp[i][j] != -1) return dp[i][j];
        int steps = Integer.MAX_VALUE;
        // Making partitions
        for(int k=i;k<j;k++){
            int partition_1 = memoization(i, k, nums,dp);
            int partition_2 = memoization(k+1, j, nums,dp);
            // if the computed now steps will be
            // dimension of p1 = nums[i-1] * nums[k];
            // dimension of p2 = nums[k] * nums[j] -> for k+1 left dimensiaon will be k-1
            // so over all steps = nums[i-1] * nums[k] * nums[j]
            // that will be currenct partion steps sum up you will get
            int current = nums[i-1] * nums[k] * nums[j];
            int step = partition_1 + partition_2 + current;
            steps = Math.min(steps, step);
        }
        return dp[i][j] = steps;
    }

    public int tabulation(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n][n];

        for(int i=n-1;i>0;i--){
            for(int j = i+1;j<n;j++){
                int steps = Integer.MAX_VALUE;

                for(int k=i;k<j;k++){
                    int partition_1 = dp[i][k];
                    int partition_2 = dp[k+1][j];
                    int current = nums[i-1] * nums[k] * nums[j];
                    int step = partition_1 + partition_2 + current;
                    steps = Math.min(steps, step);
                }
                dp[i][j] = steps;
            }
        }
        return dp[1][n-1];
    }
    public static void main(String[] args){
        int[] nums = {10,20,30,40,50};
        int[][] dp = new int[nums.length][nums.length];
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }

        MCM mcm = new MCM();
        System.out.println(mcm.memoization(1, nums.length - 1, nums,dp));
        System.out.println(mcm.tabulation(nums));
    }
}

