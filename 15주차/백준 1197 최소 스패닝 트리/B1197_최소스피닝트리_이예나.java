package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1197_최소스피닝트리_이예나 {

	static int parents[];

	static void make(int n) {
		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return find(parents[a]);
	}

	static boolean union(int a, int b) {
		if (find(a) != find(b)) {
			parents[find(a)] = find(b);
			return true;
		}
		return false;

	}

	static class Line implements Comparable<Line> {
		int a;
		int b;
		int value;

		Line(int a, int b, int v) {
			this.a = a;
			this.b = b;
			this.value = v;
		}

		@Override
		public int compareTo(Line o) {
			if (this.value == o.value) {
				if (this.a == o.a) {
					return this.b - o.b;
				}
				return this.a - o.a;

			}

			return this.value - o.value;

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<Line> lines = new ArrayList<>();
		parents = new int [V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			lines.add(new Line(a, b, c));
		}
		make(V);
		Collections.sort(lines);
		long sum=0;
		for(int i=0;i<lines.size();i++) {
			Line oneLine = lines.get(i);
			if(union(oneLine.a,oneLine.b)) {
				sum+=oneLine.value;
			}
		}
		System.out.println(sum);
	}

}
