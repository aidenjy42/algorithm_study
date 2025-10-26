import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int min=Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for(int i=0; i<N; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        System.out.println(min+" "+max);
    }
}
