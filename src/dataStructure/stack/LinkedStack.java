package dataStructure.stack;

public class LinkedStack<T> {

    private int count;
    private LinearNode<T> top;

    public LinkedStack(){
        count = 0;
        top = null;
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
        return top == null ? true : false;
    }

    //O(1)
    public void push(T element) {
        LinearNode<T> temp = new LinearNode<>(element);

        temp.next = top;
        top = temp;
        count++;
    }

    //O(1)
    public T pop(){
        if (isEmpty()) {
            throw new RuntimeException();
        }

        T element = top.element;
        top = top.next;
        count --;
        return element;
    }

    public T peek(){
        if (isEmpty()) {
            throw new RuntimeException();
        }

        return top.element;
    }
}
