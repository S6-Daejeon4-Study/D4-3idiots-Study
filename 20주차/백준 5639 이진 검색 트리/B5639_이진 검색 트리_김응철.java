import java.io.*;

public class Main_이진검색트리_5639 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Tree t = new Tree();
        while (true) {
            String input = br.readLine();
            if (input == null || input.equals(""))
                break;
            t.makeNode(Integer.parseInt(input));
        }
        t.postorder(t.root);
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static class Tree {
        public Node root;

        public void makeNode(int data) {
            if (root == null) {
                root = new Node(data);
            } else {
                Node head = root;
                Node currentNode;
                while (true) {
                    currentNode = head;
                    if (head.data > data) {
                        head = head.left;
                        if (head == null) {
                            currentNode.left = new Node(data);
                            break;
                        }
                    } else {
                        head = head.right;
                        if (head == null) {
                            currentNode.right = new Node(data);
                            break;
                        }
                    }
                }
            }
        }

        public void postorder(Node node) {
            if (node != null) {
                postorder(node.left);
                postorder(node.right);
                System.out.println(node.data);
            }
        }
    }
}
