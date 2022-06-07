package com.longluo.top100;

/**
 * 62. 不同路径
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * <p>
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * <p>
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * <p>
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * <p>
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 * <p>
 * https://leetcode.com/problems/unique-paths/
 */
public class Problem62_uniquePaths {

    // DP time: O(m * n) space: O(m * n)
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i][j - 1] + dp[i - 1][j]);
            }
        }

        return dp[m - 1][n - 1];
    }

    // Math time: O(m) space: O(1)
    // Exceed Long Limit
    public static int uniquePaths_math(int m, int n) {
        long numerator = 1;
        for (int i = 1; i < m + n - 1; i++) {
            numerator = numerator * i;
        }

        long denominator = 1;
        for (int i = 1; i < m; i++) {
            denominator = denominator * i;
        }

        for (int i = 1; i < n; i++) {
            denominator = denominator * i;
        }

        return (int)(numerator / denominator);
    }

    public static void main(String[] args) {
        System.out.println("3 ?= " + uniquePaths(3, 2));
        System.out.println("28 ?= " + uniquePaths(7, 3));
        System.out.println("28 ?= " + uniquePaths_math(7, 3));
        System.out.println("48620 ?= " + uniquePaths_math(10, 10));
    }
}
