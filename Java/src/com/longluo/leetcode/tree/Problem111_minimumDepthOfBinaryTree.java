package com.longluo.leetcode.tree;

import com.longluo.datastructure.TreeNode;
import com.longluo.datastructure.TreeUtils;

/**
 * 111. 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * <p>
 * 提示：
 * 树中节点数的范围在 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 */
public class Problem111_minimumDepthOfBinaryTree {

    public static int minDepth(TreeNode root) {
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int level) {
        if (root == null) {
            return level;
        }

        if (root.left == null && root.right != null) {
            return dfs(root.right, level + 1);
        }

        if (root.left != null && root.right == null) {
            return dfs(root.left, level + 1);
        }

        return Math.min(dfs(root.left, level + 1), dfs(root.right, level + 1));
    }

    public static void main(String[] args) {
        TreeNode tst1 = TreeUtils.constructTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("2 ?= " + minDepth(tst1));

        TreeNode tst2 = TreeUtils.constructTree(new Integer[]{2, null, 3, null, 4, null, 5, null, 6});
        System.out.println("5 ?= " + minDepth(tst2));

        TreeNode tst3 = TreeUtils.constructTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println("2 ?= " + minDepth(tst3));

        TreeNode tst4 = TreeUtils.constructTree(new Integer[]{});
        System.out.println("0 ?= " + minDepth(tst4));

        TreeNode tst5 = TreeUtils.constructTree(new Integer[]{1});
        System.out.println("1 ?= " + minDepth(tst5));
    }
}
