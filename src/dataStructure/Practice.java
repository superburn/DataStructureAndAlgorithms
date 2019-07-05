package dataStructure;

public class Practice {
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
/////////////////////////////////二叉树////////////////////////////////////////////
    //计算二叉树的深度，两行递归即可搞定。
    public static int level(Node root) {
        if (root == null)
            return 0;

        return level(root.left) + 1 > level(root.right) + 1 ? level(root.left) + 1
                : level(root.right) + 1;
    }



/////////////////////////////////链表/////////////////////////////////////////////////
}
