package Tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree {
    static int  idx = -1;
    public static Node buildBinaryTree(int[] nodes){
        idx++;
        if(nodes[idx] == -1) {
            return null;
        }

        Node newNode = new Node(nodes[idx]);
        newNode.left = buildBinaryTree(nodes);
        newNode.right = buildBinaryTree(nodes);
        return newNode;
    }

    public static void inorderTraversal(Node root){
        if(root == null){
            return;
        }

        inorderTraversal(root.left);
        System.out.print(root.data + " ");
        inorderTraversal(root.right);
    }

    public static void preOrderTraversal(Node root){
        if(root == null){
            return;
        }

        System.out.print(root.data + " ");
        inorderTraversal(root.left);
        inorderTraversal(root.right);
    }

    public static void postOrderTraversal(Node root){
        if(root == null){
            return;
        }

        inorderTraversal(root.left);
        inorderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrderTraversal(Node root){
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(queue.size() > 0){
            int count = queue.size();
            for (;count > 0; count--){
                Node node = queue.poll();
                System.out.print(node.data + " ");
                if(node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }

        }
    }

    public static void rightViewOfBinaryTree(Node root){
        if(root == null){
            return;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(queue.size() > 0) {
            int count = queue.size();
            System.out.print(queue.peek().data + " ");
            for (; count > 0; count--) {
                Node node = queue.poll();
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
    }

    public static void leftViewOfBinaryTree(Node root){
        if(root == null){
            return;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while(q.size() > 0){
            int count = q.size();
            System.out.print(q.peek().data + " ");
            for(int i = 0; i < count; i++){
                Node tn = q.poll();

                if(tn.left != null){
                    q.offer(tn.left);
                }

                if(tn.right != null){
                    q.offer(tn.right);
                }
            }
        }
    }
}

class Main{
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        Node root = BinaryTree.buildBinaryTree(arr);
        System.out.println(root.data);
        BinaryTree.inorderTraversal(root);
        System.out.println();
        BinaryTree.preOrderTraversal(root);
        System.out.println();
        BinaryTree.postOrderTraversal(root);
        System.out.println();
        BinaryTree.levelOrderTraversal(root);
        System.out.println();
        BinaryTree.rightViewOfBinaryTree(root);
        System.out.println();
        BinaryTree.leftViewOfBinaryTree(root);
    }
}
