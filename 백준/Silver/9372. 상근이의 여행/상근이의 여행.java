
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 에러 안 나게 입력만 받기..
            for (int i = 0; i < M; i++) {
                br.readLine();
            }

            // MST의 (최소) 간선 수 : 항상 N-1.. (이게 뭐야)
            sb.append((N - 1)).append('\n');
        }

        System.out.print(sb);
    }
}