import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        
        // 큐에 카드가 1장 남을 때까지 반복
        while (queue.size() > 1) {
            // 1. 제일 위 카드 버리기
            queue.poll();
            
            // 2. 그다음 제일 위 카드를 제일 아래로 옮기기
            int topCard = queue.poll();
            queue.offer(topCard);
        }
        
        // 4. 마지막에 남은 카드 출력
        System.out.println(queue.poll());
    }
}