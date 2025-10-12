import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] presum;
    static int i, j;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        presum = new int[N+1];
        presum[0] = 0;

        st = new StringTokenizer(br.readLine().trim());
        for(int idx=1; idx<=N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
            presum[idx] = presum[idx-1] + arr[idx];
        }

        for(int idx=0; idx<M; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());

            System.out.println(presum[j] - presum[i-1]);
        }
    }
}
