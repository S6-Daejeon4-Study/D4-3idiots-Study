package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9372_상근이의여행_이예나 {
	static int parents[];
	static boolean union(int a, int b) {
		if(find(a)!=find(b)) {
			parents[find(a)]=find(b);
			return true;
		}
		return false;
	}
	static int find(int a) {
		if(parents[a]==a)
			return a;
		return find(parents[a]);
		
	}
	static void make(int n) {
		for(int i=0;i<=n;i++) {
			parents[i]=i;
		}
	}
	public static void main(String[] args) throws IOException{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(bf.readLine());
		for(int t=0;t<test;t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int cnt=0;
			parents = new int[N+1];
			make(N);
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(union(a,b))cnt++;
				
				
			}

			System.out.println(cnt);
		}
		
		
		
	}

}
