package com.wangrupeng.jianzhioffer.question14;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode headCopy = head;
        for (int i = 0;i < k - 1;i++) {
            headCopy = headCopy.next;
            if (headCopy == null) {
                return null;
            }
        }
        while (headCopy.next != null) {
            head = head.next;
            headCopy = headCopy.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int i = 0;
        ListNode head = new ListNode(i++);
        head.next = new ListNode(i++);
        head.next.next = new ListNode(i++);
        head.next.next.next = new ListNode(i++);
        head.next.next.next.next = new ListNode(i++);
        head.next.next.next.next.next = new ListNode(i++);
        Solution solution = new Solution();
        ListNode result = solution.FindKthToTail(head, 2);
        System.out.println(result.val);
    }
}
