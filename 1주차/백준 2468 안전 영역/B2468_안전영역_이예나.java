package day0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2468_안전영역_이예나 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int maxCnt;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int maxH;

	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int nowH;

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N + 1][N + 1];
		maxH = 0;
		nowH = 0;
		for (int i = 0; i < N; i++) {
			int j = 0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			while (st.hasMoreTokens()) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, arr[i][j]);
//				System.out.print(arr[i][j] + " ");
				j++;
			}
		}
//		System.out.println("max:" + maxH);

		for (int h = 0; h <= maxH; h++) {
			visited = new boolean[N + 1][N + 1];
			nowH = h;
			int cnt = 0;
//			System.out.println("높이: " + nowH);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && arr[i][j] > nowH) {
//						System.out.println(i + " " + j + " 시작");
						bfs(i, j);
						cnt++;
					}
				}
			}
//			System.out.println("개수:" + cnt);
			maxCnt = Math.max(maxCnt, cnt);

		}

		System.out.println(maxCnt);
	}

	private static void bfs(int y, int x) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(y, x));
		visited[y][x] = true;
//		System.out.println("nowH: " + nowH);
//		System.out.println(y + " " + x);
		while (!queue.isEmpty()) {
			Node front = queue.poll();
//			System.out.println("front" + front.y + " " + front.x);
			for (int d = 0; d < 4; d++) {
				int ny = front.y + dy[d];
				int nx = front.x + dx[d];
				if (!isIn(ny, nx))
					continue;
				if (arr[ny][nx] <= nowH)
					continue;
				if (visited[ny][nx])
					continue;

				visited[ny][nx] = true;
//				System.out.println("next:" + ny + " " + nx);
				queue.add(new Node(ny, nx));

			}
		}

	}

	private static boolean isIn(int y, int x) {
		if (y < 0 || x < 0 || x >= N || y >= N)
			return false;
		return true;
	}

}
