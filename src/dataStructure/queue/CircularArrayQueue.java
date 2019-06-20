package dataStructure.queue;

//把数组看作是环形的,可以除去在对队列的数组实现中把元素移位的需要
//front表示的是队列的首元素存储的位置
//rear的值表示的是数组的下一个可用单元
//count跟踪元素计数
public class CircularArrayQueue<T>{
    private final int DEFAULT_CAPACITY = 100;
    private int front,rear,count;
    private T[] queue;

    public CircularArrayQueue(){
        front = rear = count = 0;
        queue = (T[])new Object[DEFAULT_CAPACITY];
    }

    public CircularArrayQueue(int initialCapacity){
        front = rear = count = 0;
        queue = (T[])new Object[initialCapacity];
    }

    public int size(){
        return count;
    }

    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    public void enqueue(T element) {
        if (size() == queue.length) {
            expandCapacity();
        }
        queue[rear] = element;
        rear = (rear + 1) % queue.length;
    }

    private void expandCapacity() {
        T[] larger = (T[]) new Object[queue.length * 2];
        for (int i = 0; i < queue.length; i++) {
            larger[i] = queue[front];
            front = (front + 1) / queue.length;
        }
        front = 0;
        rear = count;
        queue = larger;
    }

    //进行足够的dequeue操作后,front的值将达到数组的最后一个索引处.
    //当最大索引处的元素被删除后,front的值必须设置为0
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        T element = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        count --;

        return element;
    }
}
