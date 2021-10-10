package silver.lv2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 효율적인 해킹
// 시간초과...
public class Test1325 {
	public static class Computer implements Comparable<Computer> {
		int num, cnt;

		public Computer(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Computer o) {
			return o.cnt - cnt;
		}
	}

	public static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}

	static int N, M;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes = new Node[N + 1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[b] = new Node(a, nodes[b]);
		} // input end

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		for (int i = 1; i < nodes.length; i++) {
			pq.add(new Computer(i, BFS(i)));
		}

		int pre = -1;
		while (!pq.isEmpty()) {
			Computer com = pq.poll();

			if (pre != -1 && com.cnt == pre)
				bw.write(" " + com.num);
			else if (pre == -1)
				bw.write(com.num);
			else
				break;
			pre = com.cnt;
		}
		bw.close();
	}

	private static int BFS(int start) {
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			Node node = nodes[q.poll()];

			while (node != null) {
				if (visited[node.vertex])
					continue;
				q.offer(node.vertex);
				visited[node.vertex] = true;
				cnt++;
				node = node.link;
			}
		}
		return cnt;
	}

}