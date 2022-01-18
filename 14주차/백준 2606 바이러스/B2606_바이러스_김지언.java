package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test2606 {
	public static class Link {
		int node;
		Link link;

		public Link(int node, Link link) {
			this.node = node;
			this.link = link;
		}
	}

	static int N, E;
	static Link[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		edges = new Link[N + 1];
		for (int e = 0; e < E; e++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a] = new Link(b, edges[a]);
			edges[b] = new Link(a, edges[b]);
		} // input end

		int answer = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(1);
		visited[1] = true;

		while (!queue.isEmpty()) {
			Link next = edges[queue.poll()];

			while (next != null) {
				if (!visited[next.node]) {
					queue.offer(next.node);
					visited[next.node] = true;
					answer++;
				}
				next = next.link;
			}
		}

		System.out.println(answer);
	}

}