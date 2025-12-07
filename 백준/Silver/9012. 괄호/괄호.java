import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        for(int i = 0; i < T; i++) {
            String input = br.readLine();
            sb.append(solve(input)).append('\n');
        }

        System.out.println(sb);
    }

    public static String solve(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 1. 여는 괄호 '(' 는 스택에 담는다.
            if(c == '(') {
                stack.push(c);
            }
            // 2. 닫는 괄호 ')' 를 만났을 때
            else if(c == ')') {
                // 스택이 비어있는데 닫는 괄호가 나오면 올바르지 않음 (예: "())")
                if(stack.isEmpty()) {
                    return "NO";
                }
                // 짝이 맞으므로 여는 괄호를 하나 꺼냄
                stack.pop();
            }
        }

        // 3. 모든 과정을 끝냈는데 스택에 괄호가 남아있다면 올바르지 않음 (예: "(()")
        if(stack.isEmpty()) {
            return "YES";
        } else {
            return "NO";
        }
    }
}