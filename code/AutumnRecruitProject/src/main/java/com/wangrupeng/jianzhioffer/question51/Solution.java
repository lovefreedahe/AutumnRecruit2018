package com.wangrupeng.jianzhioffer.question51;

import com.wangrupeng.jianzhioffer.model.ListNode;

public class Solution {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        boolean hasLoop = false;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) {
            return null;
        }
        int count = 1;
        ListNode temp = slow.next;
        while (temp != slow) {
            temp = temp.next;
            count++;
        }
        slow = pHead;
        fast = pHead;
        for (int i = 0;i < count;i++) {
            fast = fast.next;
        }
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {

    }
}
