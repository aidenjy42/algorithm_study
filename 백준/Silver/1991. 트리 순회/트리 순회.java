import java.io.*;
import java.util.*;

public class Main {
	static class Node {
	    char value;
	    Node left;
	    Node right;

	    public Node(char value) {
	        this.value = value;
	        this.left = null;
	        this.right = null;
	    }
	}

    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 알파벳 A~Z 개수만큼 노드 배열 생성 (A=0, B=1, ...)
        tree = new Node[26];

        // 1. 트리 생성 (입력 처리)
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parentValue = st.nextToken().charAt(0);
            char leftValue = st.nextToken().charAt(0);
            char rightValue = st.nextToken().charAt(0);

            // 부모 노드가 생성되어 있지 않다면 생성
            if (tree[parentValue - 'A'] == null) {
                tree[parentValue - 'A'] = new Node(parentValue);
            }

            // 왼쪽 자식이 있다면 연결
            if (leftValue != '.') {
                if (tree[leftValue - 'A'] == null) {
                    tree[leftValue - 'A'] = new Node(leftValue);
                }
                tree[parentValue - 'A'].left = tree[leftValue - 'A'];
            }

            // 오른쪽 자식이 있다면 연결
            if (rightValue != '.') {
                if (tree[rightValue - 'A'] == null) {
                    tree[rightValue - 'A'] = new Node(rightValue);
                }
                tree[parentValue - 'A'].right = tree[rightValue - 'A'];
            }
        }

        // 2. 순회 결과 출력 (루트 노드인 'A' 즉, tree[0]부터 시작)
        preorder(tree[0]);
        System.out.println();

        inorder(tree[0]);
        System.out.println();

        postorder(tree[0]);
        System.out.println();
    }

    // 전위 순회 (Preorder): 루트 -> 왼쪽 -> 오른쪽
    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회 (Inorder): 왼쪽 -> 루트 -> 오른쪽
    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    // 후위 순회 (Postorder): 왼쪽 -> 오른쪽 -> 루트
    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }
}