public class LCS {
    public static int recursive(String s1,String s2,int index1,int index2){
        if(index1 < 0 || index2 < 0){
            return 0;
        }

        if(s1.charAt(index1) == s2.charAt(index2)){
            return 1 + recursive(s1, s2, index1 - 1, index2 - 1);
        }

        return Math.max(recursive(s1, s2, index1 - 1, index2), recursive(s1, s2, index1, index2 - 1));
    }

    public static int memoization(String s1,String s2,int index1,int index2,int[][] dp){
        if(index1 < 0 || index2 < 0){
            return 0;
        }

        if(dp[index1 + 1][index2 + 1] != -1) return dp[index1 + 1][index2 + 1];

        if(s1.charAt(index1) == s2.charAt(index2)){
            return dp[index1 + 1][index2 + 1] =  1 + recursive(s1, s2, index1 - 1, index2 - 1);
        }

        return dp[index1 + 1][index2 + 1] =  Math.max(recursive(s1, s2, index1 - 1, index2), recursive(s1, s2, index1, index2 - 1));
    }

    public static int tabulation(String s1,String s2){
        // Base cases for i = 0 what ever j is put is as 0
        // Base case for j = 0 what ever i put it as  0
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                }else{
                    dp[i+1][j+1] = Math.max(dp[i][j+1] ,dp[i+1][j]);
                }
            }
        }
        return dp[m][n];
    }

    public static String recursivePrint(String s1,String s2,int index1,int index2){
        if(index1 < 0 || index2 < 0){
            return "";
        }

        if(s1.charAt(index1) == s2.charAt(index2)){
            return recursivePrint(s1, s2, index1 - 1, index2 - 1) + s1.charAt(index1);
        }
        String  left = recursivePrint(s1, s2, index1 - 1, index2);
        String right = recursivePrint(s1, s2, index1, index2 - 1);
        if(left.length() > right.length()){
            return left;
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(recursivePrint("adaabc", "admanbnhc",5,8));
        // System.out.println(memoization(null, null, 0, 0, null));
    }
    
}
// ab ac 