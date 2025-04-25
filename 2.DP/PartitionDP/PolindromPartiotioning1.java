import java.util.ArrayList;
import java.util.List;

class PolindromPartiotioning1 {
    List<List<String>>[] dp;

    public List<List<String>> partition(String s) {
        dp = new List[s.length()];
        return help(s, 0);
    }

    public List<List<String>> help(String s, int index) {
        if (index == s.length()) {
            List<List<String>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }

        if (dp[index] != null) {
            return dp[index];
        }

        List<List<String>> ans = new ArrayList<>();

        for (int part = index + 1; part <= s.length(); part++) {
            if (check(s, index, part - 1)) {
                String poli = s.substring(index, part);
                List<List<String>> res = help(s, part);
                for (List<String> subList : res) {
                    List<String> newList = new ArrayList<>();
                    newList.add(poli);
                    newList.addAll(subList);
                    ans.add(newList);
                }
            }
        }

        dp[index] = ans;
        return ans;
    }

    public boolean check(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // For testing
    public static void main(String[] args) {
    }
}
