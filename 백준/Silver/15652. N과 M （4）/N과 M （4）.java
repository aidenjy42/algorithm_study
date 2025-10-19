import java.util.Scanner;

public class Main {
    static int N, M;
    static int[] result;
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        result = new int[M];
        sb = new StringBuilder();
        comb(0, 1);
        System.out.println(sb);
        sc.close();
    }
    static void comb(int cnt, int start){
        //기저 조건: cnt == M이면 종료
        if(cnt == M){
            for(int i: result){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i<=N; i++){
            result[cnt] = i;
            comb(cnt+1, i); 
        }
    }
}