package com.wangrupeng.jianzhioffer.question18_t;

import com.wangrupeng.jianzhioffer.model.TreeNode;

public class Solution {
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror(root.left);
        Mirror(root.right);
    }

}
