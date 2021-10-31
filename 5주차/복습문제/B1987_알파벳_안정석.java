package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_알파벳 {

	static int M, N;
	static char map[][];
	static int maxcnt; // cnt와 비교해서 가장 최대의 cnt를 저장
	static int dx[] = { -1, 1, 0, 0 }; // 상하좌우 델타 배열
	static int dy[] = { 0, 0, -1, 1 };
	static boolean[] alpha; // 알파벳을 사용했는지 안했는지 판별하기 위한 boolean 배열 선언

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[M][];
		alpha = new boolean[26];

		for (int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
		}

		move(0, 0, 1); // 첫번째는 무조건 새로운 알파벳이기 때문에 cnt를 1로 시작

		System.out.println(maxcnt);

	}

	static void move(int x, int y, int cnt) {  // 재귀함수 move 선언

		alpha[map[x][y] - 'A'] = true;  // max[x][y] 값이 char기 때문에 대문자 'A'를 빼줘서 0~26의 숫자로 만들어준다.

		for (int d = 0; d < 4; d++) {  //  4방탐색 시작

			int nx = x + dx[d];  // nx,ny를 이용해 좌표값 갱신
			int ny = y + dy[d];

			if (nx >= 0 && nx < M && ny >= 0 && ny < N && !alpha[map[nx][ny] - 'A']) {  // 좌표값의 범위와 && 를 통해 다음 nx,ny 위치의 값이 false 인지 판별하고 false 이면 이동
//				cnt++; 이건 안된다!! 재귀를 돌고 돌아왔을때 남은 방향의 cnt값을 추가시킬 수 있다.
				move(nx, ny, cnt+1); 
			}
		}
		alpha[map[x][y] - 'A'] = false;  // 갈곳이 모두 없어지고 반복문이 모두 돌아서 끝나면 true 값을 false로 바꿔서 return해서 다시 탐색할때 겹치지 않게 해준다. 
		maxcnt = Math.max(maxcnt, cnt);  // 마지막에 cnt값을 비교해줘서 큰값을 maxcnt에 저장
		
	}

}
