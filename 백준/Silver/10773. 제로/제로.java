import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int K = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());
            
            if (num == 0) {
                // 2) num이 0이면 pop
                // (문제 조건에 따라 스택이 비어있는 경우는 없음)
                stack.pop();
            } else {
                // 1) 0이 아니면 push
                stack.push(num);
            }
        }
        // 남은 수 계산
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        
        System.out.println(sum);
    }
}
