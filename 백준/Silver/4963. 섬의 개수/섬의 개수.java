import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int W;
	static int H;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int count;
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스
		String line = br.readLine();
		while(!line.equals("0 0")) {
			StringTokenizer st = new StringTokenizer(line);

			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			visited = new boolean[H][W];
			count = 0;

			for (int i = 0; i < H; i++) {
	            st = new StringTokenizer(br.readLine());
	            for (int j = 0; j < W; j++) {
	                map[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }

			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			sb.append(count+"\n");
			
			line = br.readLine();
		}
		System.out.println(sb);

	}
	static void bfs(int r, int c) {
		count++;
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int dir = 0; dir < 8; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];
				
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				
				if(map[nr][nc] == 1 && !visited[nr][nc]) {
					q.offer(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		
	}

}
