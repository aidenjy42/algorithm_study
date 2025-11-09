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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[M];
        isSelected = new boolean[N];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        perm(0);
        System.out.println(sb);
    }

    static void perm(int cnt) {
        if (cnt == M) {
            for (int i : result) {
                sb.append(i + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            result[cnt] = arr[i];
            perm(cnt + 1);
        }
    }
}
