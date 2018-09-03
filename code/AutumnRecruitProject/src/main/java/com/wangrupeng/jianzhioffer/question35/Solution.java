package com.wangrupeng.jianzhioffer.question35;

import com.wangrupeng.jianzhioffer.model.TreeNode;
import com.wangrupeng.practice.design_pattern.command.MakeDir;
import com.wangrupeng.practice.design_pattern.prototype.simple.Prototype;

public class Solution {
    private boolean isBalance = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        } else if (Math.abs(treeDepth2(root.left) - treeDepth2(root.right)) > 1) {
            return false;
        } else {
            return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
        }
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }

    public int treeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth2(root.left);
        int right = treeDepth2(root.right);
        if (Math.abs(left - right) > 1) {
            isBalance = false;
        }
        return Math.max(left, right) + 1;
    }

}
