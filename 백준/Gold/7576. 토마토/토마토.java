import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int M, N;
    static int[][] map;
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        int unripeCount = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if (map[i][j] == 1) {
                    queue.add(new int[]{i, j});
                } else if (map[i][j] == 0) {
                    unripeCount++;
                }
            }
        }

        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        bfs();

        int maxDays = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1); // 불가능
                    return;
                }
                maxDays = Math.max(maxDays, map[i][j]);
            }
        }
        System.out.println(maxDays - 1);
    }

    static void bfs() {

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = y + dr[i];
                int nc = x + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (map[nr][nc] == 0) {
                        map[nr][nc] = map[y][x] + 1;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }
}
