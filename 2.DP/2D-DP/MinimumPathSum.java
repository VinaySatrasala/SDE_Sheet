public class MinimumPathSum {
    int m;
    int n;
    int INF = Integer.MAX_VALUE;
    public int recursive(int[][] grid,int row,int col){
        // check out of bound
        if(row >= m || col >= n){
            return INF;
        }
        // Base case
        if(row == m-1 && col == n-1){
            return grid[m-1][n-1];
        }

        // Doint ops
        // move right
        int right = recursive(grid, row , col + 1);
        int down = recursive(grid, row + 1, col);


        return grid[row][col] + Math.max(right, down);
    }

    public int memoization(int[][] grid,int row,int col,int[][] dp){
        // out of bounds
        if(row >= m || col >= n) return INF;

        // Base Case
        if(row == m-1 && col == n-1) {
            return dp[row][col] = grid[row][col];
        }

        // check for overlaping problem
        if(dp[row][col] != -1) return dp[row][col];

        int right = memoization(grid, row , col + 1, dp);
        int down = memoization(grid, row + 1, col, dp);
        return dp[row][col] = grid[row][col] + Math.max(right, down);
    }

    public int tabulation(int[][] grid){
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = grid[m-1][n-1];

        for(int i = m-1;i>=0;i++){
            for(int j=n-1;j>=0;j++){
                dp[i][j] = INF;
                // going down
                if(i + 1 < m){
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j]);
                }

                // goint right
                if(j + 1 < n){
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1]);
                }

                // adding currect grid value
                dp[i][j]  += grid[i][j];
            }
        }
        return dp[0][0];
    }
    public int constantSpace(int[][] grid) {
        int[] dp = new int[n];
        
        // Traverse from bottom to top
        for (int i = m - 1; i >= 0; i--) {
            int[] temp = new int[n];
            
            // Traverse from right to left for each row
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    temp[j] = grid[i][j]; // Base case: last cell
                } else {
                    int right = (j + 1 < n) ? temp[j + 1] : INF;
                    int down = (i + 1 < m) ? dp[j] : INF;
                    temp[j] = grid[i][j] + Math.min(right, down);
                }
            }
            dp = temp; // Move temp to dp for the next iteration
        }
        return dp[0]; // The answer will be in dp[0] after all rows are processed
    }
}
