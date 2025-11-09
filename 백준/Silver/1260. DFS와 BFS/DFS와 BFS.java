import java.io.*;
import java.util.*;

public class Main {
//방문할 수 있는 정점이 여러 개인 경우 - 정점 번호가 작은 것을 먼저 방문

    static int N, M, V;
    static List<List<Integer>> adjList = new ArrayList<>();
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }
        // 인접 정점 번호 오름차순 정렬
        for(int i=1; i<=N; i++){
            Collections.sort(adjList.get(i));
        }

        visited = new boolean[N+1];

        dfs(V);
        sb.append("\n");
        bfs();
        System.out.println(sb);
    }
    
    static void dfs(int vertex){
        // 기저 조건
        if(visited[vertex]) return;

        visited[vertex] = true;
        sb.append(vertex+" ");
        
        for(int next: adjList.get(vertex)){
            if(visited[next]) continue;
            dfs(next);
        }

    }
    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        visited = new boolean[N+1];

        q.offer(V);
        visited[V] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            sb.append(cur+" ");
            
            for(int next: adjList.get(cur)){
                if(visited[next]) continue;

                q.offer(next);
                visited[next] = true;
            }
        }
    }
}
