package day1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//////////////////// 나중에 다시 풀어보기 //////////////////////
public class BaekJoon_마법사_상어와_비바라기 {
	
	static int N, M;
	static int d,s;
	static int[][] map;
	static ArrayList<Point> cloud;
	static boolean[][] check;
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud = new ArrayList<Point>();
		cloud.add(new Point(N - 1, 0));
		cloud.add(new Point(N - 1, 1));
		cloud.add(new Point(N - 2, 0));
		cloud.add(new Point(N - 2, 1));
		
		for (int i = 0; i < M; i++) { // M번 만큼 명령 실행
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			// 구름을 이동시키는데 도대체 대각선 구름이 나가면 어떤식으로 넘어가는지? 위아래 양옆만 갈 수 있는거 같은데 문제에선 대각선도 이동하라고 한다 질문.
			
			// 이동이 끝나면 물을 1씩 증가시켜주고 구름의 좌표를 true(방문처리)해준다. 구름은 사라지지만 다시 구름을 생성할 때 이 구름들의 자리에는 구름이 생길 수 없기때문.
			
			// 1씩 더해준 이후에 물복사 버그 시전 -> 대각선을 체크 (대각선은 1,3,5,7로 홀수번을 돌면 8방 배열로도 for문 가능)하고 x,y에 조건에 맞는 물을 더해주고 마지막으로 구름 삭제.
			
			// 새로운 구름 생성 (맵을 완탐하여 바구니에 물이 2 이상인 애들로 구름을 더해주고 check배열에 true인 애들은 추가하지 않고 넘어간다)하고 물의 값을 -2 해준다.
			
		
		
		
		}
	}
}
