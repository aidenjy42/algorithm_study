import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] result;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sb = new StringBuilder();
        result = new int[M];

        perm(0);
        System.out.println(sb);
        sc.close();
    }

    static void perm(int cnt) {

        if (cnt == M) {
            for (int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            result[cnt] = i;
            perm(cnt + 1);
        }
    }
}
