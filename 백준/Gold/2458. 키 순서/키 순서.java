import java.io.*;
import java.util.*;

public class Main {

    static List<ArrayList<Integer>> graph;
    static List<ArrayList<Integer>> graph_reverse;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        graph = new ArrayList<>();
        graph_reverse = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Integer>());
            graph_reverse.add(new ArrayList<Integer>());
        }

        int start, end;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph_reverse.get(end).add(start);
        }

        // 카운트 계산
        int result = 0;
        for (int node = 1; node <= N; node++) {
            // 방문한 노드의 수를 정+역방향 합쳐서 
            // 그 결과가 N+1(출발노드는 2번 더해짐)개가 나오면 됨
            int cnt = bfs(node, graph);
            cnt += bfs(node, graph_reverse);
            if(cnt == (N+1)) 
                result++;
        }
        System.out.println(result);
    }

    static int bfs(int startNode, List<ArrayList<Integer>> adjList) {
        Queue<Integer> q = new ArrayDeque<>();
        visited = new boolean[N+1];

        q.offer(startNode);
        visited[startNode] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;

            for(int to: adjList.get(cur)){
                if(visited[to]) continue;

                q.offer(to);
                visited[to] = true;
            }
        }
        
        return cnt;
    }
}
