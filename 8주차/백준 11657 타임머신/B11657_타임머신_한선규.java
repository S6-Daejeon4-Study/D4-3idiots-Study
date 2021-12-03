import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 12. 3
 *
 * 백준 11657 타임머신 - https://www.acmicpc.net/problem/11657
 *
 * 음의 가중치를 포함한 유향그래프에서 최단경로를 찾는 알고리즘인 벨먼포드 알고리즘을 이용해서 풀어야 하는 문제.
 * 벨먼포드 알고리즘에 대한 이해가 부족해서 해당 알고리즘을 먼저 학습한 뒤에 이 문제를 풀었음.
 * 첫번째 시도에서 dist 배열의 타입을 int 로 해서 (오버플러우를 예상 못함) 출력초과 오류가 났음.
 * 두번째 시도에서 dist 배열의 타입을 long 으로 바꿔서 통과!!!!
 *
 */
public class B11657_타임머신_한선규 {

    private static int N, M; // N: 도시 개수, M: 버스 노선 개수
    private static Bus[] bus; // 버스 노선 정보
    private static long[] dist; // 1번 도시에서 출발해서 각 도시까지 걸리는 최단시간 배열
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N+1];
        bus = new Bus[M];

        for (int i = 0; i < M; i++) { // 버스 노선 정보 저장
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            bus[i] = new Bus(A, B, C);
        }

        if (bellmanFord()) { // 음수 사이클이 존재하지 않는다면
            for (int i = 2; i <= N; i++) { // 각 도시까지 도착하는데 최단시간을 출력
                if (dist[i] == INF) { // 해당 도시로 가는 노선이 존재하지 않는 다면
                    System.out.println(-1);
                } else {
                    System.out.println(dist[i]);
                }
            }
        } else { // 음수 사이클이 존재 한다면
            System.out.println(-1);
        }
    }

    /**
     * 음의 가중치를 포함한 유향그래프에서 최단경로를 찾는 벨먼포드 알고리즘
     * #1. 경로 배열 INF 로 초기화
     * #2. 시작 정점 dist는 0으로 초기화
     * #3. 경로 업데이트 작업
     *
     * @return (true: 음수 사이클 O, false: 음수 싸이클 X)
     */
    private static boolean bellmanFord() {
        // #1. 경로 배열 INF 로 초기화
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

        // #2. 시작 정점 dist 0으로 초기화
        dist[1] = 0; // 1번 도시부터 출발하는 경로를 계산하기 위해서

        // #3. 경로 업데이트 작업
        for (int i = 0; i < N; i++) { // N 번 반복
            for (int j = 0; j < M; j++) { // 각각의 반복마다 모든 버스 노선정보를 확인
                int start = bus[j].s;
                int end = bus[j].e;
                int time = bus[j].t;

                // start 경로가 아직 업데이트 되지않았거나 존재 하지 않는다면 continue
                if (dist[start] == INF)
                    continue;

                // 현재까지 파악한 end 까지 가는 시간이 start 를 거쳐서 end 까지 가는 시간보다 오래걸린다면 업데이트.
                if (dist[end] > dist[start] + time) {
                    dist[end] = dist[start] + time;

                    // 음수 사이클 판단
                    // N개의 도시에대해서 나올 수 있는 최대 경로의수는 N-1 이다.
                    // N번째 반복에서 경로의 업데이트가 일어난다면 음의 사이클이 존재하는 것.
                    if (i == N-1) return false;
                }
            }
        }

        return true;
    }
}

class Bus {
    int s; // 시작 도시
    int e; // 도착 도시
    int t; // 걸리는 시간

    public Bus(int s, int e, int t) {
        this.s = s;
        this.e = e;
        this.t = t;
    }
}
