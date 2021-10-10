package silver.lv5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 올림픽
public class Test8979 {
	public static class Country implements Comparable<Country> {
		int num, gold, silver, bronze;

		public Country(int num, int gold, int silver, int bronze) {
			this.num = num;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

		@Override
		public int compareTo(Country c) {
			if (gold == c.gold) {
				if (silver == c.silver) {
					return c.bronze - bronze;
				}
				return c.silver - silver;
			}
			return c.gold - gold;
		}
	}

	static int N, K;
	static PriorityQueue<Country> pq;
	static int[] ranks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		ranks = new int[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			pq.add(new Country(num, gold, silver, bronze));
		} // input end

		Country pre = null;
		for (int i = 1; i <= N; i++) {
			Country cty = pq.poll();
			if (pre != null && pre.compareTo(cty) == 0) {
				ranks[cty.num] = ranks[pre.num];
			} else {
				ranks[cty.num] = i;
			}

			if (cty.num == K)
				break;
			pre = cty;
		}
		System.out.println(ranks[K]);
	}

}
