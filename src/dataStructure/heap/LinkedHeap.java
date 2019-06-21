package dataStructure.heap;

public class LinkedHeap<T> {
    private HeapNode root;
    private HeapNode lastNode;
    private int count;

    public class HeapNode<T> {
        private T element;
        private HeapNode<T> parent;
        private HeapNode<T> left;
        private HeapNode<T> right;

        public HeapNode(T ele) {
            element = ele;
            parent = null;
            left = null;
            right = null;
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    //在恰当的位置处添加一个元素, 2*log n
    //对堆进行重排序 O(1)
    //将lastNode指针重新设置为指向新的最末结点 log n
    //O(log n)
    public void addElement(T element) {
        HeapNode<T> node = new HeapNode<T>(element);

        if (root == null) {
            root = node;
        } else {
            HeapNode<T> nextParent = getNextParentAdd();
            if (nextParent.left == null) {
                nextParent.left = node;
            } else {
                nextParent.right = node;
            }

            node.parent = nextParent;
        }

        lastNode = node;
        count++;

        if (size() > 0) {
            heapifyAdd();
        }
    }

    private HeapNode<T> getNextParentAdd() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.parent.left != result)) {
            result = result.parent;
        }

        if (result != root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = result.parent.right;
                while (result.left != null) {
                    result = result.left;
                }
            }
        } else {
            while (result.left != null) {
                result = result.left;
            }
        }
        return result;
    }

    private void heapifyAdd() {
        T temp;
        HeapNode<T> next = lastNode;

        temp = next.element;

        while ((next != root) && ((Comparable) temp).compareTo(next.parent.element) < 0) {
            next.parent.element = temp;
            next = next.parent;
        }

        next.element = temp;
    }

    //用存储在最末结点处的元素替换存储在根处的元素O(1)
    //如有必要对堆进行重排序O(log n)
    //返回初始的根元素 2*log n
    //O(log n)
    public T removeMin() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        T minElement = (T) root.element;

        if (size() == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode<T> nextLast = getNextLastNode();
            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }

            root.element = lastNode.element;
            lastNode = nextLast;
            count--;
            heapifyRemove();
        }

        return minElement;
    }

    private HeapNode<T> getNextLastNode() {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.parent.left) == result) {
            result = result.parent;
        }

        if (result != root) {
            result = result.parent.left;
        }

        while (result.right != null) {
            result = result.right;
        }

        return result;
    }

    private void heapifyRemove() {
        T temp;
        HeapNode<T> node = root;
        HeapNode<T> left = node.left;
        HeapNode<T> right = node.right;
        HeapNode<T> next;

        if (left == null && right == null) {
            next = null;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.element).compareTo(right.element) < 0) {
            next = left;
        } else {
            next = right;
        }

        temp = node.element;

        while (next != null && ((Comparable) next.element).compareTo(temp) < 0) {
            node.element = next.element;
            node = next;
            left = node.left;
            right = node.right;

            if (left == null && right == null) {
                next = null;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.element).compareTo(right.element) < 0) {
                next = left;
            } else {
                next = right;
            }

            node.element = temp;
        }
    }
}
