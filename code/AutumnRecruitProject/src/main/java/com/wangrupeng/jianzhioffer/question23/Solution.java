package com.wangrupeng.jianzhioffer.question23;

import com.wangrupeng.jianzhioffer.model.TreeNode;

import java.util.ArrayList;


public class Solution {
    private ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return paths;
        }
        ArrayList<Integer> path = new ArrayList<>();
        FindPath(root, path, 0, target);
        return paths;
    }

    private void FindPath(TreeNode root, ArrayList<Integer> path, int currentSum, int target) {
        currentSum += root.val;
        path.add(root.val);
        boolean isLeaf = root.left == null && root.right == null ? true:false;
        if (currentSum == target && isLeaf) {
            ArrayList<Integer> list = new ArrayList<>(path.size());
            for (int i : path) {
                list.add(i);
            }
            paths.add(list);
        }
        if (root.left != null) {
            FindPath(root.left, path, currentSum, target);
        }
        if (root.right != null) {
            FindPath(root.right, path, currentSum, target);

        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(12);
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> paths = solution.FindPath(root, 22);
        for (ArrayList<Integer> path : paths) {
            for (int i : path) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
