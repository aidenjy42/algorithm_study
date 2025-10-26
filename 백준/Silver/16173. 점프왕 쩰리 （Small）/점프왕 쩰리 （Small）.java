import java.util.Scanner;

public class Main {
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static boolean success = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0);
        System.out.println(success ? "HaruHaru" : "Hing");
        sc.close();
    }

    static void dfs(int x, int y) {
        // 범위 체크
        if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y]) {
            return;
        }
        // 도착
        if (board[x][y] == -1) {
            success = true;
            return;
        }
        visited[x][y] = true;

        int jump = board[x][y];
        if (jump > 0) {
            dfs(x, y + jump); // 오른쪽 이동
            dfs(x + jump, y); // 아래로 이동
        }
    }
}
