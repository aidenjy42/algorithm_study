import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Main {
    static int N;
    static int K;
    static int[] time; // -1 이면 미방문

    static class Node {
        int vertex;
        int time; // 해당 노드까지 가는 데 걸린 시간

        public Node(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        if(N == K)
            System.out.println(0);
        else
            bfs();

    }
    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        time = new int[100001];
        Arrays.fill(time, -1);
        q.offer(N);
        time[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            // 시간 증가
            
            int[] next = {cur-1, cur+1, cur * 2};

            for(int to: next){
                // 범위 체크
                if(to < 0 || to > 100000) continue;
                // 방문 체크
                if(time[to] != -1) continue;

                //아래부터 갈 수 있는 노드
                time[to] = time[cur]+1;
                
                if(to == K){
                    System.out.println(time[to]);
                    break;
                }
                // 큐에 넣고 방문 처리
                else {
                    q.offer(to);
                }
            }
        }
    }
}