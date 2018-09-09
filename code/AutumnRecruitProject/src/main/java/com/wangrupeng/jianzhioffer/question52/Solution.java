package com.wangrupeng.jianzhioffer.question52;

import com.wangrupeng.jianzhioffer.model.ListNode;

public class Solution {
    public ListNode deleteDuplication(ListNode pHead){
        if (pHead == null ) {
            return pHead;
        }
        pHead = deleteDuplicateHeader(pHead);
        if (pHead == null) {
            return pHead;
        }
        ListNode temp = pHead;
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

        return pHead;
    }

    private ListNode deleteDuplicateHeader(ListNode header) {
        if (header == null) {
            return null;
        }
        if (header.next != null && header.val == header.next.val) {
            while (header.next != null && header.val == header.next.val) {
                header = header.next;
            }
            header = header.next;
            return deleteDuplicateHeader(header);
        }
        return header;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        Solution solution = new Solution();
        ListNode h = solution.deleteDuplication(head);
        while (h != null) {
            System.out.print(h.val + " ");
            h = h.next;
        }

    }
}
