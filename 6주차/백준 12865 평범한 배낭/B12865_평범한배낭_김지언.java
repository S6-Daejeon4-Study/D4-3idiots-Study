package gold.lv5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12865. 평범한 배낭
public class B12865_평범한배낭_김지언 {
	static int N, K;
	static int[][] knapsack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물품의 수
		K = Integer.parseInt(st.nextToken()); // 준서가 버틸 수 있는 무게
		knapsack = new int[N + 1][K + 1];
		
		for (int i = 1; i < knapsack.length; i++) { // i는 물건의 순서 - i번째 물건만 고려함
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken()); // 물건의 무게
			int V = Integer.parseInt(st.nextToken()); // 물건의 가치

			for (int j = 1; j < knapsack[i].length; j++) { // j는 배낭의 (임시)용량
				if (W > j) { // j보다 물건의 무게가 크면 이 물건은 배낭에 담을 수 없음
					knapsack[i][j] = knapsack[i - 1][j]; // 전에 물건까지 고려한 값을 넣기
				} else { // 이 물건을 배낭에 담을 수 있으면
					// 이 물건을 담았을 때 가치와 담지 않았을 때 가치 중 큰 값을 넣기
					knapsack[i][j] = Math.max(V + knapsack[i - 1][j - W], knapsack[i - 1][j]);
				}
			}
		}
		
		System.out.println(knapsack[N][K]);

	} // main end

}
