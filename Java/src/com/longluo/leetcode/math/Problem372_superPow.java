package com.longluo.leetcode.math;

/**
 * 372. 超级次方
 * <p>
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * <p>
 * 示例 1：
 * 输入：a = 2, b = [3]
 * 输出：8
 * <p>
 * 示例 2：
 * 输入：a = 2, b = [1,0]
 * 输出：1024
 * <p>
 * 示例 3：
 * 输入：a = 1, b = [4,3,3,8,5,2]
 * 输出：1
 * <p>
 * 示例 4：
 * 输入：a = 2147483647, b = [2,0,0]
 * 输出：1198
 * <p>
 * 提示：
 * 1 <= a <= 2^31 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b 不含前导 0
 * <p>
 * https://leetcode-cn.com/problems/super-pow/
 */
public class Problem372_superPow {

    public static int superPow(int a, int[] b) {
        if (a == 1) {
            return 1;
        }

        int ans = a;
        int len = b.length;
        for (int i = len - 1; i >= 0; i--) {
            int base = (int) Math.pow(10, len - 1 - i);
            int num = b[i] * base;
            for (int j = 1; j < num; j++) {
                ans = ((ans % 1337) * (a % 1337)) % 1337;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("8 ?= " + superPow(2, new int[]{3}));
        System.out.println("1024 ?= " + superPow(2, new int[]{1, 0}));
        System.out.println("1 ?= " + superPow(1, new int[]{4, 3, 3, 8, 5, 2}));
        System.out.println("1198 ?= " + superPow(2147483647, new int[]{2, 0, 0}));
    }
}
