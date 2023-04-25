package algorithmbasic.class10;

// 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SmallerEqualBigger {
    // 内部类
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一
    public static Node listPartition1(Node head, int pivot) {
        Node cur = head;
        Node SH = null;
        Node ST = null;
        Node EH = null;
        Node ET = null;
        Node BH = null;
        Node BT = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = null;
            if (cur.value < pivot) {
                if (ST == null) {
                    SH = ST = cur;
                } else {
                    ST.next = cur;
                    ST = cur;
                }
            }

            if (cur.value == pivot) {
                if (ET == null) {
                    EH = ET = cur;
                } else {
                    ET.next = cur;
                    ET = cur;
                }
            }

            if (cur.value > pivot) {
                if (BT == null) {
                    BH = BT = cur;
                } else {
                    BT.next = cur;
                    BT = cur;
                }
            }

            cur = next;
        }
        // 三个区域的连接
        ST.next = EH;
        ET.next = BH;

        if (ST != null) {
            ST.next = EH;
            ET = ET == null ? ST : ET;
        }

        if (ET != null) {
            ET.next = BH;
        }

        return SH != null ? SH : (EH != null ? EH : BH);
    }

    // 方法二：
    public static Node listPartition2(Node head, int pivot) {
        if(head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> list = new ArrayList<>();
        int count = 0;
        while (cur != null) {
            list.add(cur);
            count++;
            cur = cur.next;
        }
        list.sort(new myComparator());
        head = list.get(0);
        for (int i = 1; i < count; i++) {
            list.get(i - 1).next = list.get(i);
        }
        return head;
    }

    // 比较器
    public static class myComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }
}


