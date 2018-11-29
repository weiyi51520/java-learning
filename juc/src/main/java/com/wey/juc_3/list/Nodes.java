package com.wey.juc_3.list;

/**
 * @author Yale.Wei
 * @date 2018/10/26 11:12
 */
public class Nodes {

    Node head;

    static class Node{
        public Node next;
        public Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Nodes nodes = new Nodes();

        nodes.head = new Node(0);
        nodes.head.next = new Node(1);
        nodes.head.next.next=new Node(2);
        nodes.head.next.next=new Node(2);

        System.out.println(nodes.head.next.data);
    }
}
