import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] resultCount; // 각 노드별 해킹 가능한 컴퓨터 수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // A가 B를 신뢰한다 = B를 해킹하면 A도 해킹됨
            // 방향: B -> A
            graph.get(B).add(A);
        }

        resultCount = new int[N + 1];
        int maxVal = 0;

        // 모든 노드에 대해 각각 BFS 수행 (1번 ~ N번)
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1]; // 매번 방문 배열 초기화
            bfs(i);
        }

        // 최댓값(maxVal) 찾기
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, resultCount[i]);
        }

        // 최댓값과 같은 해킹 수를 가진 노드들을 오름차순으로 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (resultCount[i] == maxVal) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        int count = 0; // 현재 시작점에서 해킹 가능한 컴퓨터 수

        while (!q.isEmpty()) {
            int current = q.poll();
            count++; // 큐에서 뽑을 때마다 카운트 증가 (자기 자신 포함)

            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        resultCount[start] = count; // 결과 저장
    }
}