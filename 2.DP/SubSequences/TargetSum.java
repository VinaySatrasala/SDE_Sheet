import java.util.HashMap;
import java.util.Map;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        return 0;
    }

    public int recursive(int[] nums,int target,int index,int sum){
        if(index == 0){
            int plus = sum + nums[index] == target ? 1 : 0;
            int minus = sum - nums[index] == target ? 1 : 0;
            return plus + minus;
        }

        int plus = recursive(nums, target, index - 1, sum + nums[index]);
        int minus = recursive(nums, target, index - 1, sum - nums[index]);
        
        return plus + minus;
    }

    public int memoization(int[] nums, int target, int index, int sum, Map<Integer, Map<Integer, Integer>> dp) {
        if (index == 0) {
            int plus = (sum + nums[0] == target) ? 1 : 0;
            int minus = (sum - nums[0] == target) ? 1 : 0;
            dp.putIfAbsent(index, new HashMap<>());
            dp.get(index).put(sum, plus + minus);
            return plus + minus;
        }

        dp.putIfAbsent(index, new HashMap<>());
        if (dp.get(index).containsKey(sum)) {
            return dp.get(index).get(sum);
        }

        int plus = memoization(nums, target, index - 1, sum + nums[index], dp);
        int minus = memoization(nums, target, index - 1, sum - nums[index], dp);

        dp.get(index).put(sum, plus + minus);
        return plus + minus;
    }

}
