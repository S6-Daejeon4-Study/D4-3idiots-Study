package silver.lv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test2468 {
	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Area implements Comparable<Area> {
		int i, j, h;

		public Area(int i, int j, int h) {
			this.i = i;
			this.j = j;
			this.h = h;
		}

		@Override
		public int compareTo(Area o) {
			return h - o.h;
		}
	}

	static int N;
	static boolean[][] map;
	static PriorityQueue<Area> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		pq = new PriorityQueue<>();
		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				int h = Integer.parseInt(st.nextToken());
				map[i][j] = true;
				pq.add(new Area(i, j, h));
			}
		} // input end

		int max = 1;
		int pre = -1;
		while (!pq.isEmpty()) {
			Area area = pq.poll();

			if (pre != -1 && area.h != pre) {
				max = Math.max(max, calcArea());
			}
			map[area.i][area.j] = false;
			pre = area.h;
		}
		max = Math.max(max, calcArea());

		System.out.println(max);
	}

	private static int calcArea() {
		int cnt = 0;
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] && !visited[i][j]) {
					BFS(i, j, visited);
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void BFS(int i, int j, boolean[][] visited) {
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(i, j));
		visited[i][j] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int d = 0; d < delta.length; d++) {
				int x = p.x + delta[d][0];
				int y = p.y + delta[d][1];

				if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || !map[x][y])
					continue;
				queue.offer(new Point(x, y));
				visited[x][y] = true;
			}
		}
	}

}
