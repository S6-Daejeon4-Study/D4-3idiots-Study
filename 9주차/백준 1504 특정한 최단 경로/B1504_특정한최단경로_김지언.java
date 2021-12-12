package gold.lv4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Test1504 {
	public static class Node implements Comparable<Node> {
		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}

	static int N, E, V1, V2;
	static ArrayList<ArrayList<Node>> edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		for (int i = 0; i < N + 1; i++) {
			edges.add(new ArrayList<>());
		}

		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.get(a).add(new Node(b, c));
			edges.get(b).add(new Node(a, c));
		}
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		// input end
		int res1 = Integer.MAX_VALUE, res2 = Integer.MAX_VALUE;

		int a1 = dijkstra(1, V1);
		int b1 = dijkstra(V1, V2);
		int c1 = dijkstra(V2, N);

		if (a1 >= 0 && b1 >= 0 && c1 >= 0) {
			res1 = a1 + b1 + c1;
		}

		int a2 = dijkstra(1, V2);
		int b2 = dijkstra(V2, V1);
		int c2 = dijkstra(V1, N);

		if (a2 >= 0 && b2 >= 0 && c2 >= 0) {
			res2 = a2 + b2 + c2;
		}

		System.out.println(Math.min(res1, res2) == Integer.MAX_VALUE ? -1 : Math.min(res1, res2));

	} // main end

	private static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N + 1];
		int[] cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, cost[start]));

		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.vertex;

			if (!visited[cur]) {
				visited[cur] = true; // 정점 방문 처리

				for (Node node : edges.get(cur)) { // 정점의 연결 노드를 탐색하면서
					if (!visited[node.vertex] && cost[node.vertex] > cost[cur] + node.weight) { // 연결 노드의 거리가 업데이트되면
						cost[node.vertex] = cost[cur] + node.weight;
						pq.add(new Node(node.vertex, cost[node.vertex])); // 다음 방문 처리
					}
				}
			}
		}
		return cost[end] != Integer.MAX_VALUE ? cost[end] : -1;
	}
}
