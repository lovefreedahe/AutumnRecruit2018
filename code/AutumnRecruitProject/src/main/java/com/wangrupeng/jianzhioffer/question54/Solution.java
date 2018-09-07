package com.wangrupeng.jianzhioffer.question54;

import com.wangrupeng.jianzhioffer.model.TreeNode;

public class Solution {
    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null || (pRoot.left == null && pRoot.right == null)) {
            return true;
        }
        return isSymmetrival(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrival(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null && right != null) || (left != null && right == null) || (left.val != right.val)) {
            return false;
        } else {
            return (isSymmetrival(left.left, right.right) && isSymmetrival(left.right, right.left));
        }
    }
}
