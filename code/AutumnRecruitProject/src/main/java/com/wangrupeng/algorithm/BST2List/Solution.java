package com.wangrupeng.algorithm.BST2List;

import com.wangrupeng.jianzhioffer.model.TreeNode;

import java.util.Stack;

/**
 * 将二叉搜索树转化为双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Solution {
    public TreeNode Convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode preNode = null;
        boolean isFirst = true;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (isFirst) {
                root = current;
                preNode = root;
                isFirst = false;
            } else {
                preNode.right = current;
                current.left = preNode;
                preNode = current;
            }
            current = current.right;
        }
        return root;
    }
}
