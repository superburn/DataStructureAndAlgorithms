package dataStructure.heap;

import java.util.Arrays;

//对于每一结点n,n的左孩子将位于数组的2n+1位置处,
//n的游孩子将位于数组的2(n+1)位置处
//除根结点外的结点n,n的双亲位于(n-1)/2位置处
public class ArrayHeap<T> {

    private static final int DEFAULT_CAPACITY = 50;

    private int count;
    private T[] tree;

    public ArrayHeap() {
        count = 0;
        tree = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    private void expandCapacity() {
        tree = Arrays.copyOf(tree, tree.length * 2);
    }

    //在恰当的位置处添加新结点 O(1)
    //对堆进行排序以维持其排序属性 O(log n)
    //对count递增1
    //O(log n)
    public void addElement(T ele) {
        if (count == tree.length) {
            expandCapacity();
        }

        tree[count] = ele;
        count++;

        if (count > 1) {
            heapifyAdd();
        }
    }

    private void heapifyAdd() {
        T temp;
        int next = count - 1;

        temp = tree[next];

        while (next != 0 && ((Comparable) temp).compareTo(tree[(next - 1) / 2]) < 0) {
            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }

        tree[next] = temp;
    }

    //用存储在最末元素处的元素替换存储在根处的元素
    //如果有必要对堆进行重排序
    //返回初始的根元素
    //O(log n)
    public T removeMin() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        T minElement = tree[0];
        tree[0] = tree[count - 1];
        heapifyRemove();
        count--;
        return minElement;
    }

    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if (tree[left] == null && tree[right] == null) {
            next = count;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }

        temp = tree[node];

        while (next < count && ((Comparable) tree[next]).compareTo(temp) < 0) {
            tree[node] = tree[next];
            node = next;
            left = 2 * node + 1;
            right = 2 * (node + 1);

            if (tree[left] == null && tree[right] == null) {
                next = count;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable) tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            } else {
                next = right;
            }

            tree[node] = temp;
        }
    }
}
