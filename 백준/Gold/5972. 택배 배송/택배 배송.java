
import java.io.*;
import java.util.*;

class Node {
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] minDist; // 1-> i까지의 최소비용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 양방향
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        dijkstra(1);

        System.out.println(minDist[N]);
    }

    static void dijkstra(int start) {
        minDist = new int[N + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        // 가중치 오름차순
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> x.weight - y.weight);
        
        pq.offer(new Node(start, 0)); // 출발점->출발점 거리: 0
        minDist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curVertex = cur.vertex;
            int curweight = cur.weight;

            // 이미 처리된 거리보다 현재 경로 비용이 더 크면 continue
            if (curweight > minDist[curVertex]) continue;

            //인접 정점 탐색
            for (Node next : graph.get(curVertex)) {

                // vertex 에서 갈 수 있는 노드 중에서
				// minDist[to] > minDist[cur] + weight[cur->next] 이면 갱신
                if (minDist[next.vertex] > minDist[curVertex] + next.weight) {
                    minDist[next.vertex] = minDist[curVertex] + next.weight;
                    pq.offer(new Node(next.vertex, minDist[next.vertex]));
                }
            }
        }
    }
}