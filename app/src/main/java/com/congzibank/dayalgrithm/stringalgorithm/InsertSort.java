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

}
