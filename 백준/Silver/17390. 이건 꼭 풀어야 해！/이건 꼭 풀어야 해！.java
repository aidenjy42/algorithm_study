import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int Q = Integer.parseInt(st.nextToken()); // 질문의 개수

        int[] arr = new int[N]; // 입력받을 배열
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 비내림차순 정렬 (문제 조건)
        Arrays.sort(arr);

        // 2. 누적 합(Prefix Sum) 배열 생성
        // sum[i]는 arr[0]부터 arr[i-1]까지의 합 (1-based indexing 처리를 위해 N+1 크기 할당)
        int[] sum = new int[N + 1];
        sum[0] = 0;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // 3. 구간 합 구하기: S[R] - S[L-1]
            int result = sum[R] - sum[L - 1];
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }
}