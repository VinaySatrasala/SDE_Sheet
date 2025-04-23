import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordChain {
    public int longestStrChain(String[] words) {
        int n = words.length;
        Arrays.sort(words,(x,y)->x.length()-y.length());
        int[] longest = new int[n];
        int max = 1;
        for(int i=1;i<n;i++){
            longest[i] = 1;
            for(int j=0;j<i;j++){
            if(compare(words[i],words[j]) && longest[i] > 1 + longest[j]){
                longest[i] = 1 + longest[j];
            }
            }
            max = Math.max(max, longest[i]);
        }
        return max;
    }
    private boolean compare(String longer, String shorter) {
        if (longer.length() - shorter.length() != 1) return false;
    
        int i = 0, j = 0;
        boolean skipped = false;
    
        while (i < longer.length() && j < shorter.length()) {
            if (longer.charAt(i) == shorter.charAt(j)) {
                i++;
                j++;
            } else {
                if (skipped) return false; // already skipped one char before
                skipped = true;
                i++; // skip a character in the longer string
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        String[] arr = {"xb","x"};
        Arrays.sort(arr,(x,y)->x.length()-y.length());
        System.out.println(Arrays.toString(arr));
    }
}
