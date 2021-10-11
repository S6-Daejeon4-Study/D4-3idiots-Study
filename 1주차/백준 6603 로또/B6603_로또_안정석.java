package day1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_로또 {

	static int M;
	static int arr[];
	static int result[];
	static boolean check[];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken()); // 로또 번호 개수 저장

			if (M == 0) { // 입력 종료 조건
				break;
			}
			arr = new int[M]; // 로또 번호 저장 배열
			for (int i = 0; i < M; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			check = new boolean [M];
			comb(0,0);
			System.out.println();
		}
	}
	
	static void comb(int target, int cnt) {
		if(cnt == 6) {
			int cnt2 = 0;
			for(int i=0; i<M; i++) {
				if(check[i]) {
					System.out.print(arr[i]);
					++cnt2;
					if(cnt2 == 6) {
						System.out.println();
						break;
					}
					System.out.print(" ");
				}
			}
			return;
		}
		if(target == M) {
			return;
		}
		check[target] = true;
		comb(target+1, cnt+1);
		check[target] = false;
		comb(target+1, cnt);
		
	}

}
