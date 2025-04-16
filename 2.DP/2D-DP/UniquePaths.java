import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        
        return recursive(m, n, 0, 0);
    }
    public int recursive(int m,int n,int row,int col){
        // Base case
        if(row == m-1 && col == n-1){
            return 1;
        }

        // do stuf
        int total = 0;
        // goint down
        if(col + 1 < n && row < m){
            total = total + recursive(m, n, row, col + 1);
        }
        
        // go right 
        if(row + 1 < m && col < n){
            total = total + recursive(m, n, row + 1, col);
        }
        
        
        System.out.println("("+row + "," + col +")" + "--->" + total);
        return total;
    }
    public int memoization(int m, int n, int row, int col, int[][] dp){
        // Base case
        if(row == m-1 && col == n-1){
            return 1;
        }
        if(dp[row][col] != -1) return dp[row][col];

        // do stuf
        int total = 0;
        // goint down
        if(col + 1 < n && row < m){
            total = total + memoization(m, n, row, col + 1, dp);
        }
        
        // go right 
        if(row + 1 < m && col < n){
            total = total + memoization(m, n, row + 1, col, dp);
        }
        
        return dp[row][col] = total;
    }

    public int tabulation(int m,int n){
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = 1;
        // f(x,y) = f(x+1,y) + f(x,y+1);
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i + 1 < m){
                    dp[i][j] += dp[i+1][j];
                }
                if(j + 1 < n){
                    dp[i][j] += dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }

    public int constantSpace(int m,int n){
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        // f(x,y) = f(x+1,y) + f(x,y+1);
        for(int i=m-2;i>=0;i--){
            int[] temp = new int[n];
            for(int j=n-1;j>=0;j--){
                if(j+ 1 < n){
                    temp[j] += temp[j+1];
                }
                temp[j] += dp[j];
            }
            dp = temp;
        }

        return dp[0];
    }
    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        System.out.println(up.uniquePaths(3, 2));
    }
}
