package com.wangrupeng.jianzhioffer.question53;

import com.wangrupeng.jianzhioffer.model.TreeLinkNode;

public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.right == null) {
            if (isLeft(pNode) == 1 && isLeft(pNode.next) == 1) {
                return null;
            } else if (isLeft(pNode) == 1 && isLeft(pNode.next) == -1) {
                return pNode.next.next;
            } else {
                return pNode.next;
            }
        } else {
            return getLeft(pNode.right);
        }
    }

    //left:-1
    //root:0
    //right:1
    private int isLeft(TreeLinkNode pNode) {
        if (pNode.next == null) {
            return 0;
        } else if (pNode.next.left == pNode) {
            return -1;
        } else {
            return 1;
        }
    }

    private TreeLinkNode getLeft(TreeLinkNode pNode) {
        while (pNode.left != null) {
            pNode = pNode.left;
        }
        return pNode;
    }
}
