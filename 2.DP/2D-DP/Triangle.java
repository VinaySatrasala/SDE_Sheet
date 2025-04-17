import java.util.List;

public class Triangle {
    int m;
    int INF = Integer.MAX_VALUE;
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

    public int tabulation(List<List<Integer>> triangle){
        int[][] dp = new int[m][];

        dp[m-1] = new int[triangle.get(m-1).size()];
        List<Integer> lastRow = triangle.get(m-1);
        dp[m-1] = new int[lastRow.size()];
        for (int i = 0; i < lastRow.size(); i++) {
            dp[m-1][i] = lastRow.get(i);
        }

        for(int i = m-2;i>=0;i--){
            List<Integer> curr = triangle.get(i);
            dp[i] = new int[curr.size()];
            for(int j=0;j<curr.size();j++){
                dp[i][j] = curr.get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
            }
        }
        return dp[0][0];
    }

    public int constantSpace(List<List<Integer>> triangle){
        int[] dp = new int[triangle.size()];
        List<Integer> lastRow = triangle.get(m-1);
        for (int i = 0; i < lastRow.size(); i++) {
            dp[i] = lastRow.get(i);
        }

        for(int i = m-2;i>=0;i--){
            List<Integer> curr = triangle.get(i);
            int[] temp = new int[curr.size()];
            for(int j=0;j<curr.size();j++){
                temp[j] = curr.get(j) + Math.min(dp[j], dp[j+1]);
            }
            dp = temp;
        }



        return dp[0];
    }


}
