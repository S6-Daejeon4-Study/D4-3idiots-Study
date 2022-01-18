package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test2573 {

	public static class Node {
		int x, y, val;

		public Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[][] delta = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input end

		int cnt = 0;

		while (cnt < 2) {
			cnt = 0;
			visited = new boolean[N][M];

			for (int i = 1; i < map.length - 1; i++) {
				for (int j = 1; j < map[i].length - 1; j++) {
					if (visited[i][j])
						continue;
					if (map[i][j] != 0) {
						cnt++;
						BFS(i, j);
					}
				}
			}
			if (cnt == 0) {
				answer = 0;
				break;
			} else if (cnt < 2) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	private static void BFS(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] isNotZero = new boolean[N][M];
		queue.offer(new Node(i, j, map[i][j]));
		visited[i][j] = true;
		isNotZero[i][j] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int d = 0; d < delta.length; d++) {
				int next_x = now.x + delta[d][0];
				int next_y = now.y + delta[d][1];

				if (map[next_x][next_y] == 0 && !isNotZero[next_x][next_y] && map[now.x][now.y] > 0)
					map[now.x][now.y]--;

				if (visited[next_x][next_y])
					continue;

				if (map[next_x][next_y] != 0) {
					queue.offer(new Node(next_x, next_y, map[next_x][next_y]));
					visited[next_x][next_y] = true;
					isNotZero[next_x][next_y] = true;
				}

			}
		}

	}

}
