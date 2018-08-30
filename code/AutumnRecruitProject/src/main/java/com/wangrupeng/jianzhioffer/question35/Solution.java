package com.wangrupeng.jianzhioffer.question35;

import com.wangrupeng.jianzhioffer.model.TreeNode;
import com.wangrupeng.practice.design_pattern.prototype.simple.Prototype;

public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return false;
        }
        return IsBalancedTree(root, 0);
    }

    public boolean IsBalancedTree(TreeNode root, int count) {
        if (root.left == null && root.right != null) {
            if (root.right.right != null || root.right.left != null) {
                return false;
            }
        }
        if (root.left != null && root.right == null) {
            if (root.left.left != null || root.left.right != null) {
                return false;
            }
        }
        return IsBalancedTree(root.left, 0);


    }

}
