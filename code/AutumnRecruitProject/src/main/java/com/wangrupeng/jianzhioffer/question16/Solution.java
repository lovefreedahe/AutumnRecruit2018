package com.wangrupeng.jianzhioffer.question16;

import com.wangrupeng.jianzhioffer.model.ListNode;

public class Solution {

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = new ListNode();
        ListNode next = new ListNode();
        head.next = next;
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        if (temp1.val < temp2.val) {
            head.val = temp1.val;
            temp1 = temp1.next;
        } else {
            head.val = temp2.val;
            temp2 = temp2.next;
        }
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                next.val = temp1.val;
                temp1 = temp1.next;
            } else {
                next.val = temp2.val;
                temp2 = temp2.next;
            }
            next.next = new ListNode();
            next = next.next;
        }

        if (temp1 == null && temp2 != null) {
            while (temp2 != null) {
                next.val = temp2.val;
                temp2 = temp2.next;
                if (temp2 != null) {
                    next.next = new ListNode();
                    next = next.next;
                }
            }
        }

        if (temp1 != null && temp2 == null ) {
            while (temp1 != null) {
                next.val = temp1.val;
                temp1 = temp1.next;
                if (temp1 != null) {
                    next.next = new ListNode();
                    next = next.next;
                }
            }
        }

        return head;
    }

    public static void main(String[] args) {
        /*int i = 0;
        ListNode head = new ListNode(i);
        i=i+2;
        head.next = new ListNode(i);
        i=i+2;
        head.next.next = new ListNode(i);
        i=i+2;
        head.next.next.next = new ListNode(i);
        i=i+2;
        head.next.next.next.next = new ListNode(i);
        i=i+2;
        head.next.next.next.next.next = new ListNode(i);

        i = 1;
        ListNode list2 = new ListNode(i);
        i=i+2;
        list2.next = new ListNode(i);
        i=i+2;
        list2.next.next = new ListNode(i);
        i=i+2;
        list2.next.next.next = new ListNode(i);
        i=i+2;
        list2.next.next.next.next = new ListNode(i);
        i=i+2;
        list2.next.next.next.next.next = new ListNode(i);*/

        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(5);

        Solution solution = new Solution();
        solution.Merge(head,list2);
    }
}
