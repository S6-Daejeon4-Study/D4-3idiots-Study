import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1197_Main {

    static ArrayList<Edge>[] lists;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int V, E;
    static long sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visited = new boolean[V + 1];

        lists = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무방향 연결 정보 저장
            lists[start].add(new Edge(end, weight));
            lists[end].add(new Edge(start, weight));
        }

        prim();

        System.out.println(sum);
    }

    private static void prim() {
        // 1번 정점부터 시작
        pq.add(new Edge(1, 0));
        int cnt = 0; // V개의 정점을 탐색했는지 계산하기 위한 cnt 변수
        while(!pq.isEmpty()) {
            // 현재 pq에 있는 간선들 중에서 가장 작은 가중치를 가지는 간선정보를 poll
            Edge cur = pq.poll();

            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;
            sum += cur.weight;
            cnt++;

            // 선택한 정점으로부터 인접한 간선들을 pq에 넣어준다.
            for (Edge next : lists[cur.vertex]) {
                if (!visited[next.vertex]) {
                    pq.add(next);
                }
            }
            
            // V개의 정점에 대해서 모두 탐색이 완료되었다면 반복문을 빠져나온다.
            if (cnt == V) break;
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}

