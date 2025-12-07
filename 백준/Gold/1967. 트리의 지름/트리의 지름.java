import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    // 노드 정보를 담을 클래스 (목적지, 가중치)
    static class Node {
        int to;
        int weight;
        
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static ArrayList<Node>[] list; // 인접 리스트
    static boolean[] visited;      // 방문 체크
    static int max = 0;            // 최대 거리(지름) 저장
    static int maxIdx = 0;         // 최대 거리를 가지는 노드 번호 저장
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        // 1. 인접 리스트 초기화 (노드 번호가 1부터 시작하므로 n+1 크기)
        list = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 2. 간선 정보 입력 (n-1개)
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            // 양방향으로 연결 (루트에서 내려가는 것뿐만 아니라, 거꾸로 올라갈 수도 있어야 함)
            list[parent].add(new Node(child, weight));
            list[child].add(new Node(parent, weight));
        }
        
        // 노드가 1개일 경우 지름은 0 (예외 처리 안 해도 로직상 0 나오지만 명시적으로 생각)
        if(n == 1) {
            System.out.println(0);
            return;
        }

        // 3. 첫 번째 DFS: 임의의 노드(1번)에서 가장 먼 노드(maxIdx) 찾기
        visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0);
        
        // 4. 두 번째 DFS: 1번 탐색에서 찾은 가장 먼 노드(maxIdx)에서 다시 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        visited[maxIdx] = true;
        max = 0; // 거리 초기화 (중요)
        dfs(maxIdx, 0);
        
        // 5. 결과 출력
        System.out.println(max);
    }
    
    // DFS 탐색
    static void dfs(int current, int totalDist) {
        // 현재까지 온 거리가 기존 최대 거리보다 크면 갱신
        if(max < totalDist) {
            max = totalDist;
            maxIdx = current;
        }
        
        // 연결된 노드들 탐색
        for(Node next : list[current]) {
            if(!visited[next.to]) {
                visited[next.to] = true;
                // 다음 노드로 이동하면서 가중치를 더해줌
                dfs(next.to, totalDist + next.weight);
            }
        }
    }
}