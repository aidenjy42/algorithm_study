import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        // 소수 리스트 구하기(에라토스)
        primes = getPrimes(N);

        // 연속합의 경우의 수 찾기(투 포인터)
        int count = 0;
        int sum = 0;
        int start = 0; // 시작 포인터
        
        // end 포인터는 소수 리스트의 크기(primes.size())까지 이동하면서
        // sum에 값을 더한 후 이동
        for (int end = 0; end < primes.size(); end++) {
            // 현재 end가 가리키는 소수를 sum에 더함
            sum += primes.get(end);

            // sum이 N보다 크거나 같아질 때까지 start 포인터를 이동
            while (sum >= N) {
                // 연속합이 N과 일치하는 경우, 경우의 수 증가
                if (sum == N) {
                    count++;
                }
                
                // 합이 크거나 같은 경우
                // start가 가리키는 값을 빼고 start를 이동시켜 부분합을 줄임
                sum -= primes.get(start);
                start++;
            }
        }
        
        System.out.println(count);
    }

    // 에라토스테네스의 체
    private static List<Integer> getPrimes(int N) {
        if (N < 2) {
            return new ArrayList<>();
        }

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        
        isPrime[0] = isPrime[1] = false;

        // i * i <= N 까지만 확인 (최적화)
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
        return primeList;
    }
}