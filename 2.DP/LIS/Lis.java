import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lis {
    public int recursive(int[] nums,int index,int prevIndex){
        if(index == nums.length){
            return 0;
        }
        int take = 0;
        if(prevIndex == -1 ||nums[index] > nums[prevIndex]){
            take = 1 + recursive(nums, index + 1, index);
        }
        int reject = recursive(nums, index + 1, prevIndex);
        return Math.max(take,reject);
    }
    public int memoization(int[] nums,int index,int prevIndex,int[][] dp){
        if(index == nums.length){
            return 0;
        }
        int take = 0;
        if(prevIndex == -1 ||nums[index] > nums[prevIndex]){
            take = 1 + memoization(nums, index + 1, index,dp);
        }
        int reject = memoization(nums, index + 1, prevIndex,dp);
        return dp[index][prevIndex + 1] = Math.max(take,reject);
    }

    public int tabulation(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n][n+1];

        for(int index = 0;index < n;index++){
            for(int prevIndex=index-1;prevIndex>=-1;prevIndex--){
                int take = 0;
                if(prevIndex == -1 ||nums[index] > nums[prevIndex]){
                    take = 1 + dp[index + 1][index + 1];
                }
                int reject = dp[index + 1][prevIndex + 1];
                dp[index][prevIndex + 1] = Math.max(take,reject);
            }
        }
        return dp[n][-1+1];
    }

    public int constantSpce(int[] nums) {
        int n = nums.length;
        int[] next = new int[n + 1];
        int[] curr = new int[n + 1];
    
        for (int index = n - 1; index >= 0; index--) {
            for (int prevIndex = index - 1; prevIndex >= -1; prevIndex--) {
                int take = 0;
                if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
                    take = 1 + next[index + 1];
                }
                int reject = next[prevIndex + 1];
                curr[prevIndex + 1] = Math.max(take, reject);
            }
            next = curr.clone(); // ⚠️ important
        }
    
        return next[0];
    }
    
    public int optimalTabulation(int[] nums){
        int n = nums.length;
        int[] lis = new int[n];
        lis[0] = 1;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j]){
                    lis[i] = Math.max(lis[i],1 + lis[j]);
                }
            }
        }
        int max = 0;
        for(int l : lis){
            max = Math.max(max,l);
        }
        return max;
    }

    public int printLis(int[] nums){
        int n = nums.length;
        int[] lis = new int[n];
        int[] hash = new int[n];
        int max_lis_index = 0;
        int max_lis = 0;
        for(int i=0;i<n;i++){
            lis[i] = 1;
            hash[i] = i;
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j]){
                    if(lis[i] < 1 + lis[j]){
                        lis[i] = 1 + lis[j];
                        hash[i] = j;
                    }
                }
            }
            if(max_lis < lis[i]){
                max_lis = lis[i];
                max_lis_index = i;
            }
        }
        int temp = max_lis_index;
        List<Integer> ls = new ArrayList<>();
        while (hash[temp] != temp) {
            ls.add(nums[temp]);
            temp = hash[temp];
        }
        ls.add(nums[temp]);
        Collections.reverse(ls);
        System.out.println(ls);
        return max_lis;
    }
    public int lisBs(int[] nums){
        int n = nums.length;
        List<Integer> temp = new ArrayList<>();
        temp.add(nums[0]);
        for(int i=1;i<n;i++){
            if(nums[i] > temp.getLast()){
                temp.add(nums[i]);
            }else{
                int index = lowerBound(temp,nums[i]);
                temp.set(index, nums[i]);
            }
        }
        return temp.size();
    }
    private int lowerBound(List<Integer> temp,int target) {
        int s = 0;
        int e = temp.size() - 1;
        while (s < e) {
            int mid = s + (e - s)/2;
            int ele = temp.get(mid);
            if(ele == target){
                return  mid;
            }else if(ele > target){
                e = mid;
            }else{
                s = mid;
            }
        }
        return s;
    }
    public static void main(String[] args) {
        Lis lis = new Lis();
        int[] nums = {5,4,11,1,16,18};
        lis.printLis(nums);
    }
}
