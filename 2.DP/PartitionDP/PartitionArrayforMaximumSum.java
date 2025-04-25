public class PartitionArrayforMaximumSum {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        return f(0, k, arr);
    }

    public int f(int start, int k, int[] arr) {
        if (start >= arr.length) return 0;  // Base case: no more elements to partition

        int maxRes = 0;
        int max = arr[start];

        // Loop to consider partitions of length 1 to k
        for (int ind = 0; ind < k && start + ind < arr.length; ind++) {
            max = Math.max(max, arr[start + ind]);  // Update the max in the current subarray
            int part = f(start + ind + 1, k, arr);  // Recursively partition the rest
            int res = (ind + 1) * max + part;  // Calculate the partition result

            maxRes = Math.max(maxRes, res);  // Get the maximum result
        }

        System.out.println("f(" + start + ")" + " = " + maxRes);
        return maxRes;
    }

    public static void main(String[] args) {
        PartitionArrayforMaximumSum ps = new PartitionArrayforMaximumSum();
        int[] arr = {1,15,7,9,2,5,10};
        int k = 3;
        System.out.println(ps.maxSumAfterPartitioning(arr, k));
    }
}
