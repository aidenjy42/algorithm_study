import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static boolean[] isSelected;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        isSelected = new boolean[n+1];
        result = new int[m];

        perm(0);
        System.out.println(sb);
    }
    private static void perm(int cnt) {
        if(cnt == m){
            for(int i=0; i<m; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++){
            if(isSelected[i]) continue;

            result[cnt] = i;
            isSelected[i] = true;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
}
