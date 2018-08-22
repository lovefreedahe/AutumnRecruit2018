package com.wangrupeng.jianzhioffer.question6;

import com.wangrupeng.jianzhioffer.model.TreeNode;

public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        TreeNode root = new TreeNode(pre[0]);
        int i = 0;
        for (;  i < in.length ; i++) {
            if (in[i] == pre[0]){
                break;
            }
        }
        int mark = 0;
        if (i == 0) {
            root.left = null;
        } else {
            root.left = childTree(pre, mark + 1, in, 0, i - 1);
        }
        if (i == pre.length - 1) {
            root.right = null;
        } else {
            root.right= childTree(pre, i - 0 + 1, in, i + 1, in.length - 1);
        }
        return root;
    }

    private TreeNode childTree(int pre[], int mark, int[] in, int inStart, int inEnd) {
        TreeNode root = new TreeNode(pre[mark]);
        if (inStart == inEnd) {
            return root;
        }
        int position = inStart;
        for (; position <= inEnd; position ++) {
            if (in[position] == pre[mark]) {
                break;
            }
        }

        if (position == inStart) {
            root.left = null;
        } else {
            root.left = childTree(pre, mark + 1, in, inStart, position - 1);
        }
        if (position == inEnd) {
            root.right = null;
        } else {
            root.right = childTree(pre, position - inStart + mark + 1, in, position + 1, inEnd);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        int[] pre1 = {1, 2, 3, 4};
        int[] in1 = {4, 3, 2 ,1};
        Solution solution = new Solution();
        TreeNode root = solution.reConstructBinaryTree(pre1,in1);
        System.out.println();
    }
}
