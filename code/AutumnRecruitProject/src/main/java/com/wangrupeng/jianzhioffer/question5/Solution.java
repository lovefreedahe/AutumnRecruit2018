package com.wangrupeng.jianzhioffer.question5;

import com.wangrupeng.jianzhioffer.model.ListNode;

import java.util.ArrayList;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = null;
        if(listNode == null) {
            return new ArrayList<>();
        }
        if(listNode.next == null) {
            list = new ArrayList<>();
            list.add(listNode.val);
        } else {
            list = printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode root = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        root.next = node1;
        node1.next = node2;
        ArrayList<Integer> list = solution.printListFromTailToHead(root);
        for (int i : list) {
            System.out.println(i);
        }
    }
}