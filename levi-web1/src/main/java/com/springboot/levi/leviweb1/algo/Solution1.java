package com.springboot.levi.leviweb1.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和的不重复等于0的结果
 */
public class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重复的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[left] + nums[right];

                if (currentSum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 避免重复的元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution1 threeSum = new Solution1();
        int[] nums = {-1, 0, 1, 2, -1, -4,-1,-1};
        List<List<Integer>> result = threeSum.threeSum(nums);
        System.out.println(result);
    }
}
