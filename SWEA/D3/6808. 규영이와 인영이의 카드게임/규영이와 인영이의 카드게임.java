import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[] ky_cards;
	static boolean[] isSelected;
	static ArrayList<Integer> iy_list;
	static int[] result_iy;
 	static int winCnt, loseCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			ky_cards = new int[9];
			isSelected = new boolean[19];
			winCnt = 0;
			loseCnt = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				int s = Integer.parseInt(st.nextToken());
				ky_cards[i] = s;
				isSelected[s] = true; // 규영이가 가지고 있는 카드는 true로
			}
			
			iy_list = new ArrayList<>();
			//1. 인영이 카드 선택
			for(int i=1; i<=18; i++) {
				if(!isSelected[i])
					iy_list.add(i);
			}
			//2. 인영이 카드 순서 정하기 - 순열
			isSelected = new boolean[19];
			result_iy = new int[9];
			perm(0);
			
			System.out.printf("#%d %d %d\n", tc, winCnt, loseCnt);
		}
	}
	
	private static void perm(int idx) {
		
		// 기저 조건
		if(idx == 9) {
			// 규영이의 점수 계산
			getCnt(result_iy);
			return;
		}
		// iy_list에서 뽑기
		for(int i=0; i<9; i++) {
			if(isSelected[i]) continue;
			result_iy[idx] = iy_list.get(i);
			
			isSelected[i] = true;
			perm(idx+1);
			isSelected[i] = false;
		}
		
	}
	// 인영이의 카드 순서 경우에 따라 규영이가 이기는/지는 경우의 수
	private static void getCnt(int[] iy_cards) {
		int ky_score=0, iy_score=0;

		for(int i=0; i<9; i++) {
			if(ky_cards[i] > iy_cards[i]) {
				ky_score += (ky_cards[i] + iy_cards[i]);
			}
			else if(ky_cards[i] < iy_cards[i]) {
				iy_score += (ky_cards[i] + iy_cards[i]);
			}
		}
		
		if(ky_score > iy_score) {
			winCnt++;
		}
		else if(ky_score < iy_score) {
			loseCnt++;
		}
	}
}