package com.springboot.levi.leviweb1.algo;


/**
 * 求最大子数组和
 */
public class Solution2 {
    /**
     * 这个算法使用了一个迭代过程，维护两个变量：currentSum 表示以当前元素为结尾的子数组的最大和，
     * maxSum 表示全局最大子数组和。在每一步迭代中，通过比较当前元素和当前元素加上之前子数组和的大小，
     * 更新 currentSum。然后，比较 currentSum 和 maxSum，更新 maxSum。
     *
     * Kadane's算法的核心思想是贪心地选择当前最优解，同时使用动态规划的思想进行状态转移。
     * 这样可以在一次遍历中找到最大的子数组和。
     * @param nums
     * @return
     */
    public static int maxSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is empty or null");
        }
        //表示全局最大子数组之和
        int maxSum = nums[0];
        //当前元素为结尾的子数组的最大和
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = maxSubarraySum(nums);
        System.out.println("Maximum Subarray Sum: " + result);
    }
}
