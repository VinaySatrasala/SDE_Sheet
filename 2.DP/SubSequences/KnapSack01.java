public class KnapSack01 {
    public static void main(String[] args) {
        int[] weights = {1, 2, 3};
        int[] values = {10, 15, 40};
        int capacity = 6;
        KnapSack01 knapSack01 = new KnapSack01();
        System.out.println(knapSack01.maxValue(weights, values, capacity));
    }

    private int maxValue(int[] weights, int[] values, int capacity) {
        return 0;
    }

    public int recursive(int[] weights,int[] values,int index,int capacity){
        if(index == 0){
            if(weights[index] <= capacity){
                return values[index];
            }
            return 0;
        }

        int reject = recursive(weights, values, index - 1, capacity);
        int take = 0;
        if(weights[index] <= capacity){
            take = values[index] + recursive(weights, values, index - 1, capacity - weights[index]);
        }
        return Math.max(take, reject);
    }

    public int memoization(int[] weights,int[] values,int index,int capacity,int[][] dp){
        if(index == 0){
            if(weights[index] <= capacity){
                return dp[index][capacity] = values[index];
            }
            return dp[index][capacity] = 0;
        }

        if(dp[index][capacity] != -1) return dp[index][capacity];

        int reject = memoization(weights, values, index - 1, capacity, dp);
        int take = 0;
        if(weights[index] <= capacity){
            take = values[index] + memoization(weights, values, index - 1, capacity - weights[index], dp);
        }
        return dp[index][capacity] = Math.max(take, reject);
    }

    public int tabulation(int[] weights,int[] values,int bag){
        int n = weights.length;
        int[][] dp = new int[n][bag + 1];

        // filling base case
        // dp[x][y] y -> is capasoity so index > capasity are 0 obvious
        for (int c = weights[0]; c <= bag; c++) {
            dp[0][c] = values[0];
        }
        

        for(int index=1;index<n;index++){
            for(int capacity = 0;capacity <= bag;capacity++){
                int reject = dp[index - 1][capacity];
                int take = 0;
                if(weights[index] <= capacity){
                    take = values[index] + dp[index - 1][capacity - weights[index]] ;
                }
                dp[index][capacity] = Math.max(take, reject);
            }
        }

        return dp[n-1][bag];
    }



    public int constantSpace(int[] weights,int[] values,int bag){
        int n = weights.length;
        int[] dp = new int[bag + 1];

        // filling base case
        // dp[x][y] y -> is capasoity so index > capasity are 0 obvious
        for (int c = weights[0]; c <= bag; c++) {
            dp[c] = values[0];
        }
        

        for(int index=1;index<n;index++){
            int[] temp = new int[bag + 1];
            for(int capacity = 0;capacity <= bag;capacity++){
                int reject = dp[capacity];
                int take = 0;
                if(weights[index] <= capacity){
                    take = values[index] + dp[capacity - weights[index]] ;
                }
                temp[capacity] = Math.max(take, reject);
            }
            dp = temp;
        }

        return dp[bag];
    }

    public int constantSpace2(int[] weights,int[] values,int bag){
        int n = weights.length;
        int[] dp = new int[bag + 1];

        // filling base case
        // dp[x][y] y -> is capasoity so index > capasity are 0 obvious
        for (int c = weights[0]; c <= bag; c++) {
            dp[c] = values[0];
        }
        

        for(int index=1;index<n;index++){
            for(int capacity = bag;capacity >= 0;capacity--){
                int reject = dp[capacity];
                int take = 0;
                if(weights[index] <= capacity){
                    take = values[index] + dp[capacity - weights[index]] ;
                }
                dp[capacity] = Math.max(take, reject);
            }
        }
        return dp[bag];
    }
}
