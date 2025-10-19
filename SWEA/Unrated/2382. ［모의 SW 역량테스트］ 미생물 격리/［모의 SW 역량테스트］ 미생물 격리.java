import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static class Group implements Comparable<Group> {
        int r, c; // 위치
        int cnt; // 미생물 수
        int dir; // 이동 방향

        public Group(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Group g) {
            // 행, 열-> 오름차순, 미생물 수 -> 내림차순
            if (this.r != g.r) {
                return this.r - g.r;
            }
            if (this.c != g.c) {
                return this.c - g.c;
            }
            // 내림차순
            return g.cnt - this.cnt;
        }

        void move() {
            r += dr[dir];
            c += dc[dir];
            // 약품 자리일 경우
            // 홀수면 +1, 짝수면 -1
            if (r == 0 || r == N-1 || c == 0 || c == N-1) {
                dir = (dir % 2 == 1 ? dir + 1 : dir - 1);
                cnt /= 2;
            }
        }
    }

    // 1:상 2:하 3:좌 4:우
    static int[] dr = { 0, -1, 1, 0, 0 };
    static int[] dc = { 0, 0, 0, -1, 1 };
    static int N, M, K;
    static List<Group> groups;
    static int result; // M 시간 이후 남은 미생물 수
    // N : 한줄 크기
    // M : 격리 시간
    // K : 군집 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            groups = new ArrayList<>();

            // K개 군집만큼 (행, 열, 미생물 수, 이동 방향)
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                groups.add(new Group(r, c, cnt, dir));
            }
            // 격리 시간 M동안 반복
            while (M-- > 0) {
                // 이동
                for (Group g : groups) {
                    // 소멸 군집 제외
                    if (g.cnt == 0)
                        continue;

                    g.move();
                }

                // 미생물 0인 군집 제거
                groups.removeIf(g -> g.cnt == 0);
                Collections.sort(groups);

                // 모든 미생물이 0이면 종료
                if(groups.isEmpty()) break;

                // 정렬된 리스트 순회하며 병합
                List<Group> nextList = new ArrayList<>();

                // 첫 번째 군집을 '병합 기준' 군집으로 설정 (미생물 수 가장 큼)
                Group mergedCluster = groups.get(0);

                for (int i = 1; i < groups.size(); i++) {
                    Group current = groups.get(i);

                    if (current.r == mergedCluster.r && current.c == mergedCluster.c) {
                        // 같은 위치 -> 병합
                        // 정렬에 의해 mergedCluster가 항상 방향을 가짐
                        mergedCluster.cnt += current.cnt;
                    } else {
                        // 다른 위치 -> 다음 턴 리스트에 추가
                        nextList.add(mergedCluster);
                        // '현재 군집'을 새로운 '병합 기준'으로 설정
                        mergedCluster = current;
                    }
                }
                
                // 마지막 '병합 기준' 군집을 리스트에 추가
                nextList.add(mergedCluster);

                // 다음 시뮬레이션을 위해 리스트 교체
                groups = nextList; 
            }

            int totalCnt = 0;
            for(Group g: groups){
                totalCnt += g.cnt;
            }
            sb.append("#").append(tc).append(" ").append(totalCnt).append("\n");
        }
        System.out.println(sb);
    }

}