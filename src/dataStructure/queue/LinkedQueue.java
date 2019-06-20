package dataStructure.queue;


public class LinkedQueue<T> {

    private int count;
    private LinearNode<T> head,tail;

    public LinkedQueue(){
        count = 0;
        head = tail = null;
    }

    public class LinearNode<T> {

        private T element;

        private LinearNode<T> next;

        public LinearNode(){
            next = null;
            element = null;
        }

        public LinearNode(T ele){
            next = null;
            element = ele;
        }

    }

    public boolean isEmpty() {
        return count == 0 ? true : false;
    }

    //O(1)
    public void enqueue(T element) {
        LinearNode<T> node = new LinearNode<>(element);

        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        count++;
    }

    //O(1)
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        T element = head.element;
        head = head.next;
        count--;

        if (isEmpty()) {
            tail = null;
        }

        return element;
    }
}
