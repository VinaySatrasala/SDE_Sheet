import java.util.Arrays;

public class BurstBaloons {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] newNums = new int[len + 2];
        
        for(int i=1;i<len + 1;i++){
            newNums[i] = nums[i-1];
        }
        newNums[0] = 1;
        newNums[len+1] = 1;

        int[][] dp = new int[len + 1][len + 1];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        return f(1,len,newNums,dp);
    }
    public int f(int i,int j,int[] nums,int[][] dp){
        if(i > j) return 0;
        int max = 0;
        if(dp[i][j] != -1) return dp[i][j];
        for(int ind = i;ind <= j;ind++){
            // can burst any baloon
            int cost = (nums[i-1] * nums[ind] * nums[j + 1]) + f(i, ind -1, nums,dp) + f(ind + 1, j, nums,dp);
            max = Math.max(max, cost); 
        }
        return dp[i][j] = max;
    }

    public int tabulation(int[] nums){
        int n = nums.length;
        int[] newNums = new int[n + 2];
        
        for(int i=1;i<n + 1;i++){
            newNums[i] = nums[i-1];
        }
        newNums[0] = 1;
        newNums[n+1] = 1;

        int[][] dp = new int[n + 1][n + 1];

        for(int i =n-1;i>0;i++){
            for(int j= 1; j<n;j++){
                if(i > j ) continue;
                int max = 0;
                for(int ind = i;ind <= j;ind++){
                    // can burst any baloon
                    int cost = (newNums[i-1] * newNums[ind] * newNums[j + 1]) + dp[i][ind -1] + dp[ind + 1][j];
                    max = Math.max(max, cost); 
                }
                dp[i][j] = max;
            }
        }
        return dp[1][n-1];
    }
}
