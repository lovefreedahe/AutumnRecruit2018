package com.wangrupeng.jianzhioffer.question34;

import com.wangrupeng.jianzhioffer.model.TreeNode;

public class Solution {
    private int deep = 0;
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeDepthCount(root, 0);
        return deep;
    }

    private int TreeDepthCount(TreeNode root, int count) {
        if (root != null) {
            TreeDepthCount(root.left, count + 1);
            TreeDepthCount(root.right, count + 1);
        }
        if (count > deep) {
            deep = count;
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
