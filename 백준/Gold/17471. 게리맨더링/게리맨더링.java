import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] population;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] isGroupA;
    static List<Integer> a, b;
    static int minDiff = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N+1];

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 인구 수
        for(int i=1; i<=N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        // N번 만큼 반복
        // i번 구역에서 인접한 to 구역들
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int adjCnt = Integer.parseInt(st.nextToken());
            // 인접 구역 수 만큼 반복
            for(int j=1; j<=adjCnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph.get(i).add(to);
            }
        }
        
        isGroupA = new boolean[N+1];
        subset(1);

        if(minDiff == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(minDiff);
        }
    }
    
    // 부분 집합
    static void subset(int idx) {
        // 기저조건
        if(idx == N+1) {
            // A 선거구 선택 완료
            
            a = new ArrayList<>();
            b = new ArrayList<>();
            
            //A 개수가 N || 0일 때 continue;
            for(int i=1; i<=N; i++) {
                if(isGroupA[i]) a.add(i);
                else b.add(i);
            }

            if(a.size() == 0 || b.size() == 0) return;
            
            // a, b 모두 연결된 상태면 차이 구하고 최솟값 갱신
            if(bfs(a) && bfs(b)) {
                int diff = calcDiff();
                if(minDiff == -1 || minDiff > diff){
                    minDiff = diff;
                }
            }
            
            return;
        }
        
        isGroupA[idx] = true;
        subset(idx+1);
        isGroupA[idx] = false;
        subset(idx+1);
    }

    static boolean bfs(List<Integer> group) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        
        // 첫번째 거 일단 넣기
        q.offer(group.get(0));
        visited[group.get(0)] = true;
        
        int visitCnt = 1;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next: graph.get(cur)){
                // 방문하지 않은 것중에 group 안에 있는 구역
                if(!visited[next] && group.contains(next)){
                    q.offer(next);
                    visited[next] = true;
                    visitCnt++;
                }
            }
        }
        
        // A/B 선거구에 모두 방문을 했다면 true
        if(visitCnt == group.size())
            return true;
        return false;
    }
    
    static int calcDiff() {
        int aPop = 0, bPop = 0;
        for(int i=1; i<=N; i++) {
            if(isGroupA[i])
                aPop += population[i];
            else
                bPop += population[i];
        }
        return Math.abs(aPop - bPop);
    }

}