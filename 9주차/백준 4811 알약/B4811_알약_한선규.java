import java.io.*;

/**
 * @author comkkyu
 * @since 21. 12. 12
 *
 * 백준 4811 알약 - https://www.acmicpc.net/problem/4811
 *
 * 동적프로그래밍으로 구현하는 문제
 * N = 1일때, 2일때, 3일때를 직접 그려보고 dp[n] += dp[i] * dp[N-1-i] (i = 0 부터 N - 1 까지 반복) 이라는 점화식을 찾아냄.
 *
 */
public class B4811_알약_한선규 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[] dp = new long[31]; // dp[n] : 약의 개수가 n 개일 때 가능한 서로 다른 문자열의 개수

        // N 이 1일때, 2일때 가능한 서로 다른 문자열의 개수 초기화
        // N 이 0일 경우에는 곱셈연산에서 개수를 그대로 유지하기 위해서 1로 초기화
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int n = 3; n <= 30; n++) {
            for (int i = 0; i <= n-1; i++) {
                dp[n] += dp[i] * dp[n-1-i];
            }
        }

        while (true) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) // 입력의 마지막줄에는 0이 하나 주어진다.
                break;

            bw.write(dp[num]+"");
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
