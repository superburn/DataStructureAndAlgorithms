package dataStructure.stack;


import java.util.Arrays;

//栈底总是在数组的索引0处
//有一个整数变量top,表示下一个单元(即将压入元素就存储在这里),
//也表示了当前栈中元素的数量
public class ArrayStack<T> {

    private final int DEFAULT_CAPACITY = 100;
    private int top;
    private T[] stack;

    public ArrayStack(){
        top = 0;
        stack = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int initialCapacity){
        top = 0;
        stack = (T[]) new Object[initialCapacity];
    }

    public int size(){
        return top;
    }

    public boolean isEmpty() {
        return top == 0 ? true : false;
    }

    //复杂度O(1),expandCapacity方法的复杂度被分摊
    public void push(T element){
        if(size() == stack.length){
            expandCapacity();
        }
        stack[top] = element;
        top ++;
    }

    private void expandCapacity(){
        stack = Arrays.copyOf(stack,stack.length * 2);
    }

    //O(1)
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        top--;
        T element = stack[top];
        stack[top] = null;

        return element;
    }

    //O(1)
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return stack[top - 1];
    }

}
