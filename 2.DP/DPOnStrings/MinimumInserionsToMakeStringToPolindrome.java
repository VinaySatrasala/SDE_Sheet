public class MinimumInserionsToMakeStringToPolindrome {
    public static int recursive(String s1,int i,int j){
        if(i > j){
            return 0;
        }
        if(s1.charAt(i) == s1.charAt(j)){
            return recursive(s1, i + 1, j - 1);
        }
        return 1 + Math.min(recursive(s1, i, j-1),recursive(s1, i + 1, j));
    }

    public static int memoization(String s1,int i,int j,Integer[][] dp){
        if(i >= j){
            return 0;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        if(s1.charAt(i) == s1.charAt(j)){
            return dp[i][j] =  recursive(s1, i + 1, j - 1);
        }
        return dp[i][j] =  1 + Math.min(recursive(s1, i, j-1),recursive(s1, i + 1, j));
    }
    public static void main(String[] args) {
        String s = "leetcode";
        // int prev = s.length();
        // int i = 0;
        // int j = s.length() - 1;

        // while (i < j) {
        //     if(s.charAt(i) == s.charAt(j)){
        //         i++;
        //         j--;
        //     }else{
        //         String left = s.substring(0, i);
        //         s = left + s.charAt(j) + s.substring(i);
        //         i++; 
        //     }
        // }
        System.out.println(recursive(s,0,s.length()-1));
        // System.out.println(s.length() - prev);
    }
}
