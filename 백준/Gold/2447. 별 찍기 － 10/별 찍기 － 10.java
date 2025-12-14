import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static char[][] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        arr = new char[N][N];
        
        // 배열을 '*'로 초기화
        for (int i = 0; i < N; i++) {
        	Arrays.fill(arr[i], '*');
        }
        
     // 재귀적으로 가운데 부분을 공백으로 변경
        star(0, 0, N);
        
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append('\n');
        }
        System.out.print(sb);
		
	}
	
	static void star(int x, int y, int size) {
        // 기저 조건: 크기가 1이면 종료
        if (size == 1) return;
        
        int newSize = size / 3;
        
        // 9개 영역으로 분할
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // 가운데 영역(i=1, j=1)은 공백으로 채움
                if (i == 1 && j == 1) {
                    fillBlank(x + i * newSize, y + j * newSize, newSize);
                } else {
                    // 나머지 8개 영역은 재귀 호출
                    star(x + i * newSize, y + j * newSize, newSize);
                }
            }
        }
    }
    
    static void fillBlank(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                arr[i][j] = ' ';
            }
        }
    }
}
