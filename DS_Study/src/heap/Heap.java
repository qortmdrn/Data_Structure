package heap;

import java.util.PriorityQueue;

public class Heap<E extends Comparable> implements PriorityQueueInterface<E> {
  private E[] A;
  private int numItems;

  public Heap(int arraySize) {
    A = (E[]) new Comparable[arraySize];
    numItems = 0;
  }
  public Heap(E[] B, int numElements) {
    A = B; // copy the reference of the array
    numItems = numElements;
  }
  
  public void insert(E newItem) throws PriorityQueueException {
    if (numItems < A.length) {
      A[numItems] = newItem;
      percolateUp(numItems);
      numItems++;
    } else throw new PriorityQueueException("HeapErr: Insert() - Overflow");
  } 

  public void percolateUp(int i) {
    int parent = (i - 1) / 2;
    if (parent >= 0 && A[i].compareTo(A[parent]) > 0) {
      E temp = A[i];
      A[i] = A[parent];
      A[parent] = temp;
      percolateUp(parent);
    }
  }

  public E deleteMax() throws PriorityQueueException {
    if (!isEmpty()) {
      E max = A[0];
      A[0] = A[numItems - 1];
      numItems--;
      percolateDown(0);
      return max;
    } else throw new PriorityQueueException("HeapErr: DeleteMax() - Underflow");
  }

  public void percolateDown(int i) {
    int child = 2 * i + 1; // left child
    int rightChild = 2 * i + 2; // right child
    if (child <= numItems - 1) {
      if (rightChild <= numItems - 1 && A[child].compareTo(A[rightChild]) < 0) {
        child = rightChild;
      }
      if (A[i].compareTo(A[child]) < 0) {
        E temp = A[i];
        A[i] = A[child];
        A[child] = temp;
        percolateDown(child);
      }
    }
  }

  public void buildHeap() {
    if (numItems >= 2) {
      for (int i = (numItems - 2) / 2; i >= 0; i--) {
        percolateDown(i);
      }
    }
  }

  public E max() throws PriorityQueueException {
    if (!isEmpty()) {
      return A[0];
    } else throw new PriorityQueueException("HeapErr: Max() - Empty");
  }

  public boolean isEmpty() {
    return numItems == 0;
  }

  public void clear() {
    A = (E[]) new Object[A.length];
    numItems = 0;
  }
}
