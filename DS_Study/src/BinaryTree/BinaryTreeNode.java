package BinaryTree;
// ADT for binary tree nodes
public interface BinaryTreeNode<E> {
    // Return and set the element value
    public E element();
    public E setElement(E v);

    // Return the left child and the right child
    public BinaryTreeNode<E> left();
    public BinaryTreeNode<E> right();

    // Return whether the node is leaf or not
    public boolean isLeaf();

    default void preOrderTraversal(BinaryTreeNode root) {
        if (root == null) return;
        visit(root);
        preOrderTraversal(root.left());
        preOrderTraversal(root.right());
    };

    default void preOrderTraversal2(BinaryTreeNode root) {
        visit(root);
        if(root.left() != null) preOrderTraversal2(root.left());
        if(root.right() != null) preOrderTraversal2(root.right());
    }
}

// Binary Search Tree
class BSTNode<K, E> implements BinaryTreeNode<E> {
    private K key;
    private E element;
    private BSTNode<K, E> left;
    private BSTNode<K, E> right;
    // Constructor
    public BSTNode() {left = right = null;}
    public BSTNode(K k, E val) {
        key = k;
        element = val;
        left = right = null;
    }
    public BSTNode(K k, E val, BSTNode<K, E> l, BSTNode<K, E> r) {
        left = l;
        right = r;
        key = k;
        element = val;
    }
    // functions
    public K key() { return key; }
    public E element() { return element; }
    public K setKey(K k) { return key = k; }
    public E setElement(E v) { return element = v; }
    public BSTNode<K, E> left() { return left; }
    public BSTNode<K, E> right() { return right; }
    public BSTNode<K, E> setLeft(BSTNode<K, E> l) { return left = l; }
    public BSTNode<K, E> setRight(BSTNode<K, E> r) { return right = r; }
    public boolean isLeaf() { return left == null && right == null; }
}