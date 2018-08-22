package com.wangrupeng.jianzhioffer.question21;

import com.wangrupeng.jianzhioffer.model.TreeNode;

import java.util.ArrayList;

public class Solution {
    private ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null) {
            return list;
        }
        list.add(root.val);
        addChild(root);
        return list;
    }

    private void addChild(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            list.add(root.left.val);
        }
        if (root.right != null) {
            list.add(root.right.val);
        }
        addChild(root.left);
        addChild(root.right);
    }
}
