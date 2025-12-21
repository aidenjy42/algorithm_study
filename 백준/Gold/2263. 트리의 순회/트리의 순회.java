import java.io.*;
import java.util.*;

public class Main {
    static int[] inorder, postorder;
    static int[] inorderIdx;  // 값 → inorder에서의 인덱스 (O(1) 조회용)
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        inorder = new int[n];
        postorder = new int[n];
        inorderIdx = new int[n + 1];  // 노드 값은 1~n
        
        // inorder 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            inorderIdx[inorder[i]] = i;  // 값 → 인덱스 매핑
            /*
             * inorder = [4, 2, 5, 1, 3] 이면
             * inorderIdx[4] = 0
             * inorderIdx[2] = 1
             * inorderIdx[5] = 2 ...
             */
        }
        
        // postorder 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }
        
        solve(0, n - 1, 0, n - 1);
        
        System.out.println(sb);
    }
    
    // inStart~inEnd: inorder 범위
    // postStart~postEnd: postorder 범위
    static void solve(int inStart, int inEnd, int postStart, int postEnd) {
        // 기저조건: 범위가 비었으면 리턴
        if (inStart > inEnd) {
            return;
        }
        
        // 1. postorder 마지막이 루트
        int root = postorder[postEnd];
        
        // 2. root 출력(sb 저장)
        sb.append(root).append(" ");
        
        // 3. inorder에서 루트 위치 찾기 (위치 매핑 배열 이용 -> O(1))
        int rootIdx = inorderIdx[root];
        
        // 4. 왼쪽 서브트리 크기 계산
        int leftSize = rootIdx - inStart;
        
        // 5. 왼쪽 서브트리 재귀
        //    inorder:   [inStart, rootIdx-1]
        //    postorder: [postStart, postStart+leftSize-1]
        solve(inStart, rootIdx - 1, postStart, postStart + leftSize - 1);
        
        // 6. 오른쪽 서브트리 재귀
        //    inorder:   [rootIdx+1, inEnd]
        //    postorder: [postStart+leftSize, postEnd-1]
        solve(rootIdx + 1, inEnd, postStart + leftSize, postEnd - 1);
    }
}