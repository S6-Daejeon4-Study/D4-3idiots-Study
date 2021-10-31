package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B2160_마법사상어와비바라기_이예나 {

	static int N, M;
	static int[][] arr; //물의 양
	static int[][] cloud; //구름 위치 여부 체크해주는 뱌열
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 }; // 명령에 따른 방향
	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] my = { -1, -1, 1, 1 }; // 대각선 방향
	static int[] mx = { -1, 1, -1, 1 };

	static class Node { // 구름 위치를 저장하기 위한 클래스
		int y;
		int x;

		Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static List<Node> clouds; // 구름의 위치를 저장한 리스트

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		cloud = new int[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		init(); //처음 구름 위치 4개 리스트에 넣어줌
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			move(d, s);
			makeCloud();
		}
		System.out.println(makeSum());

	}

	private static void init() { //처음 구름 위치 생성해주는 메서드
		clouds = new ArrayList<>();

		cloud[N - 1][0] = 1;
		clouds.add(new Node(N - 1, 0));
		cloud[N - 1][1] = 1;
		clouds.add(new Node(N - 1, 1));
		cloud[N - 2][0] = 1;
		clouds.add(new Node(N - 2, 0));
		cloud[N - 2][1] = 1;
		clouds.add(new Node(N - 2, 1));
	}

	private static void move(int d, int s) { //각 구름의 위치를 이동시키는 메서드
		List<Node> newClouds = new ArrayList<>(); //새로운 구름 위치 리스트
		for (int l = 0; l < clouds.size(); l++) { //기존 구름마다 이동
			Node cs = clouds.get(l);

			int sy = cs.y;
			int sx = cs.x;
			for (int k = 0; k < s; k++) { //인덱스를 넘어가도 다시 돌아올 수 있게함
				sy += dy[d];
				sx += dx[d];
				if (sy == N) //N이면 시작 위치로
					sy = 0;
				else if (sy == -1) //-1이면 마지막 위치로
					sy = N - 1; 
				if (sx == N)
					sx = 0;
				else if (sx == -1)
					sx = N - 1;
			}
			cloud[cs.y][cs.x] = 0; //이동해서 구름이 없어졌으므로 되돌림
			arr[sy][sx] += 1; //새로운 구름에 비가 내려서 1증가
			newClouds.add(new Node(sy, sx)); //새로운 구름 리스트에 추가

		}

		clouds.clear(); //모든 기존 구름이 다 이동했으면 지워주고
		for (Node tc : newClouds) { //새로운 구름리스트를 넣어준다
			clouds.add(new Node(tc.y, tc.x));
		}
		for (int i = 0; i < clouds.size(); i++) { //각 구름 위치에서 copy magic 수행
			Node tempC = clouds.get(i);
			copyMagic(tempC.y, tempC.x);
		}
	}

	private static void copyMagic(int y, int x) { //copy magic를 하는 메서드

		int cnt = 0;
		for (int i = 0; i < 4; i++) { //대각선의 값들을 확인
			int ay = y + my[i];
			int ax = x + mx[i];
			if (!isIn(ay, ax))
				continue;
			if (arr[ay][ax] >= 1) //1이상인 이웃을 찾으면
				cnt++; //cnt를 증가시킴
		} 
		arr[y][x] += cnt; //cnt만큼 증가시킴
		cloud[y][x] = 1; //구름 위치 표시
	}

	private static boolean isIn(int y, int x) { //인덱스를 벗어나는지 확인하는 메서드
		if (y > N || y < 0 || x > N || x < 0)
			return false;
		return true;
	}

	private static void makeCloud() { //새로운 구름을 만드는 메서드
		clouds.clear(); //기존 구름 없엠
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cloud[i][j] == 1) {//구름 표시되어있으면 되돌림
					cloud[i][j] = 0;
					continue;
				} else { //구름이 아니었고 값이 2이상인곳을 찾음

					if (arr[i][j] >= 2) {
						arr[i][j] -= 2;
						cloud[i][j] = 1;
						clouds.add(new Node(i, j));
					}
				}
			}
		}

	}

	private static int makeSum() { //최종 답을 위해 각 물의양을 더하는 메서드
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += arr[i][j];
			}
		}
		return ans;
	}

}
