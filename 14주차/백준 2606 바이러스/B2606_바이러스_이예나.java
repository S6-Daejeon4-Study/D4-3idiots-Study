import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B2606_바이러스_이예나 {

	static List<Integer>[] numbers;
	public static void main(String[] args) throws IOException {
		
		int n;
		int line;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		line = Integer.parseInt(bf.readLine());
		numbers = new List[n+2];
		visited =new boolean[n+2];
		cnt=0;
		for(int i=0;i<=n;i++) {
			numbers[i] = new ArrayList();
		}
		for(int i=0;i<line;i++) {
			
			int a,b;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			numbers[a].add(b);
			numbers[b].add(a);
		}
		dfs(1);
		visited[1]=true;
		for(int i=1;i<=n;i++) {
			if(visited[i]) {
				cnt++;
			}
		}
		System.out.println(cnt-1);
		

	}
	static boolean[] visited;
	static int cnt;
	static void dfs(int start) {
		
		for(int i=0;i<numbers[start].size();i++) {
			if(visited[numbers[start].get(i)]) continue;
			visited[numbers[start].get(i)] =true;
			dfs(numbers[start].get(i));
		}
	}

}