package com.springboot.levi.leviweb1.algo;

import java.util.HashSet;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Solution3 {

    /**
     * 你可以使用滑动窗口的方法来解决这个问题。滑动窗口是一种在字符串上通过两个指针移动窗口的技巧，用于解决子串或子数组的问题
     * 在这个例子中，使用两个指针 left 和 right 来表示滑动窗口的边界。set 用于存储当前窗口内的字符，确保窗口内没有重复字符。
     * 通过移动右指针来扩展窗口，如果遇到重复字符，则移动左指针来缩小窗口。在每一步中，都更新最大子串的长度。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        int result = lengthOfLongestSubstring(s);
        System.out.println("The length of the longest substring without repeating characters is: " + result);
    }

}
