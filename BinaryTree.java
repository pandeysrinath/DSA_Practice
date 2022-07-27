package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

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
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void postOrderTraversal(Node root){
        if(root == null){
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrderTraversal(Node root){
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(queue.size() > 0){
            int count = queue.size();
            for (;count > 0; count--){
                Node node = queue.poll();
                assert node != null;
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
                assert node != null;
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

    public static int getSize(Node root){
        // Size of BT: Total number of nodes in Binary Tree
        if(root == null)
            return 0;

        return 1 + getSize(root.left) + getSize(root.right);
    }

    public static int getSum(Node root){
        // getSum returns the addition of all nodes in Binary Tree.
        if (root == null){
            return 0;
        }

        return root.data + getSum(root.left) + getSum(root.right);
    }

    public static int getHeight(Node root){
        // getHeight returns the number of edges present between root node and deepest leaf node.
        if (root == null){
            return -1;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    private static class Pair{
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void preInPostOrderTraversalIterative(Node root, ArrayList<Integer> preLst, ArrayList<Integer> inLst, ArrayList<Integer> postLst){
        if (root == null)
            return;

        Stack<Pair> st = new Stack<>();
        Pair rtp = new Pair(root, 1);
        st.push(rtp);

        while (st.size() > 0){
            Pair top = st.peek();
            if(top.state == 1){
                preLst.add(top.node.data);
                top.state++;

                if (top.node.left != null){
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                }

            } else if(top.state == 2){
                inLst.add(top.node.data);
                top.state++;

                if (top.node.right != null){
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                }
            } else {
                postLst.add(top.node.data);
                st.pop();
            }
        }

    }


    public static ArrayList<Integer> boundary(Node node){
        ArrayList<Integer> ans = new ArrayList<>();

        if(isLeaf(node)){
            ans.add(node.data);
            return ans;
        }

        ans.add(node.data);
        leftBoundary(node.left, ans);
        leafBoundary(node, ans);
        rightBoundary(node.right, ans);
        return ans;
    }

    private static void leftBoundary(Node node, ArrayList<Integer> ans){
        while(node != null) {
            if(!isLeaf(node))
                ans.add(node.data);
            if(node.left == null)
                node = node.right;
            else
                node = node.left;
        }

    }

    private static void leafBoundary(Node node, ArrayList<Integer> ans){
        if(node == null)
            return;
        if(isLeaf(node))
            ans.add(node.data);

        leafBoundary(node.left, ans);
        leafBoundary(node.right, ans);
    }

    private static void rightBoundary(Node node, ArrayList<Integer> ans){

        Stack<Integer> st = new Stack<>();

        while(node != null) {
            if(!isLeaf(node))
                st.push(node.data);
            if(node.right == null)
                node = node.left;
            else
                node = node.right;
        }

        while(st.size() > 0){
            ans.add(st.pop());
        }
    }

   private static boolean isLeaf(Node node){
        if(node != null && node.left == null && node.right == null)
            return true;
        else
            return false;
    }

    static class DiaPair{
        int ht;
        int dia;
    }

    public static int diameter(Node root){
        return diameter1(root).dia;
    }

    private static DiaPair diameter1(Node root){
        if(root == null){
            DiaPair bp = new DiaPair();
            bp.ht = -1;
            bp.dia = 0;
            return bp;
        }

        DiaPair lp = diameter1(root.left);
        DiaPair rp = diameter1(root.right);

        DiaPair np = new DiaPair();
        np.ht = Math.max(lp.ht, rp.ht) + 1;

        int fes = lp.ht + rp.ht + 2;
        np.dia = Math.max(fes, Math.max(lp.dia, rp.dia));

        return np;
    }

    public static ArrayList<Node> path;

    public static boolean find(Node node, int data){
        if (node == null)
            return false;

        if (node.data == data){
            path.add(node);
            return true;
        }

        boolean filc = find(node.left, data);
        if (filc){
            path.add(node);
            return true;
        }

        boolean firc = find(node.right, data);
        if (firc){
            path.add(node);
            return true;
        }

        return false;
    }

    public static void printKthLevel(Node root, int k){
        if (root == null || k < 0)
            return;

        if (k == 0){
            System.out.println(root.data);
        }

        printKthLevel(root.left, k - 1);
        printKthLevel(root.right, k - 1);
    }
}

class Main{
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        Node root = BinaryTree.buildBinaryTree(arr);
        BinaryTree.preOrderTraversal(root);
        System.out.println();
        BinaryTree.inorderTraversal(root);
        System.out.println();
        BinaryTree.postOrderTraversal(root);
        System.out.println();
        BinaryTree.levelOrderTraversal(root);
        System.out.println();
        BinaryTree.rightViewOfBinaryTree(root);
        System.out.println();
        BinaryTree.leftViewOfBinaryTree(root);
        System.out.println();
        System.out.println("Size of Binary Tree: " + BinaryTree.getSize(root));
        System.out.println("Sum of all the nodes of Binary Tree: " + BinaryTree.getSum(root));
        System.out.println("Height of Binary Tree: " + BinaryTree.getHeight(root));

        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        BinaryTree.preInPostOrderTraversalIterative(root, pre, in, post);
        System.out.println(pre + " " + in + " " + post);
        System.out.println(BinaryTree.diameter(root));

        BinaryTree.path = new ArrayList<>();
        BinaryTree.find(root, 6);
        for (Node i : BinaryTree.path){
            int val = i.data;
            System.out.print(val + " -> ");
        }
        BinaryTree.printKthLevel(root, 2);
    }
}
