package gold.lv4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test11657 {
	public static class Bus {
		int start, dest, weight;

		public Bus(int start, int dest, int weight) {
			this.start = start;
			this.dest = dest;
			this.weight = weight;
		}
	}

	static int N, M; // 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)
	static long[] dist;
	static Bus[] edgeList; // 간선 - 버스 정보 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edgeList = new Bus[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Bus(start, dest, weight);
		} // input end
		dist = new long[N + 1]; // 최단거리 테이블
		Arrays.fill(dist, Long.MAX_VALUE); // 무한대로 초기화
		dist[1] = 0; // 시작점만 0

		if (bellmanFord()) {
			System.out.println(-1);
		} else {
			for (int i = 2; i < dist.length; i++) {
				if (dist[i] == Long.MAX_VALUE) { // 도달할 수 없는 경우
					System.out.println(-1);
				} else {
					System.out.println(dist[i]);
				}
			}
		}
	}

	private static boolean bellmanFord() {
		for (int i = 1; i <= N; i++) { // 싸이클을 가지지 않는 범위 내에서 확인
			for (int j = 0; j < edgeList.length; j++) { // 간선들 확인
				int start = edgeList[j].start;
				int dest = edgeList[j].dest;
				int weight = edgeList[j].weight;

				if (dist[start] != Long.MAX_VALUE && dist[dest] > dist[start] + weight) { // 기존 값보다 작으면
					dist[dest] = dist[start] + weight; // 값 갱신
					if (i == N) { // 끝났는데 값이 갱신되면 음수 순환이 존재
						return true;
					}
				}
			}
		}
		return false;
	}

}
