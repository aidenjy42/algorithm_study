import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] fanin;
	static List<List<Integer>> adjList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fanin = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			adjList.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			
			adjList.get(front).add(back);
			fanin[back]++;
		}
		bfs();
		System.out.println(sb);
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			// 진입차수 0인 노드 큐에 넣기
			if(fanin[i] == 0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur+" ");
			for(int to: adjList.get(cur)) {
				fanin[to]--;
				// 진입 차수 0이 되면 큐 삽입
				if(fanin[to] == 0) {
					q.offer(to);
				}
			}
		}
	}
}
