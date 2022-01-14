import java.io.*;
import java.util.*;

public class Main_바이러스_2606 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N,M,cnt=0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        boolean arr[][] = new boolean[N+1][N+1];
        boolean visited[] = new boolean[N+1];

        for(int i=1;i<=M;i++){

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = true;
            arr[b][a] = true;
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);

        while(!queue.isEmpty()){
            int node = queue.poll();
            visited[node] = true;

            for(int i=1;i<=N;i++) {
                if (arr[node][i] && !visited[i]) {
                    queue.offer(i);
                    cnt++;
                    visited[i] = true;
                }
            }
        }

        System.out.println(cnt);

    }

}
