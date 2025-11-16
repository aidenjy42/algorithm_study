import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int start = 0;
        int end = N - 1;
        int count = 0;
        
        while (start < end) {
            int sum = arr[start] + arr[end];
            
            if (sum == x) {
                // 1: 합이 x와 같음 (쌍을 찾음)
                count++;
                start++; // 다음 수 탐색
                end--;   // 다음 수 탐색
            } else if (sum < x) {
                // 2: 합이 x보다 작음
                start++; // 합을 키우기 위해 start를 오른쪽으로 이동
            } else {
                // 3: 합이 x보다 큼
                end--; 
            }
        }
        
        System.out.println(count);

    }
}
