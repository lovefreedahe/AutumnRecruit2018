package com.wangrupeng.jianzhioffer.question52;

import com.wangrupeng.jianzhioffer.model.ListNode;

public class Solution {
    public ListNode deleteDuplication(ListNode pHead){
        ListNode head = pHead;
        if (pHead.next == null) {
            return pHead;
        }
        if (head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode temp = head;
        ListNode pre = temp;
        while (temp.next != null) {
            if (temp.val != temp.next.val) {
                pre = temp;
                temp = temp.next;
            } else {
                pre.next = temp.next.next;
                temp = pre;
            }
        }

        return head;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        Solution solution = new Solution();
        solution.deleteDuplication(head);
    }
}
