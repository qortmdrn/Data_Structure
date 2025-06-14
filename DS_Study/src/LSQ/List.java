package LSQ;
public interface List<E> {
    public void clear();
    public void insert(E item);
    public void append(E item);
    public E remove();
    public void moveToStart();
    public void moveToEnd();
    public void prev();
    public void next();
    public int length();
    public int currPos();
    public void moveToPos(int pos);
    public E getValue();
}

// Array-Based List Class
class ArrayList<E> implements List<E> {
    private static final int defaultSize = 10;
    private int maxSize;
    private int listSize;
    private int curr;
    private E[] listArray;

    // Constructors
    ArrayList() { this(defaultSize); };
    ArrayList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[]) new Object[size];
    }

    // Construct Basic Functions inherited by Parent Class
    @Override
    public void clear() { listSize = curr = 0; }

    @Override
    public void insert(E item) { // Insert item at current position
        assert listSize < maxSize : "List capacity exceeded";
        for (int i = listSize; i > curr; i--) {
            listArray[i] = listArray[i - 1];
        }
        listArray[curr] = item;
        listSize++;
    }

    @Override
    public void append(E item) { // Append item at last
        assert listSize < maxSize : "List capacity exceeded";
        listArray[listSize++] = item;
    }

    @Override
    public E remove() {
        if((curr < 0) || (curr >= listSize)) return null; // 잘못된 curr 위치
        E item = listArray[curr];
        for(int i = curr; i < listSize - 1; i++) {
            listArray[i] = listArray[i + 1]; // 타겟보다 오른쪽 원소들을 모두 한 칸씩 앞으로 당김.
        }
        listSize--;
        return item;
    }

    @Override
    public void moveToStart() { curr = 0; }

    @Override
    public void moveToEnd() { curr = listSize; }

    @Override
    public void prev() { if (curr != 0) curr--; }

    @Override
    public void next() { if (curr < listSize) curr++; }

    @Override
    public int length() { return listSize; }

    @Override
    public int currPos() { return curr; }

    @Override
    public void moveToPos(int pos) {
        assert (pos >= 0 && pos < listSize) : "Position out of range";
        curr = pos;
    }

    @Override
    public E getValue() {
        assert (curr >= 0) && (curr < listSize) : "No current element";
        return listArray[curr];
    }
}

// Concept of "Link"
class Link<E> {
    private E element;
    private Link<E> next;

    // Constructors
    Link(E it, Link<E> nextval) { element = it; next = nextval; }
    Link(Link<E> nextval) { next = nextval; }

    Link<E> next() { return next; }
    Link<E> setNext(Link<E> nextval) { return next = nextval; }
    E element() { return element; }
    E setElement(E it) { return element = it; }

    // Extensions
    static Link freelist = null;

    static <E> Link<E> get(E it, Link<E> nextval) {
        if (freelist == null) { return new Link<E>(it, nextval); }
        Link<E> temp = freelist;
        freelist = freelist.next();
        temp.setElement(it);
        temp.setNext(nextval);
        return temp;
    }

    void release() { // Return to Freelist
        element = null;
        next = freelist;
        freelist = this;
    }
}

// LinkedList Class
class LinkedList<E> implements List<E> {
    private Link<E> head;
    private Link<E> tail;
    protected Link<E> curr;
    int cnt;

    // Constructors
    LinkedList(int size) { this(); }
    LinkedList() {
        curr = head = tail = new Link<E>(null);
        cnt = 0;
    }

    // Construct Basic Functions inherited by Parent Class
    @Override
    public void clear() {
        curr = tail = head = new Link<E>(null);
        cnt = 0;
    }

    @Override
    public void insert(E item) {
        curr.setNext(new Link<E>(item, curr.next()));
        if (tail == curr) tail = curr.next();
        cnt++;
    }

    @Override
    public void append(E item) {
        tail = tail.setNext(new Link<E>(item, null));
        cnt++;
    }

    @Override
    public E remove() { // Remove and Return Current Element
        if (curr.next() == null) return null;
        E item = curr.next().element();
        if (tail == curr.next()) tail = curr;
        curr.setNext(curr.next().next());
        cnt--;
        return item;
    }

    @Override
    public void moveToStart() { curr = head; }

    @Override
    public void moveToEnd() { curr = tail; }

    @Override
    public void prev() { // Move curr one step left; no change if already at front;
        if (curr == head) return;
        Link<E> temp = head;
        while (temp.next() != curr) { temp = temp.next(); }
        curr = temp;
    }

    @Override
    public void next() { if (curr != tail) curr = curr.next(); }

    @Override
    public int length() { return cnt; } // O(1)

    @Override
    public int currPos() { // Return Position of the Current Element
        Link<E> temp = head;
        int i;
        for (i = 0; curr != temp; i++) { temp = temp.next(); }
        return i;
    }

    @Override
    public void moveToPos(int pos) { // Move Down List to "pos" Position
        assert (pos >= 0) && (pos <= cnt) : "Position out of range";
        curr = head;
        for (int i = 0; i < pos; i++) { curr = curr.next(); }
    }

    @Override
    public E getValue() {
        assert curr.next() != null : "Nothing to get"; // curr == tail
        return curr.next().element();
    }
}

// Using Freelist in LinkedList
//class LinkedListWithFreeList<E> implements List<E> {
//    private Link<E> head;
//    private Link<E> tail;
//    protected Link<E> curr;
//    int cnt;
//
//    public void insert (E it) {
//        curr.setNext(Link.get(it, curr.next()));
//        if (tail == curr) tail = curr.next();
//        cnt++;
//    }
//
//    public E remove() {
//        if (curr.next() == null) return null;
//        E item = curr.next().element();
//        if (tail == curr.next()) tail = curr;
//        Link<E> tempptr = curr.next();
//        curr.setNext(curr.next().next());
//        tempptr.release();
//        cnt--;
//        return item;
//    }
//}

// Doubly Linked Lists
//class DLink<E> {
//    private E element;
//    private DLink<E> next;
//    private DLink<E> prev;
//
//    DLink(E it, DLink<E> p, DLink<E> n) { element = it; next = n; prev = p; }
//    DLink(DLink<E> p, DLink<E> n) { prev = p; next = n; }
//
//    DLink<E> next() { return next; }
//    DLink<E> prev() { return prev; }
//    DLink<E> setNext(DLink<E> nextVal) { return next = nextVal; }
//    DLink<E> setPrev(DLink<E> prevVal) { return prev = prevVal; }
//
//    E element() { return element; }
//    E setElement(E it) { return element = it; }
//
//    public void insert(E it) {
//        curr.setNext(new DLink<E>(it, curr, curr.next()));
//        curr.next().next().setPrev(curr.next());
//        cnt++;
//    }
//
//    public E remove() {
//        if (curr.next() == tail) return null;
//        E it = curr.next().element();
//        curr.next().next().setPrev(curr);
//        curr.setNext(curr.next().next());
//        cnt--;
//        return it;
//    }
//}