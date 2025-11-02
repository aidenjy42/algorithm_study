import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<Point> houses = new ArrayList<>();
	static List<Point> chickens = new ArrayList<>();
	static Point[] selectedChickens;
	static int minTotalDist = Integer.MAX_VALUE;

	// 좌표를 저장하기 위한 Point 클래스
	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		selectedChickens = new Point[M]; 

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 1) {
					houses.add(new Point(i, j));
				} else if (val == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}

		comb(0, 0);

		System.out.println(minTotalDist);
	}

	// 치킨집 M개 정하기- 조합
	static void comb(int start, int cnt) {
		if (cnt == M) {
			int currentTotalDist = calcTotalDist();
			// 최소값 갱신
			minTotalDist = Math.min(minTotalDist, currentTotalDist);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			selectedChickens[cnt] = chickens.get(i);

			comb(i + 1, cnt + 1);
		}
	}

	// 도시의 치킨 거리 계산
	static int calcTotalDist() {
		int totalDist = 0;

		// 모든 집
		for (Point house : houses) {
			int minDist = Integer.MAX_VALUE;

			// 모든 M개 치킨집
			for (Point chicken : selectedChickens) {
				int dist = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
				minDist = Math.min(minDist, dist); // 최솟값 갱신
			}
			totalDist += minDist;
		}

		return totalDist;
	}
}
