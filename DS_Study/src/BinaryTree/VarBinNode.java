package BinaryTree;
// Base class
public interface VarBinNode {
    public boolean isLeaf();
}

// Leaf Node (값)
class VarLeafNode implements VarBinNode {
    private String operand; // 값 only
    public VarLeafNode(String val) { operand = val; }
    public boolean isLeaf() { return true; }
    public String value() { return operand; }
}

// Internal Node (식, 연산자, 식)
class VarIntlNode implements VarBinNode {
    private VarBinNode left; // 왼쪽 식
    private VarBinNode right; // 오른쪽 식
    private Character operator; // 연산자

    public VarIntlNode(Character op, VarBinNode l, VarBinNode r) {
        operator = op;
        left = l;
        right = r;
    }
    public boolean isLeaf() { return false; }
    public VarBinNode leftChild() { return left; }
    public VarBinNode rightChild() { return right; }
    public Character value() { return operator; }
}

/* Preorder traversal */
//public static void traverse(VarBinNode rt) {
//    if (rt == null) return;
//    if (rt.isLeaf()) { VisitLeafNode(((VarLeafNode) rt).value()); }
//    else {
//        VisitInternalNode(((VarIntlNode) rt).value());
//        traverse(((VarIntlNode) rt).leftChild());
//        traverse(((VarIntlNode) rt).rightChild());
//    }
//}