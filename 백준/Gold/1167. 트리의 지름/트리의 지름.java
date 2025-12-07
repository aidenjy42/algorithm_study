
import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static int N;
    static boolean[] visited;
    static List<ArrayList<Node>> tree = new ArrayList<>();
    static int maxDist = 0; // 가장 긴 지름 길이
    static int maxNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 출발 노드 번호

            while (true) {
                int end = Integer.parseInt(st.nextToken());

                // -1이 나오면 해당 줄 입력 종료
                if (end == -1) break;

                int weight = Integer.parseInt(st.nextToken());

                // 입력 정보에 따라 간선 추가 (문제에서 양방향 의미로 주어짐)
                tree.get(start).add(new Node(end, weight));
            }
        }

        // 임의의 노드에서 dfs 돌려서 그 노드와 가장 거리가 먼 노드 찾기
        visited = new boolean[N + 1];
        dfs(1, 0);

        //가장 거리가 먼 노드(maxNode)에서 다시 가장 긴 지름 구하기
        visited = new boolean[N + 1];
        maxDist = 0;
        dfs(maxNode, 0);

        System.out.println(maxDist);

    }
    static void dfs(int vertex, int dist) {

        // 기저조건. 이미 방문한 노드면 return
        if(visited[vertex])
            return;
        visited[vertex] = true;

        // 현재까지 온 거리가 최대면 갱신
        if (maxDist < dist) {
            maxDist = dist; // 가장 긴 지름 길이
            maxNode = vertex; // 가장 거리가 먼 노드 갱신
        }

        // 다음 인접 노드
        for (Node next : tree.get(vertex)) {
            if (!visited[next.vertex]) {
                dfs(next.vertex, dist + next.weight);
            }
        }
    }
}