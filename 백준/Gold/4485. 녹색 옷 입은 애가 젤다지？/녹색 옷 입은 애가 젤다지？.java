import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int[][] minDist;
	static boolean[][] visited;
	static final int INF = Integer.MAX_VALUE;
	static int ans;
	
	static class Node {
		int r, c;
		int weight;
		
		public Node(int r, int c, int weight) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int pCnt = 0;
		N = Integer.parseInt(br.readLine());
		
		while(N != 0) {
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dijkstra();
			sb.append("Problem ").append(++pCnt).append(": ").append(ans).append("\n");
			
			N = Integer.parseInt(br.readLine());
		}
		System.out.println(sb);
	}
	
	static void dijkstra() {
		minDist = new int[N][N];
		visited = new boolean[N][N];
		ans = 0;
		
		for (int[] row : minDist) {
		    Arrays.fill(row, INF);
		}
		
		minDist[0][0] = map[0][0];
		
		PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
		pq.offer(new Node(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			int curR = cur.r;
			int curC = cur.c;
			
			// cur에서 상하좌우 탐색
			for(int dir=0; dir<4; dir++) {
				int nr = curR + dr[dir];
				int nc = curC + dc[dir];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(visited[nr][nc]) continue;
				
				if(minDist[nr][nc] > minDist[curR][curC] + map[nr][nc]) {
					minDist[nr][nc] = minDist[curR][curC] + map[nr][nc];
					pq.offer(new Node(nr, nc, minDist[nr][nc]));
				}
			}
		}
		ans = minDist[N-1][N-1];
	}
}
