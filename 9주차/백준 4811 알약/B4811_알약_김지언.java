package gold.lv5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test4811 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] dp = new long[31];
		dp[0] = 1;
		dp[1] = 1;
		// 카탈란 수 공식 응용: (2n)!/(n!*(n+1)!)
		for (int i = 2; i < dp.length; i++) {
			dp[i] = dp[i - 1] * 2 * (2 * i - 1) / (i + 1);
		}
		int N = Integer.parseInt(br.readLine());
		while (N != 0) {
			System.out.println(dp[N]);
			N = Integer.parseInt(br.readLine()); // 다음 TC 입력
		}
	}

}