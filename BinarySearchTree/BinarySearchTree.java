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

    @Override
    protected int getMax(Node root){
        if (root.right != null)
            return getMax(root.right);
        else
            return root.data;
    }

    protected int getMin(Node root){
        if (root.left != null)
            return getMin(root.left);
        else
            return root.data;
    }

    public Node add(Node root, int val){
        if (root == null)
            return new Node(val);

        if (val > root.data){
            root.right = add(root.right, val);
        } else if(val < root.data){
            root.left = add(root.left, val);
        } else {
            // Nothing to do;
        }

        return root;
    }

    public Node remove(Node root, int val){
        if (root == null)
            return null;

        if (val > root.data){
            root.right = remove(root.right, val);
        } else if(val < root.data){
            root.left = remove(root.left, val);
        } else {
            // Nothing to do;
            if (root.left != null && root.right != null){
                int lmax = getMax(root.left);
                root.data = lmax;
                root.left = remove(root.left, lmax);
                return  root;
            } else if (root.left != null){
                return root.left;
            } else if (root.right != null){
                return root.right;
            } else {
                return null;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = buildBinarySearchTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8 , 9, 10, 11, 12, 13}, 0, 12);
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(root, 0);
        bst.remove(root, 10);
        inorderTraversal(root);
    }
}

