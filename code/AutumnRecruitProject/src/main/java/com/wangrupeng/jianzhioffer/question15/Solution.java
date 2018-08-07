package com.wangrupeng.jianzhioffer.question15;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode temp = ReverseList(head.next);
        head.next = null;
        temp.next = head;
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
        ListNode result = solution.ReverseList(head);
        System.out.println(result.val);
    }
}
