public interface Queue<E> {
    public void clear();
    public void enqueue(E it);
    public E dequeue();
    public E frontValue();
    public int length();
}

class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_SIZE = 100;
    private int maxSize;
    private int front;
    private int rear;
    private int size;
    private E[] listArray;
    public ArrayQueue(int capacity) {
        maxSize = capacity;
        front = 0;
        rear = 0;
        size = 0;
        listArray = (E[]) new Object[maxSize];
    }
    public ArrayQueue() { this(DEFAULT_SIZE); }
    public void clear() { front = rear = size = 0; }
    public void enqueue(E it) {
        if (size == maxSize) throw new RuntimeException("Queue Overflow");
        listArray[rear] = it;
        rear = (rear + 1) % maxSize;
        size++;
    }
    public E dequeue() {
        if (size == 0) throw new RuntimeException("Queue Underflow");
        E it = listArray[front];
        front = (front + 1) % maxSize;
        size--;
        return it;
    }
    public E frontValue() {
        if (size == 0) throw new RuntimeException("Queue is empty");
        return listArray[front];
    }
    public int length() { return size; }
}

class LinkedQueue<E> implements Queue<E> {
    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node<E> front;
    private Node<E> rear;
    private int size;
    public LinkedQueue() {
        front = rear = null;
        size = 0;
    }
    public void clear() {
        front = rear = null;
        size = 0;
    }
    public void enqueue(E it) {
        Node<E> newNode = new Node<>(it, null);
        if (rear != null) { rear.next = newNode; }
        else { front = newNode; }
        rear = newNode;
        size++;
    }
    public E dequeue() {
        if (front == null) throw new RuntimeException("Queue Underflow");
        E it = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return it;
    }
    public E frontValue() {
        if (front == null) throw new RuntimeException("Queue is empty");
        return front.data;
    }
    public int length() { return size; }
}

class StackQueue<E> implements Queue<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;
    public StackQueue() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }
    public void clear() {
        stack1.clear();
        stack2.clear();
    }
    public void enqueue(E it) { stack1.push(it); }
    public E dequeue() {
        if (stack2.length() == 0) { while (stack1.length() > 0) { stack2.push(stack1.pop()); } }
        if (stack2.length() == 0) throw new RuntimeException("Queue Underflow");
        return stack2.pop();
    }
    public E frontValue() {
        if (stack2.length() == 0) { while (stack1.length() > 0) { stack2.push(stack1.pop()); } }
        if (stack2.length() == 0) throw new RuntimeException("Queue is empty");
        return stack2.topValue();
    }
    public int length() { return stack1.length() + stack2.length(); }
}
