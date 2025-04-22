public class LongestBitonicSubsqunce {
    public static int lbs(int[] nums){
        int n = nums.length;
        int[] lis = new int[n];
        int[] lds = new int[n];

        for(int i=0;i<n;i++){
            lis[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j]){
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }

        for(int i=n-1;i>=0;i--){
            lds[i] = 1;
            for(int j=i+1;j<n;j++){
                if(nums[i] > nums[j]){
                    lds[i] = Math.max(lds[i], 1 + lds[j]);
                }
            }
        }

        int max = 0;
        for(int i=0;i<n;i++){
            int lbs = lis[i] + lds[i] - 1;
            max = Math.max(max, lbs);
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = { 1,2,3,4,3,2,1};
        System.err.println(lbs(arr));

    }
}
