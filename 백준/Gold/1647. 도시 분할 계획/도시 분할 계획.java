
import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int from;
    int to;
    int weight;

    public Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // 가중치 오름차순 정렬
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Main {

    static int[] root;
    static List<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        root = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }

        nodes = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes.add(new Node(a, b, c));
        }

        // 간선 정렬
        Collections.sort(nodes);

        int totalCost = 0; // 전체 비용
        int maxCost = 0;   // MST에 포함된 간선 중 가장 비싼 비용
        
        // 3. 간선을 하나씩 확인하며 연결 (Union-Find)
        for (Node v : nodes) {
            if (find(v.from) != find(v.to)) {
                union(v.from, v.to);
                
                totalCost += v.weight;
                // 오름차순 정렬-> 마지막에 들어오는 weight가 최대 비용
                maxCost = v.weight;
            }
        }

        // 4. 결과 출력: (전체 MST 비용) - (가장 비싼 간선 비용)
        // 가장 비싼 연결을 끊음으로써 두 개의 마을로 분할됨
        System.out.println(totalCost - maxCost);
    }

    static int find(int x) {
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            root[b] = a;
        }
    }
}