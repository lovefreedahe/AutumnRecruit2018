package com.wangrupeng.jianzhioffer.question5;

import java.util.ArrayList;

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