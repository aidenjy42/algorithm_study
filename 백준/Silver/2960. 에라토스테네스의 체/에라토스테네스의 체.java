import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int cnt = 0;
        boolean[] erased = new boolean[N + 1];
        // N <= 4000
        for(int i = 2; i <= N; i++){
            if(erased[i]) continue;

            // 안지워진 숫자들만
            for(int j = i; j <= N; j += i){
                if(erased[j]) continue;

                erased[j] = true;
                cnt++;

                if(cnt == K){
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}
