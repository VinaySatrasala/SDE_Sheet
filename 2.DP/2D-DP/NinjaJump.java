import java.util.Arrays;

public class NinjaJump {
    public int maxPoints(int[][] points){
        int n = points.length;
        int[][] dp = new int[n][4];
        for(int[] row : dp) Arrays.fill(row, -1);
        return memoization(points, n - 1, 3, dp);
    }

    public int recursive(int[][] points, int day, int lastTask){
        if(day == 0){
            int max = 0;
            for(int i = 0; i < 3; i++){
                if(i != lastTask){
                    max = Math.max(max, points[0][i]);
                }
            }
            return max;
        }

        int max = 0;
        for(int i = 0; i < 3; i++){
            if(i != lastTask){
                int point = points[day][i] + recursive(points, day - 1, i);
                max = Math.max(max, point);
            }
        }
        return max;
    }

    public int memoization(int[][] points, int day, int lastTask, int[][] dp){
        if(dp[day][lastTask] != -1) return dp[day][lastTask];

        if(day == 0){
            int max = 0;
            for(int i = 0; i < 3; i++){
                if(i != lastTask){
                    max = Math.max(max, points[0][i]);
                }
            }
            return dp[day][lastTask] = max;
        }

        int max = 0;
        for(int i = 0; i < 3; i++){
            if(i != lastTask){
                int point = points[day][i] + memoization(points, day - 1, i, dp);
                max = Math.max(max, point);
            }
        }
        return dp[day][lastTask] = max;
    }

    public int tabulation(int[][] points){
        int n = points.length;
        int[][] dp = new int[n][4];

        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day = 1; day < n; day++){
            for(int lastTask = 0; lastTask < 4; lastTask++){
                dp[day][lastTask] = 0;
                for(int task = 0; task < 3; task++){
                    if(task != lastTask){
                        int point = points[day][task] + dp[day-1][task];
                        dp[day][lastTask] = Math.max(dp[day][lastTask], point);
                    }
                }
            }
        }

        return dp[n-1][3];
    }

    public int constantSpace(int[][] points){
        int n = points.length;
        int[] prev = new int[4];

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day = 1; day < n; day++){
            int[] temp = new int[4];
            for(int lastTask = 0; lastTask < 4; lastTask++){
                temp[lastTask] = 0;
                for(int task = 0; task < 3; task++){
                    if(task != lastTask){
                        int point = points[day][task] + prev[task];
                        temp[lastTask] = Math.max(temp[lastTask], point);
                    }
                }
            }
            prev = temp;
        }

        return prev[3];
    }

    public static void main(String[] args) {
        NinjaJump nj = new NinjaJump();
        int[][] points = {{10, 40, 70}, {20, 50, 80}, {30, 60, 90}};
        System.out.println(nj.maxPoints(points)); // should print 210
        System.out.println(nj.tabulation(points)); // also 210
        System.out.println(nj.constantSpace(points)); // also 210
    }
}
