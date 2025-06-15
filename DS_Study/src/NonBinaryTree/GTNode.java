package NonBinaryTree;

interface GTNode<E> {
    public E value();
    public boolean isLeaf();
    public GTNode<E> parent();
    public GTNode<E> leftmostchild();
    public GTNode<E> rightsibling();
    public void setValue(E value);
    public void setParent(GTNode<E> par);
    public void insertFirst(GTNode<E> n);
    public void insertNext(GTNode<E> n);
    public void removeFirst();
    public void removeNext();
}
