import java.util.Arrays;

public class MinimumFallingPathSum {
    int n;
    int INF = Integer.MAX_VALUE;

    // ✅ 1. PURE RECURSION (TLE for large inputs)
    public int recursive(int[][] matrix, int row, int col) {
        n = matrix.length;

        // Out of bounds
        if (col < 0 || col >= n) return INF;

        // Reached last row
        if (row == n - 1) return matrix[row][col];

        // Recurse to three possible directions
        int left = recursive(matrix, row + 1, col - 1);
        int down = recursive(matrix, row + 1, col);
        int right = recursive(matrix, row + 1, col + 1);

        return matrix[row][col] + Math.min(down, Math.min(left, right));
    }

    // ✅ 2. MEMOIZATION
    public int memoization(int[][] matrix, int row, int col, int[][] dp) {
        // Out of bounds
        if (col < 0 || col >= n) return INF;

        // Base case
        if (row == n - 1) return dp[row][col] = matrix[row][col];

        // Memoization hit
        if (dp[row][col] != -1) return dp[row][col];

        // Recurse and memoize
        int left = memoization(matrix, row + 1, col - 1, dp);
        int down = memoization(matrix, row + 1, col, dp);
        int right = memoization(matrix, row + 1, col + 1, dp);

        return dp[row][col] = matrix[row][col] + Math.min(down, Math.min(left, right));
    }

    // ✅ 3. TABULATION
    public int tabulation(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // Copy last row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = matrix[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int down = dp[i + 1][j];
                int left = (j > 0) ? dp[i + 1][j - 1] : INF;
                int right = (j < n - 1) ? dp[i + 1][j + 1] : INF;

                dp[i][j] = matrix[i][j] + Math.min(down, Math.min(left, right));
            }
        }

        int min = INF;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dp[0][j]);
        }
        return min;
    }

    // ✅ 4. CONSTANT SPACE
    public int constantSpace(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n];

        // Copy last row
        for (int j = 0; j < n; j++) {
            dp[j] = matrix[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                int down = dp[j];
                int left = (j > 0) ? dp[j - 1] : INF;
                int right = (j < n - 1) ? dp[j + 1] : INF;

                temp[j] = matrix[i][j] + Math.min(down, Math.min(left, right));
            }
            dp = temp;
        }

        int min = INF;
        for (int val : dp) {
            min = Math.min(min, val);
        }
        return min;
    }

    // ✅ Wrapper to call memoization
    public int minFallingPathSum(int[][] matrix) {
        n = matrix.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int min = INF;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, memoization(matrix, 0, j, dp));
        }
        return min;
    }
}
