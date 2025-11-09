
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] indegree;
    static List<List<Integer>> adjList = new ArrayList<>();
    static int[] cost; 
    static int[] dp;  // N번 건물이 완성되기까지 걸리는 최소 시간

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        cost = new int[N+1];
        dp = new int[N+1];

        for(int i=0; i<=N; i++){
            adjList.add(new ArrayList<>());
        }
        // 입력
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 일단 각 건물의 설치 시간을 저장
            cost[i] = Integer.parseInt(st.nextToken());
            dp[i] = cost[i];

            // 2번째 부턴 먼저 지어야 하는 건물 번호
            while(true){
                //-1 이면 break;
                int input = Integer.parseInt(st.nextToken());
                if(input == -1){
                    break;
                }
                else {
                    adjList.get(input).add(i);
                    indegree[i]++; // 먼저 지어야 하는 건물 갯수만큼 증가
                }
            }
        }

        bfs();

        // 출력
        for(int i=1; i<=N; i++){
            System.out.println(dp[i]);
        }
    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        
        // 진입 차수가 0인 노드부터 큐 삽입
        for(int i=1; i<=N; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for(int to: adjList.get(cur)){
                indegree[to]--;
                // to번 건물의 총 건설 시간: cur건물이 걸리는 최소 시간 + to건물 건설 시간
                dp[to] = Math.max(dp[to], cost[to] + dp[cur]);
                //진입 차수가 0이 되면 큐 삽입
                if(indegree[to] == 0){
                    q.offer(to);
                }
            }
            
        }
    }
}
