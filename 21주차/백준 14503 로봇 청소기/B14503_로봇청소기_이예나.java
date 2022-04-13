package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14503_로봇청소기_이예나 {

	static int N,M;
	static int dy[]= {-1,0,1,0};
	static int dx[]= {0,1,0,-1};
	static int [][]map;
	static boolean [][] visited;
	static int count;
	static int[][]tmp;
	public static void main(String[] args) throws IOException {
	
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		count=0;
		map=new int[N+1][M+1];
		tmp=new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		st = new StringTokenizer(bf.readLine());
		int startY=Integer.parseInt(st.nextToken());
		int startX=Integer.parseInt(st.nextToken());
		int direction=Integer.parseInt(st.nextToken());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			int k=0;
			while(st.hasMoreTokens()){
				map[i][k]=Integer.parseInt(st.nextToken());
				k++;
				
			}
		}
		move(startY,startX,direction,0);

		System.out.println(count+1);
		
	}
	
	static void move(int y, int x, int d, int cnt) {
		visited[y][x]=true;
		int ny=0;
		int nx=0;
		int nd = changeDir(d);
		int bx=0;
		int by=0;
		

		if(nd==0) {//d=1
			ny = y-1;
			nx=x;
			bx=x-1;
			by=y;
			
		}
		else if(nd==1) {//d=2
			ny=y;
			nx=x+1;
			by=y-1;
			bx=x;
		}
		else if(nd==2) {//d=3
			ny=y+1;
			nx=x;
			bx=x+1;
			by=y;
		}
		else {//d==0
			ny=y;
			nx=x-1;
			bx=x;
			by=y+1;

		}
		
		if(cnt==4) {
			if(isIn(by,bx) && map[by][bx]==0) {
				
				if( !visited[by][bx]) {
					tmp[by][bx]=count;
					count++;
					visited[by][bx]=true;
				}
				
				move(by,bx,d,0);
			}
				return;
		}
		
		
		if(map[ny][nx]==0&&!visited[ny][nx]) {
			tmp[ny][nx]=count;
			count++;
			visited[ny][nx]=true;
			move(ny,nx,nd,0);
			return;
		}
		
		
		move(y,x,nd,cnt+1);
		return;
			
		
		
		
		
	}
	static int changeDir(int d) {
		if(d==0) return 3;
		return d-1;
	}
	
	static boolean isIn(int y, int x) {
		if(y<0 || x<0 || y>=N || x>=M)
			return false;
		return true;
	}
	
	
	
	
	
	

}
