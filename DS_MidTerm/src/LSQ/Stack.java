package LSQ;
public interface Stack<E> {
    public void clear();
    public void push(E it);
    public E pop();
    public E topValue();
    public int length();
}

class ArrayStack<E> implements Stack<E> {
    private int maxSize; // Max Size of Stack
    private int top; // Index for Top
    private E[] listArray;

    @Override
    public void clear() { top = -1; }

    @Override
    public void push(E it) {
        if (top + 1 < maxSize) { listArray[++top] = it; }
        else { throw new RuntimeException("Stack Overflow"); }
    }

    @Override
    public E pop() {
        if (top == -1) { throw new RuntimeException("Empty Stack"); }
        else { return listArray[top--]; }
    }

    @Override
    public E topValue() {
        if (top == -1) { throw new RuntimeException("Empty Stack"); }
        else { return listArray[top]; }
    }

    @Override
    public int length() { return top + 1; }
}

class LinkedStack<E> implements Stack<E> {
    // Node class (inner class)
    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> top; // Top of stack
    private int size;    // Number of elements

    public LinkedStack() {
        top = null;
        size = 0;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E it) {
        top = new Node<>(it, top); // 새 노드가 top이 됨
        size++;
    }

    @Override
    public E pop() {
        if (top == null) throw new RuntimeException("Empty Stack");
        E it = top.data;
        top = top.next;
        size--;
        return it;
    }

    @Override
    public E topValue() {
        if (top == null) throw new RuntimeException("Empty Stack");
        return top.data;
    }

    @Override
    public int length() { return size; }
}
