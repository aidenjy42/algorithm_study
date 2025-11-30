import java.io.*;
import java.util.*;

public class Main {
    // 5x5 빙고판
    static int[][] map = new int[5][5];
    // 사회자가 숫자를 부른 횟수
    static int callCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 철수의 빙고판 입력
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 사회자가 부르는 수 입력 (5줄에 걸쳐 25개가 들어옴)
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                callCount++; // 몇 번째로 부른 수인지 카운트

                // 해당 숫자를 찾아 지움 (0으로 표시)
                checkNumber(num);

                // 빙고가 3줄 이상 완성되었는지 확인
                if (countBingo() >= 3) {
                    System.out.println(callCount);
                    return; // 프로그램 종료
                }
            }
        }
    }

    // 숫자를 찾아서 0(지워짐)으로 변경하는 메서드
    static void checkNumber(int num) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (map[r][c] == num) {
                    map[r][c] = 0;
                    return;
                }
            }
        }
    }

    // 현재 완성된 빙고 줄의 개수를 세는 메서드
    static int countBingo() {
        int lineCount = 0;

        // 가로(행) 검사
        for (int r = 0; r < 5; r++) {
            int cnt = 0;
            for (int c = 0; c < 5; c++) {
                if (map[r][c] == 0) cnt++;
            }
            if (cnt == 5) lineCount++;
        }

        // 세로(열) 검사
        for (int c = 0; c < 5; c++) {
            int cnt = 0;
            for (int r = 0; r < 5; r++) {
                if (map[r][c] == 0) cnt++;
            }
            if (cnt == 5) lineCount++;
        }

        // 대각선 1 (왼쪽 위 -> 오른쪽 아래) 검사
        int diag1Cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 0) diag1Cnt++;
        }
        if (diag1Cnt == 5) lineCount++;

        // 대각선 2 (오른쪽 위 -> 왼쪽 아래) 검사
        int diag2Cnt = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][4 - i] == 0) diag2Cnt++;
        }
        if (diag2Cnt == 5) lineCount++;

        return lineCount;
    }
}