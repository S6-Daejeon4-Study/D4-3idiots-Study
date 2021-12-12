import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 12. 12
 *
 * 백준 1504 특정한 최단 경로 - https://www.acmicpc.net/problem/1504
 *
 * 양의 가중치를 포함한 그래프에서 최단경로를 구하느 알고리즘인 다익스트라 알고리즘을 활용해서 해결
 * 문제에서 주어진 특정 조건인 임의의 서로 다른 두 정점을 필수로 방문해야 하는 조건을 충족시키기 위해서
 * 다익스트라 알고리즘을 나눠서 생각하면 된다.
 *
 * 예를 들어 임의의 두정점 v1, v2를 거쳐서 1번정점에서 N번 정점으로 가는 최단거리는 아래와 같이 두가지 경우가 있다.
 * (1) 1 -> v1 -> v2 -> N
 * (2) 1 -> v2 -> v1 -> N
 *
 * (1), (2) 중에서 더 작은 값을 최단거리로 결정하면 된다. 이때 그 값이 INF 보다 크다면 없는 경로이므로 -1을 출력하면 된다.
 */
public class B1504_특정한최단경로_한선규 {

    private static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    private static final int INF = 200000000;
    private static int N, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        // #1. 각 정점에 대한 간선정보 초기화
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<Node>());

        // #2. 입력값을 토대로 간선정보를 그래프에 저장
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            // 그래프가 무방향이기 때문에 양쪽 정점에 대해서 간선정보를 추가
            graph.get(src).add(new Node(dest, dist));
            graph.get(dest).add(new Node(src, dist));
        }

        // #3. 반드시 거쳐야 하는 두개의 서로 다른 정점 v1, v2 저장
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        bw.write(compareDist(v1, v2)+"");
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     *
     * @param v1 필수방문 정점1
     * @param v2 필수방문 정점2
     * @return v1, v2 를 방문하면서 1번 정점에서 N번 정점까지의 최단거리, 경로가 존재하지 않는다면 -1 반환
     */
    public static int compareDist(int v1, int v2) {
        int dist1 = 0;
        int dist2 = 0;

        // 1 -> v1 -> v2 -> N 순서로 방문했을 때 최단거리
        dist1 += dijkstra(1, v1);
        dist1 += dijkstra(v1, v2);
        dist1 += dijkstra(v2, N);

        // 1 -> v2 -> v1 -> N 순서로 방문했을 때 최단거리
        dist2 += dijkstra(1, v2);
        dist2 += dijkstra(v2, v1);
        dist2 += dijkstra(v1, N);

        // INF 보다 크거나 같다면 경로가 존재하지 않는 것이므로 -1 을 반환
        if (dist1 >= INF && dist2 >= INF) return -1;
        // 그게 아니라면 두 거리중에서 최소거리를 반환
        return Math.min(dist1, dist2);
    }

    /**
     * src 에서 출발해서 dest 에 도착하는 최단거리를 다익스트라 알고리즘으로 구하는 메서드
     *
     * @param src 출발정점
     * @param dest 도착정점
     * @return src -> dest 로가는 최단거리
     */
    public static int dijkstra(int src, int dest) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, INF);
        dist[src] = 0; // 출발정점 초기화

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, dist[src]));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int curVertex = node.dest;
            int curDist = node.weight;

            if (visited[curVertex])
                continue;

            visited[curVertex] = true;

            // 현재 정점으로 부터 이동가능한 간선 정보를 모두 탐색
            for (int i = 0; i < graph.get(curVertex).size(); i++) {
                int nextVertex = graph.get(curVertex).get(i).dest;
                int nextDist = graph.get(curVertex).get(i).weight;

                if (!visited[nextVertex] && dist[nextVertex] > curDist + nextDist) {
                    dist[nextVertex] = curDist + nextDist;
                    pq.add(new Node(nextVertex, dist[nextVertex]));
                }
            }
        }

        return dist[dest];
    }
}

class Node implements Comparable<Node> {
    int dest; // 도착 정점 번호
    int weight; // 해당 정점까지의 거리

    public Node(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
