package com.springboot.levi.leviweb1.algo;

/**
 * 买股票的最佳时期
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class Solution4 {

    /**
     * 思路：其中利用动态规划思想来求解最大利润
     *  dp[i] 表示在第 i 天卖出股票的最大利润。通过迭代数组，我们可以计算出最终的最大利润。
     *  minPrice 用于记录遍历过程中的最低股价，以便在每一天都选择最低点买入。最终返回 dp[n - 1]，即最后一天卖出股票的最大利润。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[] dp = new int[n]; // dp[i]表示第i天卖出股票的最大利润

        int minPrice = prices[0]; // 记录最低股价

        for (int i = 1; i < n; i++) {
            // 在第i天卖出股票的最大利润是在前i-1天找到的最低价格买入
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
            // 更新最低股价
            minPrice = Math.min(minPrice, prices[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = maxProfit1(prices);
        System.out.println("The maximum profit is: " + result);
    }

    public static  int maxProfit1(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
