package silver.lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test6603 {
	static int k;
	static int[] nums, answer;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());

		while (k != 0) {
			nums = new int[k];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			answer = new int[6];

			combination(0, 0);
			sb.append("\n");

			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
		}
		sb.setLength(sb.length() - 1);
		System.out.print(sb);
	}

	private static void combination(int start, int idx) {
		if (idx == 6) {
			for (int n = 0; n < answer.length; n++) {
				sb.append(answer[n]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < nums.length; i++) {
			answer[idx] = nums[i];
			combination(i + 1, idx + 1);
		}

	}

}
