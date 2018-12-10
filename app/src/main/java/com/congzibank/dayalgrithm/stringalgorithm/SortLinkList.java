package com.congzibank.dayalgrithm.stringalgorithm;

import android.opengl.GLSurfaceView;

/**
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 对链表进行排序
 * Created by cong_wang on 2018/12/6.
 */

public class SortLinkList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * 使用归并排序
     * 1 使用快慢指针--将得到链表一分为二 继续分成最小，然后两两合并
     * @param head
     * @return
     */
    public static Node sortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node mid = getMidNode(head);
        Node next = mid.next; //得到中间指针，划分成两个链表
        mid.next = null;

        //递归的调用自己的划分为2 然后再不断合并
        return mergeSortedList(sortList(head), sortList(next));
    }

    /**
     * 使用快慢指针得到链表中间的指针
     * @param head
     * @return
     */
    private static Node getMidNode(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        Node fast = null;
        Node slow = null;
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 合并两个有序列表
     * @param h1
     * @param h2
     * @return
     */
    public static Node mergeSortedList(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        //得到哪个 是头节点
        Node head = h1.value < h2.value ? h1 : h2;
        //得到去除头节点之后的开始界面位置
        Node cur1 = head == h1 ? h1.next : h1;
        Node cur2 = head == h2 ? h2.next : h2;
        Node pre = null;
        Node next;
        //判断cur1 小于cur2 时 一直往后遍历，直到大于时，修改链表指针
        //使得pre.next = cur2,cur2.next = cur1 这样就串起来了，最后修改cur2 和pre指针
        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                 pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        //走出循环的时候肯定有一个node 为null 因此把不是null的链表连在后面
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }


    /**
     * 两两反转 链表
     * @param head
     * @return
     */
    public Node swapPairs(Node head) {
        Node pre = new Node(0);
        pre.next = head;
        Node res = pre;//用于最后返回结果 因为stHead会改变
        Node a = null;
        Node b = null;
        while (pre.next != null && pre.next.next != null) {
            a = pre.next;
            b = pre.next.next;
            //一般的交换都会从屁股开始，如果是python的话 这个交换就很简单---
            a.next = b.next;
            b.next = a;
            pre.next = b;
            pre = pre.next.next;
        }
        return res.next;
    }

    /**
     * 合并两个有序链表
     * @param n1
     * @param n2
     * @return
     */
    public Node mergeTwoLists(Node n1, Node n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;
        Node head = null;
        Node newN1 = null;
        Node newN2 = null;
        Node pre = null;
        if (n1.value <= n2.value) {
            head = n1;
            newN1 = n1;
            newN2 = n2;
        } else {
            head = n2;
            newN1 = n2;
            newN2 = n1;
        }

        while (newN1 != null && newN2 != null) {
            if (newN1.value <= newN2.value) {
                pre = newN1;
                newN1 = newN1.next;
            } else {
                Node temp = newN2.next;
                pre.next = newN2;
                newN2.next = newN1;
                pre = newN2;
                newN2 = temp;
            }
        }
        pre.next = newN1 == null ? newN2 : newN1;
        return head;
    }


    public Node reverseList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Node pre = null;

        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0) return null;
        //什么时候停止循环？
        int length = lists.length;
        while (length > 1) {
            int K = (length + 1) / 2;
            for (int i = 0 ; i < length / 2; i ++) {
                lists[i] = mergeSortedList(lists[i], lists[i + K]);
            }
            length = K;
        }
        return lists[0];
    }

}