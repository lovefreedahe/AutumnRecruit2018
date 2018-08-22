package com.wangrupeng.jianzhioffer.question17;

import com.wangrupeng.jianzhioffer.model.TreeNode;

public class Solution {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean hasSubTree = false;
        if (root1 != null && root2 != null) {
            hasSubTree = exactlyHasSubTree(root1, root2);
            if (!hasSubTree) {
                hasSubTree = HasSubtree(root1.left, root2);
            }
            if (!hasSubTree) {
                hasSubTree = HasSubtree(root1.right, root2);
            }
        }

        return hasSubTree;
    }

    public boolean exactlyHasSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }
        return exactlyHasSubTree(root1.left,root2.left) && exactlyHasSubTree(root1.right, root2.right);
    }

    public static void main(String[] args) {

    }
}
