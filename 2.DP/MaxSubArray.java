import java.util.Arrays;

public class MaxSubArray {
    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = Integer.MIN_VALUE;
        int neg = nums[0] < 0 ? 1 : 0;
        int mul = nums[0];
        for(int i=1;i<n;i++){
            left[i] = mul;
            mul = mul * nums[i];
            if(nums[i] < 0) neg++;
        }
        if(neg == 0 || neg % 2 == 0){
            return mul;
        }
        right[n-1] = Integer.MIN_VALUE;
        mul = nums[n-1];
        for(int i=n-2;i>=0;i--){
            right[i] = mul;
            mul = mul * nums[i];
        }
        int max = 0;
        for(int i=0;i<n;i++){
            if(nums[i] >=0) continue;
            max = Math.max(max,Math.max(left[i],right[i]));
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        return max;
    }
    public static void main(String[] args) {
        int[] nums = {-3,-1,-1};
        System.out.println(maxProduct(nums));
    }
}
