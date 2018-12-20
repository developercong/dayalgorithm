package com.congzibank.dayalgrithm.stringalgorithm;

/**
 * Sort a linked list using insertion sort.

 * Created by cong_wang on 2018/12/6.
 */

/**
 * 回顾一下插入排序
 * 1 从头开始遍历，认为前面的已经排序好，因此
 */

public class InsertSort {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * 插入排序
     * 1 设置一个preHead 每次插入后让pre 都冲值乘
     * @param head
     * @return
     */
    public static Node sort(Node head) {
        if (head == null) return null;
        Node preHead = new Node(0);
        Node pre = preHead;
        Node cur = head;
        Node temp = null;

        while (cur != null) {
            temp = cur.next;
            while (pre.next != null && pre.next.value < cur.value) {
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            pre = preHead;// 每次插入后都重置
            cur = temp;
        }
        return preHead.next;
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

     如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

     您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

     示例：

     输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     输出：7 -> 0 -> 8
     原因：342 + 465 = 807
     * @param n1
     * @param n2
     * @return
     */
    public Node addTwoNumbers(Node n1, Node n2) {
        int capacity = 0; //十位数
        int less = 0; //个位数

        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        Node cur1 = n1;
        Node cur2 = n2;
        Node preHead = new Node(0);
        Node pre = preHead;
        while (cur1 != null || cur2 != null) {
            int val1 = cur1 == null ? 0 : cur1.value;
            int val2 = cur2 == null ? 0 : cur2.value;
            less = (val1 + val2 + capacity) % 10;
            capacity = (val1 + val2 + capacity) / 10;
            Node node = new Node(less);
            preHead.next = node;
            preHead = node;
            cur1 = cur1 == null ? cur1 : cur1.next;
            cur2 = cur2 == null ? cur2 : cur2.next;
        }

        if (capacity != 0) {
            preHead.next = new Node(capacity);
        }
        return pre.next;
    }


}
