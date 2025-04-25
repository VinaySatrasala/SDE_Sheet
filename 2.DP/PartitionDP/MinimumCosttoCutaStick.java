import java.util.Arrays;

public class MinimumCosttoCutaStick {
    // Brute Force
    public int minCost1(int n, int[] cuts) {
        boolean[] visited = new boolean[cuts.length];
        return f(0,n,cuts,visited);   
    }
    public int f(int i,int j,int[] cuts,boolean[] visited){
        int min = Integer.MAX_VALUE;
        
        int length = j - i;
        System.out.println("i: " + i + " j: " + j);
        for(int cut=0;cut<cuts.length;cut++){
            if(visited[cut]) continue;
            if(!(i < cuts[cut] && cuts[cut] < j)) continue;
            visited[cut] = true;
            int lp = f(i, cuts[cut], cuts, visited);
            int rp = f(cuts[cut], j, cuts, visited);
            min = Math.min(min, lp + rp);
            visited[cut] = false;
        }
        System.out.println("min: " + min);
        if(min == Integer.MAX_VALUE) return 0;
        return length + min;
    }

    public int minCost2(int n, int[] cuts) {
        Arrays.sort(cuts);
        int c = cuts.length;
        int[][] dp = new int[c+2][c+2];
        for(int[] i : dp){
            Arrays.fill(i,-1);
        }
        int[] newCuts = new int[c + 2];
        newCuts[0] = 0;
        newCuts[c + 1] = n;
        for (int i = 0; i < c; i++) {
            newCuts[i + 1] = cuts[i];
        }

        return mcm(1, c, newCuts,dp); // from cut 1 to cut c
    }

    public int mcm(int i, int j, int[] cuts,int[][] dp) {
        if (i > j) return 0;
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = cuts[j + 1] - cuts[i - 1] + mcm(i, ind - 1, cuts,dp) + mcm(ind + 1, j, cuts,dp);
            min = Math.min(min, cost);
        }

        return dp[i][j] = min;
    }

    public int tabulation(int n, int[] cuts) {
        Arrays.sort(cuts);
        int c = cuts.length;
        int[][] dp = new int[c+2][c+2];
        int[] newCuts = new int[c + 2];
        newCuts[0] = 0;
        newCuts[c + 1] = n;
        for (int i = 0; i < c; i++) {
            newCuts[i + 1] = cuts[i];
        }

        for(int i=c;i>=1;i--){
            for(int j=1;j<=c;j++){
                if(i > j) continue;
                int min = Integer.MAX_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    int cost = cuts[j + 1] - cuts[i - 1] + mcm(i, ind - 1, cuts,dp) + mcm(ind + 1, j, cuts,dp);
                    min = Math.min(min, cost);
                }
        
                dp[i][j] = min;
            }
        }

        return dp[1][c]; // from cut 1 to cut c
    }

    public static void main(String[] args) {
        int[] cuts = {1,3,4,5};
        int n = 7;
        MinimumCosttoCutaStick obj = new MinimumCosttoCutaStick();
        int ans = obj.minCost1(n, cuts);
        System.out.println(ans);
        // Output: 16
    }
}
