package BinaryTree;
// Base class
public interface VarBinNode {
    public boolean isLeaf();
}

// Leaf Node
class VarLeafNode implements VarBinNode {
    private String operand;
    public VarLeafNode(String val) { operand = val; }
    public boolean isLeaf() { return true; }
    public String value() { return operand; }
}

// Internal Node
class VarIntlNode implements VarBinNode {
    private VarBinNode left;
    private VarBinNode right;
    private Character operator;

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