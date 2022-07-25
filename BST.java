package Tree;

public class BST {
    int c = 0;
    static class Node{
        int data, lc;
        Node left, right;
        public Node(int data) {
            this.data = data;
        }
    }
    Node insert(Node root, int val){
        if(root == null) return new Node(val);
        if(root.data < val){
            c += root.lc+1;
            root.right = insert(root.right, val);
        }else{
            root.lc++;
            root.left = insert(root.left, val);
        }
        return root;
    }

}
