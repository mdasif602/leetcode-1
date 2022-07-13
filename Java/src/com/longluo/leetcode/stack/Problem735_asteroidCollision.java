package com.longluo.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 735. 行星碰撞
 * <p>
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * <p>
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。
 * 每一颗行星以相同的速度移动。
 * <p>
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。
 * 两颗移动方向相同的行星，永远不会发生碰撞。
 * <p>
 * 示例 1：
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * <p>
 * 示例 2：
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * <p>
 * 示例 3：
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * <p>
 * 提示：
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * <p>
 * https://leetcode.cn/problems/asteroid-collision/
 */
public class Problem735_asteroidCollision {

    // TODO: 2022/7/13
    // Stack time: O(n) space: O(n)
    public static int[] asteroidCollision(int[] asteroids) {
        int len = asteroids.length;
        Stack<Integer> stk = new Stack<>();
        int idx = 0;
        while (idx < len) {
            if (stk.empty()) {
                stk.push(asteroids[idx++]);
            } else {
                if (!stk.empty() && stk.peek() * asteroids[idx] > 0) {
                    stk.push(asteroids[idx++]);
                } else if (!stk.empty() && stk.peek() * asteroids[idx] < 0) {
                    if (Math.abs(stk.peek()) == Math.abs(asteroids[idx])) {
                        stk.pop();
                    } else if (Math.abs(stk.peek()) > Math.abs(asteroids[idx])) {
                        continue;
                    } else {
                        while (!stk.empty() && stk.peek() * asteroids[idx] < 0
                                && Math.abs(stk.peek()) < Math.abs(asteroids[idx])) {
                            stk.pop();
                        }
                    }
                }
            }
        }

        if (stk.empty()) {
            return new int[]{};
        }

        int[] ans = new int[stk.size()];
        int i = 0;
        while (!stk.empty()) {
            ans[i++] = stk.pop();
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("[5, 10] ?= " + Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
    }
}
