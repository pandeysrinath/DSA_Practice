package Tree.BinarySearchTree;

import Tree.BinaryTree.BinaryTree;
import Tree.BinaryTree.Node;

public class BinarySearchTree extends BinaryTree{
    public static Node buildBinarySearchTree(int[] nodes, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int mid = lo + (hi - lo)/2;
        int data  = nodes[mid];

        Node left = buildBinarySearchTree(nodes, lo, mid - 1);
        Node right = buildBinarySearchTree(nodes, mid + 1, hi);

        Node node = new Node(data, left, right);
        return node;
    }

    public static void main(String[] args) {
        Node root = buildBinarySearchTree(new int[]{1, 2, 3, 4, 5}, 0, 4);
        preOrderTraversal(root);
    }
}

