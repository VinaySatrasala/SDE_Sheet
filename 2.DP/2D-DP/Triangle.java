import java.util.List;

public class Triangle {
    int m;
    int INF = Integer.MAX_VALUE;
    public int minimumTotal(List<List<Integer>> triangle) {
        m = triangle.size();
        return 0;    
    }
    public int recursive(List<List<Integer>> triangle,int row,int col){
        int n = triangle.get(row).size();

        // Invalid row
        if(row >= m || col >= n){
            return INF;
        }
        // Base case 
        if(row == m-1 && col < n){
            return triangle.get(row).get(col);
        }

        int curr = triangle.get(row).get(col);
        // go to i 
        int first = recursive(triangle, row + 1, col);

        // got to i +1 col in next row
        int sec = recursive(triangle, row + 1, col + 1);

        if(Math.min(first, sec) == INF) return curr;


        return curr + Math.min(first, sec);
    }

    public int memoization(List<List<Integer>> triangle,int row,int col,int[][] dp){
        int n = triangle.get(row).size();

        // Invalid row
        if(row >= m || col >= n){
            return INF;
        }


        // Base case 
        int curr = triangle.get(row).get(col);
        if(row == m-1 && col < n){
            dp[row][col] = curr;
            return curr;
        }

        if(dp[row][col] != -1) return dp[row][col];

        // go to i 
        int first = memoization(triangle, row + 1, col,dp);

        // got to i +1 col in next row
        int sec = memoization(triangle, row + 1, col + 1,dp);
        int res = 0;
        if(Math.min(first, sec) == INF) {
            res = curr;
        }else{
            res = curr + Math.min(first, sec);
        }
        return dp[row][col] = res;
    }


}
