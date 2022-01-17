package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2573_빙산_이예나 {

	static class Node {
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int ans;
	static int N, M;
	static boolean water = true;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };


	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int arr[][] = new int[N + 1][M + 1];
		ans = 0;
		for (int i = 0; i < N; i++) {
			int k = 0;
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][k] = Integer.parseInt(st.nextToken());
				k++;
			}
		}
		boolean[][] visited = new boolean[N + 1][M + 1];
		if (!countNum(arr, visited))
			melt(arr, 1);
		else {
			ans = 0;
		}
		System.out.println(ans);
	}


	static void melt(int[][] bef, int sec) { //녹이기
		int[][] aft = new int[N + 1][M + 1];//새로운 배열
		boolean[][] visited = new boolean[N + 1][M + 1];//방문 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cnt = 0;
				if (bef[i][j] != 0) {

					for (int d = 0; d < 4; d++) { 
						if (!isIn(i + dy[d], j + dx[d]))
							continue;
						if (bef[i + dy[d]][j + dx[d]] == 0)
							cnt++;
					}
					aft[i][j] = bef[i][j] - cnt; //주변 0개수만큼 빼줌
					if (aft[i][j] < 0)
						aft[i][j] = 0;
				}
			}
		}

		
		if (!countNum(aft, visited)) {
			melt(aft, sec + 1); //만약 아직도 한 덩어리이거나 다 녹지않으면 다시 녹이기
		} else {
			if (ans != 0) { // ans가 0이 아니면 덩어리가 아닌것임
				ans = sec; // 그때의 시간
			}
		}

	}

	static boolean countNum(int[][] arr, boolean[][] visited) {
		water = true;//다 녹았는지 체크
		int cnt = 0; // 덩어리 세기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (visited[i][j] || arr[i][j] == 0)
					continue;
				Queue<Node> queue = new LinkedList<>();
				queue.add(new Node(i, j));
				while (!queue.isEmpty()) {
					Node front = queue.poll();
					visited[front.y][front.x] = true;
					for (int d = 0; d < 4; d++) {
						int ny = front.y + dy[d];
						int nx = front.x + dx[d];
						if (!isIn(ny, nx) || visited[ny][nx] || arr[ny][nx] == 0)
							continue;
						visited[ny][nx] = true;
						queue.add(new Node(ny, nx));
						water = false; //bfs 돌면 다녹은거 아님
					}
				}
				cnt++;
			}
		}

		if (cnt > 1) { //덩어리수가 2개이상임
			ans = cnt;
			return true;
		}
		if (water) {//모두 녹음
			ans = 0;
			return true;
		}
		return false;
	}

	static boolean isIn(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= M)
			return false;
		return true;
	}

}
