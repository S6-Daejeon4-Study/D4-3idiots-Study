package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1504_특정한최단경로_이예나 {

	static int N,E;
	static long [] dis;
	static boolean [] visited;
	static int[][]map;
	static int m1,m2;
	static long ans;
	static long ans1,ans2;
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		map= new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=0;j<=N;j++) {
			map[i][j] = Integer.MAX_VALUE;
			map[j][i] = Integer.MAX_VALUE;
		}
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(bf.readLine());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[f][s] = d;
			map[s][f] = d;
		}
		st = new StringTokenizer(bf.readLine());
		m1 = Integer.parseInt(st.nextToken());
		m2 = Integer.parseInt(st.nextToken());
		
		makeAns();
	}
	public static void makeAns() {
		
	 long ans1=0,ans2=0;
	 long ans=0;
		
		ans1+=Dijkstra(1, m1);
		ans1+=Dijkstra(m1, m2);
		ans1+=Dijkstra(m2, N);
		
		ans2+=Dijkstra(1, m2);
		ans2+=Dijkstra(m2, m1);
		ans2+=Dijkstra(m1, N);
		
		ans = Math.min(ans2, ans1);
		if(ans<=0) ans=-1;
		System.out.println(ans);
	}

	
	public static long Dijkstra(int start, int end) {
		dis = new long [N+1];
		visited = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			if(map[start][i]!=0) 
				dis[i] = map[start][i];
			else
				dis[i] = Integer.MAX_VALUE;
			
		}
		dis[start]=0;
		visited[start]=true;
		//탐색 시작
		for(int i=1;i<N;i++) {
			int minEdge=-1;
			long minLen = Integer.MAX_VALUE;
			for(int e=1;e<=N;e++) {
				if(visited[e]) continue;
				if(dis[e]<minLen ) {
					minEdge = e;
					minLen = dis[e];
				}
			}
			if(minEdge==-1) {
				return -1;
			}
			visited[minEdge] = true; // 최소 거리의 노드 찾음
			for(int j=1;j<=N;j++) {
				if(!visited[j] && dis[j] > dis[minEdge]+map[minEdge][j]) {
					dis[j]=dis[minEdge]+ map[minEdge][j];				
				}
			}
			
		}
			
			return dis[end];
	}


}
