package com.longluo.leetcode.bitmanipulation;

/**
 * 318. 最大单词长度乘积
 * <p>
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * <p>
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * <p>
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * <p>
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * <p>
 * 提示：
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 * <p>
 * https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
 */
public class Problem318_maxProduct {

    public static int maxProduct(String[] words) {
        if (words == null || words.length <= 1) {
            return 0;
        }

        int n = words.length;
        boolean[][] array = new boolean[n][26];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (char ch : word.toCharArray()) {
                array[i][ch - 'a'] = true;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean flag = true;
                for (int k = 0; k < 26; k++) {
                    if (array[i][k] && array[j][k]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
