package gold.lv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 구슬이 변화하는 단계에서 막혔음
 * 다음 주차에서 보완할 예정
 *
 */

public class Test21611 {
	public static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, start, one, two, three;
	static int[][] marble;
	static int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = (N + 1) / 2;
		marble = new int[N + 1][N + 1];
		for (int i = 1; i < marble.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < marble[i].length; j++) {
				marble[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			magic(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
//			print();
			checkBombable();
//			print();
			checkMoveable();
//			print();
			checkChangeable();
//			print();
		}
		// input end
		int answer = one + 2 * two + 3 * three;
		System.out.println(answer);

	} // main end

	private static void print() {
		System.out.println("===============================");
		for (int i = 1; i < marble.length; i++) {
			for (int j = 1; j < marble[i].length; j++) {
				System.out.print(marble[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void checkChangeable() {
		Point point = new Point(start, start); // 시작 좌표
		LinkedList<Point> list = new LinkedList<>();

		for (int i = 1; i < N; i++) { // N-1까지
			if (i % 2 == 0) { // 짝수번째면
				point = changeMarble(i, 3, point, list); // 오른쪽으로
				point = changeMarble(i, 0, point, list); // 위쪽으로
			} else { // 홀수번째면
				point = changeMarble(i, 2, point, list); // 왼쪽으로
				point = changeMarble(i, 1, point, list); // 아래쪽으로
			}
		}
		changeMarble(N - 1, 2, point, list); // N-1 만큼 왼쪽으로 한번 더
		makeMarble(list);
	}

	private static void makeMarble(LinkedList<Point> list) {

	}

	private static Point changeMarble(int i, int d, Point point, LinkedList<Point> list) {
		int x = point.x;
		int y = point.y;
		int c = 0;
		int pre = 0;

		if (list.size() > 0) {
			Point p = list.removeLast();
			c = p.x;
			pre = p.y;
		}

		for (int j = 0; j < i; j++) { // i번 반복
			x += direction[d][0];
			y += direction[d][1];

			if (marble[x][y] == 0)
				continue;

			if (pre != 0) {
				if (pre != marble[x][y]) { // 전에 구슬과 지금 구슬이 다르면
					list.add(new Point(c, pre)); // 구슬의 개수, 그룹을 이루고 있는 구슬의 번호
					c = 0;
				}
			}
			pre = marble[x][y];
			c++;
		}
		list.add(new Point(c, pre));

		return new Point(x, y);
	}

	/**
	 * 폭발할 구슬을 탐색하는 메소드
	 */
	private static void checkBombable() {
		Point point = new Point(start, start); // 시작 좌표
		Stack<Point> stack = new Stack<>();
		Stack<Integer> cnt = new Stack<>();

		for (int i = 1; i < N; i++) { // N-1까지
			if (i % 2 == 0) { // 짝수번째면
				point = bombMarble(i, 3, point, stack, cnt); // 오른쪽으로
				point = bombMarble(i, 0, point, stack, cnt); // 위쪽으로
			} else { // 홀수번째면
				point = bombMarble(i, 2, point, stack, cnt); // 왼쪽으로
				point = bombMarble(i, 1, point, stack, cnt); // 아래쪽으로
			}
		}
		bombMarble(N - 1, 2, point, stack, cnt); // N-1 만큼 왼쪽으로 한번 더

	}

	/**
	 * 구슬을 폭발시키는 메소드
	 * 
	 * @param i     반복 횟수
	 * @param d     방향
	 * @param point 탐색 좌표
	 * @param stack 탐색한 구슬들 좌표
	 * @param cnt   연속되는 구슬 개수
	 * @return 탐색 좌표
	 */
	private static Point bombMarble(int i, int d, Point point, Stack<Point> stack, Stack<Integer> cnt) {
		int x = point.x;
		int y = point.y;
		int c = 0;

		if (!cnt.empty()) {
			c = cnt.pop();
		}

		for (int j = 0; j < i; j++) { // i번 반복
			x += direction[d][0];
			y += direction[d][1];

			if (marble[x][y] == 0)
				continue;

			if (stack.size() > 0) {
				Point top = stack.peek();
				if (marble[top.x][top.y] != marble[x][y] && c >= 4) { // 전에 구슬과 지금 구슬이 다르고 연속된 구슬이 4개 이상이면
					// 폭발하는 구슬의 개수 더하기
					switch (marble[top.x][top.y]) {
					case 1:
						one += c;
						break;
					case 2:
						two += c;
						break;
					case 3:
						three += c;
						break;
					}
					// 연속된 좌표들 폭발
					while (c-- > 0) {
						Point p = stack.pop();
						marble[p.x][p.y] = 0;
//						c--;
					}

					if (stack.size() > 0) {
						top = stack.peek();
						if (marble[top.x][top.y] == marble[x][y]) { // 폭발 후 남은 가장 전의 구슬과 지금 구슬이 같으면
							c = cnt.pop();
						}
					}
				} else if (marble[top.x][top.y] != marble[x][y]) { // 전에 구슬과 다른데 4개 이상도 아니면
					cnt.push(c); // 혹시 모르니까 저장
					c = 0;
				}
			}
			stack.add(new Point(x, y)); // 일단 저장
			c++;
		}
		cnt.push(c);

		return new Point(x, y);

	}

	/**
	 * 마법 시전 후 구슬을 이동시키기 위해 이동 가능성을 확인하는 메서드
	 */
	private static void checkMoveable() {
		Point point = new Point(start, start); // 시작 좌표
		LinkedList<Point> empty = new LinkedList<>();

		for (int i = 1; i < N; i++) { // N-1까지
			if (i % 2 == 0) { // 짝수번째면
				point = moveMarble(i, 3, point, empty); // 오른쪽으로
				point = moveMarble(i, 0, point, empty); // 위쪽으로
			} else { // 홀수번째면
				point = moveMarble(i, 2, point, empty); // 왼쪽으로
				point = moveMarble(i, 1, point, empty); // 아래쪽으로
			}
		}
		moveMarble(N - 1, 2, point, empty); // N-1 만큼 왼쪽으로 한번 더
	}

	/**
	 * 구슬을 이동시키는 메서드
	 * 
	 * @param i     반복 횟수
	 * @param d     탐색 방향
	 * @param point 탐색 좌표
	 * @param empty 빈 공간 좌표가 저장되어 있는 큐
	 * @return 탐색 좌표
	 */
	private static Point moveMarble(int i, int d, Point point, LinkedList<Point> empty) {
		int x = point.x;
		int y = point.y;

		for (int j = 0; j < i; j++) { // i번 반복
			x += direction[d][0];
			y += direction[d][1];

			if (marble[x][y] == 0) { // 구슬이 비었으면 빈 공간 좌표 저장
				empty.offer(new Point(x, y));
			} else if (empty.size() > 0) { // 구슬이 비어있지 않은데 빈 공간 좌표가 저장되어 있으면
				Point p = empty.poll();
				marble[p.x][p.y] = marble[x][y]; // 빈 곳에 구슬을 넣고
				marble[x][y] = 0; // 옮긴 곳은 빈 곳으로
				empty.offer(new Point(x, y)); // 빈 곳 좌표 저장
			}
		}
		return new Point(x, y);
	}

	/**
	 * 상어가 마법을 시전하는 메서드
	 * 
	 * @param d 방향
	 * @param s 거리
	 */
	private static void magic(int d, int s) {
		// 상어 위치
		int x = start;
		int y = start;

		for (int i = 0; i < s; i++) { // 거리만큼
			x += direction[d][0];
			y += direction[d][1];

			marble[x][y] = 0; // 구슬 파괴
		}
	}

}
