package com.longluo.top_interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * 29. 两数相除
 * <p>
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * <p>
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是[−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回2^31 − 1。
 * <p>
 * https://leetcode-cn.com/problems/divide-two-integers/
 */
public class Problem29_divideTwoIntegers {

    /**
     * use long
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        long dividendLong = dividend;
        long divisorLong = divisor;

        boolean isNegative = false;
        if (dividendLong < 0 && divisorLong < 0) {
            dividendLong = -dividendLong;
            divisorLong = -divisorLong;
        } else if (dividendLong < 0 && divisorLong > 0) {
            isNegative = true;
            dividendLong = -dividendLong;
        } else if (dividendLong > 0 && divisorLong < 0) {
            isNegative = true;
            divisorLong = -divisorLong;
        }

        long ans = 0;
        while (dividendLong >= divisorLong) {
            dividendLong -= divisorLong;
            ans++;
        }

        if (isNegative) {
            ans = -ans;
        }

        if (ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) ans;
    }


    /**
     * use int
     */
    public static int divide_int(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (divisor == 1) {
            return dividend;
        }

        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            }

            return Integer.MAX_VALUE;
        }

        int ans = 0;
        boolean isNegative = true;
        if (dividend > 0 && divisor > 0) {
            dividend = -dividend;
            isNegative = false;
        } else if (dividend > 0 && divisor < 0) {
            dividend = -dividend;
            divisor = -divisor;
        } else if (dividend < 0 && divisor < 0) {
            isNegative = false;
            divisor = -divisor;
        }

        while (dividend + divisor <= 0) {
            dividend += divisor;
            ans++;
        }

        if (isNegative) {
            ans = -ans;
        }

        return ans;
    }

    /**
     *  fast
     */
    public static int divide_fast(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (divisor == 1) {
            return dividend;
        }

        if (divisor == -1) {
            if (dividend > Integer.MIN_VALUE) {
                return -dividend;
            }

            return Integer.MAX_VALUE;
        }

        long ans = 0;
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }

        long tdividend = dividend;
        long tdivisor = divisor;

        tdividend = tdividend > 0 ? tdividend : -tdividend;
        tdivisor = tdivisor > 0 ? tdivisor : -tdivisor;
        ans = fastDiv(tdividend, tdivisor);
        if (sign > 0 && ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return sign > 0 ? (int)ans : (int)-ans;
    }

    public static long fastDiv(long a, long b) {
        if (a < b) {
            return 0;
        }

        int count = 1;
        long tb = b;
        while ((tb + tb) <= a) {
            count <<= 1;
            tb <<= 1;
        }

        return count + fastDiv(a - tb, b);
    }

    /**
     * quickAdd
     */
    public static boolean quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0;
        int add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 result + add >= x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }

        return true;
    }

    public static int divide_quick(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }

        return rev ? -ans : ans;
    }

    public static void main(String[] args) {
        int val = Math.abs(-2147483648);
        System.out.println("val = " + val);

        System.out.println("15 ?= " + divide_quick(31, 2));

        System.out.println("3 ?= " + divide_fast(10, 3));
        System.out.println("3 ?= " + divide_fast(2147483647, 2));

        System.out.println("2147483648 ?= " + Math.abs(-2147483648));

        System.out.println("3 ?= " + divide(10, 3));
        System.out.println("-2 ?= " + divide(7, -3));
        System.out.println("1 ?= " + divide(1, 1));
        System.out.println("-1 ?= " + divide(-1, 1));
        System.out.println("-1 ?= " + divide_int(-1, 1));
        System.out.println("2147483647 ?= " + divide(-2147483648, -1));
        System.out.println("-2147483648 ?= " + divide(-2147483648, 1));
        System.out.println("-1073741824 ?= " + divide(-2147483648, 2));

        quickAdd(-3, 3, -10);
        quickAdd(-3, 4, -10);
        quickAdd(-3, 2, -2);
        quickAdd(-3, 1, -5);
        quickAdd(-3, 2, -5);
    }
}
