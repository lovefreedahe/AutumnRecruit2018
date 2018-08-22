package com.wangrupeng.jianzhioffer.question15;

import com.wangrupeng.jianzhioffer.model.ListNode;

public class Solution {
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null){
            return head;
        }

        ListNode temp = head;
        ListNode preNode = null;
        while (temp != null) {
            ListNode nextNode = temp.next;
            temp.next = preNode;
            preNode = temp;
            temp = nextNode;
        }
        return preNode;
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
