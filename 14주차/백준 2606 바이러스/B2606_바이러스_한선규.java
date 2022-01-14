import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2606_Main {

    private static ArrayList<Integer>[] adjList;
    private static boolean[] visited;
    private static int N, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            adjList[src].add(dest);
            adjList[dest].add(src);
        }

        visited = new boolean[N + 1];

//        bfs();

        dfs(1);

        System.out.println(cnt);
    }

//    private static void bfs() {
//        Queue<Integer> q = new LinkedList<>();
//        q.offer(1);
//        visited[1] = true;
//
//        while (!q.isEmpty()) {
//            int cur = q.poll();
//            ArrayList<Integer> lists = adjList[cur];
//
//            for (int num : lists) {
//                if (!visited[num]) {
//                    q.offer(num);
//                    visited[num] = true;
//                    cnt++;
//                }
//            }
//        }
//    }

    private static void dfs(int cur) {
        visited[cur] = true;

        for (int next : adjList[cur]) {
            if (!visited[next]) {
                dfs(next);
                cnt++;
            }
        }
    }
}