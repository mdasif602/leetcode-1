package com.longluo.studyplan.binary_search;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * <p>
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * <p>
 * 示例 1:
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * <p>
 * 注意:
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 * <p>
 * https://leetcode-cn.com/problems/valid-triangle-number/
 */
public class Problem611_validTriangleNumber {

    // BF time: O(n^3) space: O(1)
    // TimeOut
    public static int triangleNumber_bf(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }

        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] <= 0) {
                continue;
            }

            for (int j = i + 1; j < len - 1; j++) {
                if (nums[j] <= 0) {
                    continue;
                }

                for (int k = j + 1; k < len; k++) {
                    if (nums[k] <= 0) {
                        continue;
                    }

                    if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i]) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    // Sort + BF time: O(n^3) space: O(logn)
    // AC
    public static int triangleNumber_sort(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return 0;
        }

        Arrays.sort(nums);
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (nums[j] <= 0) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        ans++;
                    } else {
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("0 ?= " + triangleNumber_bf(new int[]{7, 0, 0, 0}));
        System.out.println("10 ?= " + triangleNumber_bf(new int[]{24, 3, 82, 22, 35, 84, 19}));
        System.out.println("3 ?= " + triangleNumber_bf(new int[]{2, 2, 3, 4}));
        System.out.println("3 ?= " + triangleNumber_sort(new int[]{2, 2, 3, 4}));
    }
}
