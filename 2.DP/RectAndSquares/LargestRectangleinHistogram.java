import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleinHistogram {
    public int bruteForce(int[] heights) {
        int max = 0;
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            int min_height = heights[i];
            int tempi = i;
            while (tempi >= 0) {
                if (heights[tempi] == 0) {
                    break;
                }
                min_height = Math.min(min_height, heights[tempi]);
                max = Math.max(max, (i - tempi + 1) * min_height);
                tempi--;
            }
        }
        return max;
    }

    public int betterSolution(int[] heights) {
        int max = 0;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n];
        for(int i=0;i<n;i++){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? - 1 : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(heights));
        System.out.println(Arrays.toString(left));
        stack = new Stack<>();
        int[] right = new int[n];
        for(int i=n-1;i>=0;i--){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(right));
        for(int i=0;i<n;i++){
            max = Math.max(max,heights[i] * (right[i] - left[i] - 1));
        }
        return max;
    }
    public int largestRectangleArea(int[] heights){
        int n = heights.length;
        int max = 0;
        Stack<Integer> pse = new Stack<>();
        for(int i=0;i<n;i++){
            while (!pse.isEmpty() && heights[pse.peek()] >= heights[i]) {
                int pop = pse.pop();
                int nse = i;
                int prevSmaller = pse.peek();
                // for this pop  element we have nse and pse next smaller and prev smaller
                max = Math.max(max, heights[pop] * (nse - prevSmaller - 1)) ;               
            }
            pse.push(i);
        }
        while (!pse.isEmpty()) {
            int pop = pse.pop();
            int nse = n;
            int psee = pse.isEmpty() ? -1 : pse.peek();
            max = Math.max(max, heights[pop] * (nse - psee - 1)); 
        }

        return max;
    }



    public static void main(String[] args) {
        LargestRectangleinHistogram ls = new LargestRectangleinHistogram();
        int[] h = { 2, 1, 5, 6, 2, 3 };
        System.out.println(ls.betterSolution(h));
    }
}
