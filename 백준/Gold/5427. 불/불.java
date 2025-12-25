import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] fireTime;  // 불이 각 칸에 도달하는 시간
    static int[][] jihunTime; // 지훈이가 각 칸에 도달하는 시간
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};
    
    static class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	C = Integer.parseInt(st.nextToken());
        	R = Integer.parseInt(st.nextToken());
        	
        	map = new char[R][C];
        	fireTime = new int[R][C];
        	jihunTime = new int[R][C];
        	
        	Queue<Point> fireQueue = new LinkedList<>();
        	Queue<Point> jihunQueue = new LinkedList<>();
        	
        	// 배열 초기화
        	// fireTime: 큰 값으로 초기화 (불이 도달하지 않는 곳은 무한대 취급)
        	// jihunTime: -1은 아직 방문하지 않음을 의미
        	for (int i = 0; i < R; i++) {
        		Arrays.fill(fireTime[i], Integer.MAX_VALUE);
        		Arrays.fill(jihunTime[i], -1);
        	}
        	
        	// 입력 받기
        	for (int i = 0; i < R; i++) {
        		String line = br.readLine();
        		for (int j = 0; j < C; j++) {
        			map[i][j] = line.charAt(j);
        			
        			if (map[i][j] == '*') {
        				fireQueue.offer(new Point(i, j));
        				fireTime[i][j] = 0; // 불 시작점은 0분
        			} else if (map[i][j] == '@') {
        				jihunQueue.offer(new Point(i, j));
        				jihunTime[i][j] = 0; // 지훈이 시작점은 0분
        			}
        		}
        	}
        	
        	// 1단계: 불 BFS - 각 칸에 불이 도달하는 시간 계산
        	bfsFire(fireQueue);
        	
        	// 2단계: 지훈이 BFS - 탈출 시도
        	int result = bfsJihun(jihunQueue);
        	
        	if (result == -1) {
        		System.out.println("IMPOSSIBLE");
        	} else {
        		System.out.println(result);
        	}
        }
    }
    
    // 불 확산 BFS
    static void bfsFire(Queue<Point> queue) {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                // 범위 체크
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                // 벽이거나 이미 불이 도달한 곳은 패스
                if (map[nr][nc] == '#' || fireTime[nr][nc] != Integer.MAX_VALUE) continue;
                
                fireTime[nr][nc] = fireTime[cur.r][cur.c] + 1;
                queue.offer(new Point(nr, nc));
            }
        }
    }
    
    // 지훈이 탈출 BFS
    static int bfsJihun(Queue<Point> queue) {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            
            // 가장자리에 도달하면 탈출 성공!
            // 현재 위치에서 한 발 더 나가면 탈출이므로 +1
            if (cur.r == 0 || cur.r == R - 1 || cur.c == 0 || cur.c == C - 1) {
                return jihunTime[cur.r][cur.c] + 1;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                // 범위 체크
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                // 벽이거나 이미 방문한 곳은 패스
                if (map[nr][nc] == '#' || jihunTime[nr][nc] != -1) continue;
                
                int nextTime = jihunTime[cur.r][cur.c] + 1;
                
                // 핵심: 지훈이가 도착하는 시간이 불보다 빨라야 함
                // fireTime이 MAX_VALUE면 불 도달 x -> 통과
                if (nextTime >= fireTime[nr][nc]) {
                    continue; // 불이 먼저 또는 동시에 도착하면 이동 불가
                }
                
                jihunTime[nr][nc] = nextTime;
                queue.offer(new Point(nr, nc));
            }
        }
        
        return -1; // 탈출 실패
    }
}