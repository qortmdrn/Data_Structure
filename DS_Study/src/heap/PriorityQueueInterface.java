package heap;

public interface PriorityQueueInterface<E> {
  public void insert(E newItem) throws Exception;
  public E deleteMax() throws Exception;
  public E max() throws Exception;
  public boolean isEmpty();
  public void clear();
}
