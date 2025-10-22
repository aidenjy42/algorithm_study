import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		// (전체 길이) - (0을 뺀 길이) = 0의 개수
		int zeroCount = s.length() - s.replace("0", "").length();
		int oneCount = s.length() - zeroCount;
		
		// 제거해야할 0,1의 개수
		zeroCount /= 2;
		oneCount /= 2;
		
		int len = s.length();
		// 제거할 인덱스 위치를 저장하기 위해 boolean 배열 생성
		boolean[] remove = new boolean[len];
		
		for(int idx=len-1; idx>=0; idx--) {
			if(zeroCount == 0) break;
			
			if(s.charAt(idx) == '0') {
				remove[idx] = true;
				zeroCount--;
			}
		}

		for(int idx=0; idx<=len-1; idx++) {
			if(oneCount == 0) break;
			if(s.charAt(idx) == '1') {
				remove[idx] = true;
				oneCount--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<len; i++) {
			 if(!remove[i])
				 sb.append(s.charAt(i));
		}
		System.out.println(sb);
	}
}
