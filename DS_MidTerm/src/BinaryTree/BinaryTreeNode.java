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