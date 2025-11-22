import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] result;
    static boolean[] isSelected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[M];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        perm(0);
        System.out.println(sb);

    }
    
    static void perm(int cnt){
        if (cnt == M){
            for(int i=0; i<M; i++){
                sb.append(result[i]+" ");       
            }
            sb.append("\n");

            return;
        }

        int before = 0; // 이 depth에서 마지막에 넣은 값

        for(int i=0; i<N; i++){

            if(isSelected[i]) continue;

            if(before == arr[i]) continue;

            result[cnt] = arr[i];
            before = arr[i];
            
            isSelected[i] = true;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
}
