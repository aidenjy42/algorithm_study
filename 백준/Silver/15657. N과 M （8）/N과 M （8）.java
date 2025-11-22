import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] result;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        comb(0,0);
        System.out.println(sb);

    }
    static void comb(int cnt, int start){
        if (cnt == M){
            for(int i=0; i<M; i++){
                sb.append(result[i]+" ");       
            }
            sb.append("\n");

            return;
        }

        for(int i=start; i<N; i++){
            result[cnt] = arr[i];
            comb(cnt+1, i);
        }
    }
}
