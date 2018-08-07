package com.wangrupeng.tree;

import com.wangrupeng.jianzhioffer.question14.Solution;
import sun.reflect.generics.tree.Tree;

class TreeNode {
    TreeNode left;
    TreeNode right;
    int value;
}

public class TreeDistanceTest {
    private int minDis = 0;
    public int getMinDis(TreeNode root, int count) {
        if (root == null) {
            if (minDis == 0) {
                minDis = count;
            }
            if (count < minDis) {
                minDis = count;
            }
            return minDis;
        }

        getMinDis(root.left, count + 1);
        getMinDis(root.right, count + 1);
        return minDis;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        //root.right = new TreeNode();
        root.left = new TreeNode();
        /*root.right = new TreeNode();
        root.right.left = new TreeNode();
        //root.left.right = new TreeNode();
        root.right.right = new TreeNode();
        root.right.right.left = new TreeNode();*/
        TreeDistanceTest treeDistanceTest = new TreeDistanceTest();
        int result = treeDistanceTest.getMinDis(root, 0);
        System.out.println(result);
    }
}
