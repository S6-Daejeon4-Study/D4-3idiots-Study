import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 11. 6
 *
 * 백준 12865 평범한 배낭 https://www.acmicpc.net/problem/12865
 *
 * DP 알고리즘
 * 큰 문제를 작은 문제로 쪼개서 해결하는 (Divide-and-Conquer) 라는 분할정복 원리에 기반을 두고 있는 알고리즘이다.
 * 이전에 계산해둔 메모리 (배열 등)에 저장해서 반복 작업을 줄이는 기법 (Memoization) 이 핵심이다.
 *
 */
public class B12865_평범한배낭_한선규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dp; // 배낭의 무게가 0부터 K일때 0부터 N개의 물품에 대한 최적의 가치를 고려해서 저장할 배열
        int[][] product; // 각물품의 무게와 가중치를 저장할 배열 (행번호가 곧 물품의 번호). 0번 열은 무게, 1번 열은 가치를 나타냄
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];
        product = new int[N+1][2];

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            product[n][0] = Integer.parseInt(st.nextToken()); // n 번 물품의 무게
            product[n][1] = Integer.parseInt(st.nextToken()); // n 번 물품의 가치
        }

        for (int i = 1; i <= N; i++) { // 1번 물건 부터
            for (int j = 1; j <= K; j++) { // 배낭의 무게가 K 일때 까자 반복
                 if (product[i][0] > j) { // i 번 물건의 무게가 현재 배낭의 무게보다 크다면 !!
                     dp[i][j] = dp[i-1][j]; // i 번 물건을 고려하지 않고 이전 물건까지 고려한 경우가 최적해가 된다.
                 } else { // i 번물건을 고려하고 남은 배낭의 무게의 최적해의 합과 i번 물건 이전까지고 고려한 최적해중에서 더 큰 값을 고르면 된다.
                     dp[i][j] = Math.max(product[i][1] + dp[i-1][j-product[i][0]], dp[i-1][j]);
                 }
            }
        }

        System.out.println(dp[N][K]); // N개의 물건에 대해서 배낭의 무게가 K개일 때까지 고려한 최적해가 문제에서 요구하는 답이 된다.
    }
}
