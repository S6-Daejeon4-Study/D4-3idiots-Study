package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11657_타임머신_이예나 {

	static int N;
	static int M;

	static class Node {
		int start;
		int end;
		int length;

		Node(int s, int e, int l) {
			this.start = s;
			this.end = e;
			this.length = l;

		}
	}

	static Node[] map;
	static int[] bell;

	public static void main(String[] args) throws IOException {

			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new Node[M];
			bell = new int[N+1];
			for(int i=0;i<=N;i++) {
				bell[i] = Integer.MAX_VALUE;
			}
			
			
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(bf.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				
				map[i] = new Node(s,e,l);
				if(s==1 ) {
					bell[e] = Math.min(bell[e],l);
				}
				
			}
			bell[1]=0;
			
			
			
				
				if(bellman(1)) System.out.println("-1");
				else {
					
					for(int i=2;i<=N;i++) {
						if(bell[i]==Integer.MAX_VALUE) {
							System.out.println("-1");
						}else {
							System.out.println(bell[i]);
						}
					}
					
				}
				
			
		
	}

	static boolean bellman(int start) {

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (bell[map[j].start] == Integer.MAX_VALUE) {
					continue;
				} else {
					if (bell[map[j].start] + map[j].length  < bell[map[j].end]) {
						bell[map[j].end] = bell[map[j].start] + map[j].length;
						
						if(i==N-1) {
							return true;
						}
					}
				}
				
			}
		}
	
	return false;
	}
}
