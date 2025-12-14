import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // 상근이 카드 체크
        boolean[] isSanggeun = new boolean[2 * n + 1];
        for (int i = 0; i < n; i++) {
            int card = Integer.parseInt(br.readLine());
            isSanggeun[card] = true;
        }
        
        // 각자의 카드를 ArrayList에 담고 정렬
        ArrayList<Integer> sanggeun = new ArrayList<>();
        ArrayList<Integer> geunsang = new ArrayList<>();
        
        for (int i = 1; i <= 2 * n; i++) {
            if (isSanggeun[i]) {
                sanggeun.add(i);
            } else {
                geunsang.add(i);
            }
        }
        
        // 이미 오름차순으로 들어감 (1부터 순회했으므로)
        // 만약 아니라면 Collections.sort() 사용
        
        // 각 카드를 사용했는지 체크
        boolean[] usedS = new boolean[n];
        boolean[] usedG = new boolean[n];
        
        int remainS = n;  // 상근이 남은 카드 수
        int remainG = n;  // 근상이 남은 카드 수
        
        int lastCard = 0;
        boolean sanggeunTurn = true;
        
        while (remainS > 0 && remainG > 0) {
            ArrayList<Integer> current;
            boolean[] used;
            
            if (sanggeunTurn) {
                current = sanggeun;
                used = usedS;
            } else {
                current = geunsang;
                used = usedG;
            }
            
            // lastCard보다 큰 카드 중 가장 작은 것 찾기
            int cardToPlay = -1;
            int cardIndex = -1;
            
            for (int i = 0; i < current.size(); i++) {
                if (!used[i] && current.get(i) > lastCard) {
                    cardToPlay = current.get(i);
                    cardIndex = i;
                    break;  // 정렬되어 있으므로 첫 번째가 최소
                }
            }
            
            if (cardToPlay != -1) {
                // 카드를 낼 수 있음
                used[cardIndex] = true;
                lastCard = cardToPlay;
                
                if (sanggeunTurn) {
                    remainS--;
                } else {
                    remainG--;
                }
            } else {
                // 패스 -> 바닥 초기화
                lastCard = 0;
            }
            
            sanggeunTurn = !sanggeunTurn;
        }
        
        // 상대방 남은 카드 수가 내 점수
        System.out.println(remainG);
        System.out.println(remainS);
    }
}