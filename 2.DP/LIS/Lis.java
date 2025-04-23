public class Lis {
    public int recursive(int[] nums,int index,int prevIndex){
        if(index == nums.length){
            return 0;
        }
        int take = 0;
        if(prevIndex == -1 ||nums[index] > nums[prevIndex]){
            take = 1 + recursive(nums, index + 1, index);
        }
        int reject = recursive(nums, index + 1, prevIndex);
        return Math.max(take,reject);
    }
    public int memoization(int[] nums,int index,int prevIndex,int[][] dp){
        if(index == nums.length){
            return 0;
        }
        int take = 0;
        if(prevIndex == -1 ||nums[index] > nums[prevIndex]){
            take = 1 + memoization(nums, index + 1, index,dp);
        }
        int reject = memoization(nums, index + 1, prevIndex,dp);
        return dp[index][prevIndex + 1] = Math.max(take,reject);
    }

    public int tabulation(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n + 1][n+1];


        return 0;
    }

}
