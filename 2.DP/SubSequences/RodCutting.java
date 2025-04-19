import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RodCutting {
    public int recursive(int[] prices, int index, int target) {
        if (index == 0) {
            int rodLength = 1;
            return (target / rodLength) * prices[0]; // unbounded: take as many as possible
        }
    
        int rodLength = index + 1;
        int reject = recursive(prices, index - 1, target);
        int take = Integer.MIN_VALUE;
        if (rodLength <= target) {
            take = prices[index] + recursive(prices, index, target - rodLength);
        }
    
        return Math.max(reject, take);
    }
    
    public int memoization(int[] prices, int index, int target, int[][] dp) {
        if (index == 0) {
            int rodLength = 1;
            return (target / rodLength) * prices[0];
        }
    
        if (dp[index][target] != -1) return dp[index][target];
    
        int rodLength = index + 1;
        int reject = memoization(prices, index - 1, target, dp);
        int take = Integer.MIN_VALUE;
        if (rodLength <= target) {
            take = prices[index] + memoization(prices, index, target - rodLength, dp);
        }
    
        return dp[index][target] = Math.max(reject, take);
    }
    
    public int tabulation(int[] prices, int n) {
        int[][] dp = new int[n][n + 1];
    
        // Base case for index 0
        for (int t = 0; t <= n; t++) {
            dp[0][t] = t * prices[0];  // fill with max pieces of rodLength 1
        }
    
        for (int index = 1; index < n; index++) {
            int rodLength = index + 1;
            for (int target = 0; target <= n; target++) {
                int reject = dp[index - 1][target];
                int take = Integer.MIN_VALUE;
                if (rodLength <= target) {
                    take = prices[index] + dp[index][target - rodLength];
                }
                dp[index][target] = Math.max(reject, take);
            }
        }
    
        return dp[n - 1][n];
    }
    
    public int constantSpace(int[] prices, int n) {
        int[] dp = new int[n + 1];
    
        for (int t = 0; t <= n; t++) {
            dp[t] = t * prices[0];
        }
    
        for (int index = 1; index < n; index++) {
            int rodLength = index + 1;
            for (int target = rodLength; target <= n; target++) {
                dp[target] = Math.max(dp[target], prices[index] + dp[target - rodLength]);
            }
        }
    
        return dp[n];
    }
    

    public static void main(String[] args) {
        RodCutting rod = new RodCutting();
        int[] prices = {1, 6, 8, 9, 10, 19, 7, 20};
        int n = 8;
        System.out.println(rod.recursive(prices, n -1, n));
    }


}
