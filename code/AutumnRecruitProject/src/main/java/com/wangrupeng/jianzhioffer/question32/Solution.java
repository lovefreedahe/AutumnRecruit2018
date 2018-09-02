package com.wangrupeng.jianzhioffer.question32;

import com.wangrupeng.jianzhioffer.model.ListNode;

public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int length1 = 0, length2 = 0;
        ListNode temp = pHead1;
        while (temp != null) {
            length1++;
            temp = temp.next;
        }
        temp = pHead2;
        while (temp != null) {
            length2++;
            temp = temp.next;
        }
        ListNode temp1 = pHead1;
        ListNode temp2 = pHead2;
        if (length2 < length1) {
            for (int i = 0;i < length1 - length2;i++) {
                temp1 = temp1.next;
            }
        } else {
            for (int i = 0;i < length1 - length2;i++) {
                temp2 = temp2.next;
            }
        }

        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
            if (temp1 == null || temp2 == null) {
                return null;
            }
        }
        return temp1;
    }

    public static void main(String[] args) {

    }
}
