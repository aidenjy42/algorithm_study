import java.io.*;
import java.util.*;

public class Main {
    static int W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 블록의 가로, 세로 길이
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 상점의 개수
        int N = Integer.parseInt(br.readLine());
        
        // 상점들의 위치를 저장할 배열
        int[] storeLocs = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            // 위치를 1차원 좌표로 변환하여 저장
            storeLocs[i] = getLinearPosition(dir, dist);
        }

        // 경비원의 위치 입력
        st = new StringTokenizer(br.readLine());
        int guardDir = Integer.parseInt(st.nextToken());
        int guardDist = Integer.parseInt(st.nextToken());
        int guardLoc = getLinearPosition(guardDir, guardDist);

        // 전체 둘레
        int totalLength = 2 * (W + H);
        int sum = 0;

        for (int i = 0; i < N; i++) {
            // 1. 단순 거리 차이 (시계 방향 혹은 반시계 한쪽 방향 거리)
            int diff = Math.abs(guardLoc - storeLocs[i]);
            
            // 2. 더 짧은 거리 선택 (직선 거리 vs 반대편으로 돌아가는 거리)
            sum += Math.min(diff, totalLength - diff);
        }

        System.out.println(sum);
    }

    // 방향과 거리를 입력받아, 북서쪽 모서리(0)를 기준으로 시계방향 펼친 좌표를 반환하는 함수
    static int getLinearPosition(int dir, int dist) {
        // 1: 북, 2: 남, 3: 서, 4: 동
        switch (dir) {
            case 1: // 북쪽: 왼쪽 모서리 기준 거리 그대로
                return dist;
            case 4: // 동쪽: 북쪽 다 지나고(W) + 위쪽 모서리 기준 거리(dist)
                return W + dist;
            case 2: // 남쪽: 북(W) + 동(H) + (오른쪽 끝에서부터의 거리니까 W - dist)
                return W + H + (W - dist);
            case 3: // 서쪽: 북(W) + 동(H) + 남(W) + (아래쪽 끝에서부터의 거리니까 H - dist)
                return W + H + W + (H - dist);
        }
        return 0;
    }
}