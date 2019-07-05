package dataStructure.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class traversal {
    //         A
    //        /\
    //       B  C
    //      /\
    //     D E
    //

    //前序  A,B,D,E,C
    //
    //  visit node
    //  traverse(left child)
    //  traverse(right child)
    //
    //中序  D,B,E,A,C
    //
    //  traverse(left child)
    //  visit node
    //  traverse(right child)
    //
    //后序  D,E,B,C,A
    //
    //  traverse(left child)
    //  traverse(right child)
    //  visit  node
    //
    //层序 A,B,C,D,E
    //
    // create a queue called nodes
    // create an unordered list called results
    // enqueue the root onto the nodes queue
    // while the nodes queue is not empty {
    //
    //   dequeue the first element form the queue
    //   If that element is not null
    //      Add that element to the rear of the result list
    //      Enqueue the children of the element to the nodes queue
    //   Else
    //      Add null on the result list
    // }
    // Return an Iterator for the result list
    public static void levelTraverse(Node root) {
        if (root == null)
            return;
        Queue<Node> q = new ArrayDeque<Node>();
        q.add(root);
        Node cur;
        while (!q.isEmpty()) {
            cur = q.peek();
            System.out.print(cur.value + " ");
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
            q.poll();
        }
    }

    public class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value){
            this.value = value;
            left = null;
            right = null;
        }
    }

}
