package day0926;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1325_효율적인해킹_이예나 {

	static int maxCnt;
	static List<Integer> maxList;
	static List<Integer>[] numList;
	static int N, M;
	static int[] cnts;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		BufferedWriter bw = new  BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numList = new List[N + 1];
		maxCnt = 0;
		maxList = new ArrayList<>();
		cnts = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			numList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int addN = Integer.parseInt(st.nextToken());
			int base = Integer.parseInt(st.nextToken());
			numList[base].add(addN);

		}
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			bfs(i);
		}
		
		StringBuffer sb = new StringBuffer();
		for (int c=1;c<=N;c++) {
			if(cnts[c]==maxCnt) {
				sb.append(c+" ");	
			}
		}
		System.out.println(sb.toString());

	}

	static void bfs(int base) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		visited[base] = true;
		queue.add(base);
		while (!queue.isEmpty()) {

			int front = queue.poll();
			for (int e : numList[front]) {
				if (visited[e])
					continue;
				visited[e] = true;
				queue.add(e);
				cnt++;
			}

		}
		cnts[base] = cnt;
		maxCnt=Math.max(maxCnt, cnt);

	}

}