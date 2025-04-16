import java.util.Arrays;

public class UniquePath2 {
    int m;
    int n;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        
        // You can switch between methods here
        // return recursive(obstacleGrid, 0, 0);
        
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);
        return memoization(obstacleGrid, 0, 0, dp);

        // return tabulation(obstacleGrid);
        // return constantSpace(obstacleGrid);
    }

    public int recursive(int[][] grid, int row, int col) {
        if (row >= m || col >= n) return 0;
        if (grid[row][col] == 1) return 0;
        if (row == m - 1 && col == n - 1) return 1;

        int right = recursive(grid, row + 1, col);
        int down = recursive(grid, row, col + 1);

        return right + down;
    }

    public int memoization(int[][] grid, int row, int col, int[][] dp) {
        if (row >= m || col >= n) return 0;
        if (grid[row][col] == 1) return 0;
        if (row == m - 1 && col == n - 1) return 1;
        if (dp[row][col] != -1) return dp[row][col];

        int right = memoization(grid, row + 1, col, dp);
        int down = memoization(grid, row, col + 1, dp);

        return dp[row][col] = right + down;
    }

    public int tabulation(int[][] grid) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = (grid[m - 1][n - 1] == 0 ? 1 : 0);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) continue;

                // Going down
                if (i + 1 < m && grid[i + 1][j] != 1) {
                    dp[i][j] += dp[i + 1][j];
                }

                // Going right
                if (j + 1 < n && grid[i][j + 1] != 1) {
                    dp[i][j] += dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    public int constantSpace(int[][] grid) {
        int[] dp = new int[n];
        dp[n - 1] = (grid[m - 1][n - 1] == 0 ? 1 : 0);

        for (int i = m - 1; i >= 0; i--) {
            int[] temp = new int[n];
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) continue;

                // Going right
                if (j + 1 < n && grid[i][j + 1] != 1) {
                    temp[j] += temp[j + 1];
                }

                // Going down
                if (i + 1 < m && grid[i + 1][j] != 1) {
                    temp[j] += dp[j];
                }

                // If we're at the bottom-right cell and it's not an obstacle
                if (i == m - 1 && j == n - 1 && grid[i][j] == 0) {
                    temp[j] = 1;
                }
            }
            dp = temp;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        UniquePath2 up = new UniquePath2();

        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        System.out.println("Paths: " + up.uniquePathsWithObstacles(grid)); // Expected: 2
    }
}
