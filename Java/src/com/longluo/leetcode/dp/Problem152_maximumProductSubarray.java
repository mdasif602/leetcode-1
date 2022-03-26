package com.longluo.leetcode.dp;

/**
 * 152. 乘积最大子数组
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 提示:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 * <p>
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class Problem152_maximumProductSubarray {

    public static int maxProduct_bf(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums[0];
        }

        int max = nums[0];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int product = 1;
            for (int j = i; j < len; j++) {
                product = product * nums[j];
                max = Math.max(max, product);
            }
        }

        return max;
    }

    public static int maxProduct_dp(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] * nums[i], dp[i - 1]);
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("-2 ?= " + maxProduct_bf(new int[]{-2}));
        System.out.println("6 ?= " + maxProduct_bf(new int[]{2, 3, -2, 4}));
        System.out.println("24 ?= " + maxProduct_bf(new int[]{-2, 3, -4}));
        System.out.println("0 ?= " + maxProduct_bf(new int[]{-2, 0, -1}));
    }
}
