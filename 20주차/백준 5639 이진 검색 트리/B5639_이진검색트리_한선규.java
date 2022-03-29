import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B5639_이진검색트리_한선규 {

    private static int[] preorderArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        ArrayList<Integer> preorderList = new ArrayList<>();
        preorderList.add(Integer.parseInt(input));

        while (true) {
            input = br.readLine();
            if (input == null || input.equals(""))
                break;
            preorderList.add(Integer.parseInt(input));
        }

        preorderArr = preorderList.stream().mapToInt(i -> i).toArray();

        preToPostorder(0, preorderArr.length - 1);

        System.out.println(sb);
    }

    static void preToPostorder(int begin, int end) {
        if (begin > end)
            return;

        int root = preorderArr[begin];
        int idx = -1;

        for (int i = begin + 1; i <= end; i++) {
            if (preorderArr[i] > root) { // 오른쪽 서브트리의 root를 발견하게 된다면
                idx = i;
                break;
            }
        }

        if (idx == -1) // 오른쪽 서브트리의 root를 발견하지 못했다면
            idx = end + 1; // 종료조건에서 begin > end 로 더이상 탐색하지 않게 하기 위해서 + 1
        preToPostorder(begin + 1, idx - 1); // 현재 root를 기준으로 왼쪽 서브트리에 대해서 탐색
        preToPostorder(idx, end); // 현재 root를 기준으로 오른쪽 서브트리에 대해서 탐색
        sb.append(root).append("\n");
    }

//    private static Node[] tree = new Node[1000001];
//    private static StringBuilder sb = new StringBuilder();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//        int root = Integer.parseInt(input);
//        tree[root] = new Node(root); // 전위 순회의 첫번째 값은 트리의 root 임.
//
//        while ((input = br.readLine()) != null) {
//            int n = Integer.parseInt(input);
//            makeTree(root, n);
//        }
//
//        // 후위 순회한 결과를 StringBuilder 에 저장
//        postorder(root);
//
//        System.out.println(sb);
//    }
//
//    static class Node {
//        int root; // 트리의 root 노드
//        Node left; // 왼쪽 서브트리의 root 노드
//        Node right; // 오른쪽 서브트리의 root 노드
//
//        public Node(int root) {
//            this.root = root;
//        }
//    }
//
//    private static void makeTree(int root, int n) {
//        int now = root;
//        while (true) {
//            if (n < tree[now].root) { // 왼쪽 서브트리 탐색
//                if (tree[now].left != null)
//                    now = tree[now].left.root;
//                else {
//                    tree[now].left = new Node(n);
//                    break;
//                }
//            } else { // 오른쪽 서브트리 탐색
//                if (tree[now].right != null)
//                    now = tree[now].right.root;
//                else {
//                    tree[now].right = new Node(n);
//                    break;
//                }
//            }
//        }
//    }
//
//    private static void postorder(int root) {
//        if (tree[root] == null)
//            return;
//        if (tree[root].left != null)
//            postorder(tree[root].left.root);
//        if (tree[root].right != null)
//            postorder(tree[root].right.root);
//        sb.append(root).append("\n");
//    }
}
