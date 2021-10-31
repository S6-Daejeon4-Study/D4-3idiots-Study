package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author comkkyu 
 * @since 21. 10. 31
 * 
 * @see MST, 크루스칼 알고리즘 복습
 */
public class B17472_다리만들기2_한선규 {
    private static int[][] map;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int[] parents;
    private static PriorityQueue<Bridge> pq;
    private static int N, M, islandCnt, ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // #1. 정점 만들기
        makeIslandNumber();
        // #2. 간선 정보 만들기
        makeBridge();
        // #3. MST 만들기
        kruskal();

        System.out.println(ans);
    }

    public static void makeBridge() {
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) { // 해당 좌표가 섬이라면 이제 다리를 놓을 수 있는지 (간선을 만들수 있는지) 확인해보자!
                    int now = map[i][j];

                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];

                        if (isInside(ni, nj) && map[ni][nj] == 0) { // 인접한 부분이 바다라면! 다른 섬만날때까지 지금 방향으로 쭉가보는거야!
                            while (true) {
                                ni += dx[d];
                                nj += dy[d];

                                if (!isInside(ni, nj)) break; // 배열 밖에 벗어나면 패스~
                                if (map[ni][nj] == now) break; // 같은 섬이면 패스~
                                if (map[ni][nj] != 0 && map[ni][nj] != now) { // 내가 아닌 !! 다른 섬을 만난거다!
                                    int dist = getDistance(i, j, ni, nj); // 섬(i,j) 와 섬(ni,nj) 를 잇는 다리의 길이

                                    if (dist < 2) break;

                                    pq.add(new Bridge(map[i][j], map[ni][nj], dist)); // 해당 간선 정보를 pq 에 추가하자!
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void makeIslandNumber() {
        Queue<Island> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    islandCnt++;
                    visited[i][j] = true;
                    map[i][j] = islandCnt;
                    q.offer(new Island(i, j));

                    while (!q.isEmpty()) {
                        Island cur = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int nextX = cur.x + dx[d];
                            int nextY = cur.y + dy[d];

                            if (isInside(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                                map[nextX][nextY] = islandCnt;
                                q.offer(new Island(nextX, nextY));
                                visited[nextX][nextY] = true;
                            }
                        }
                    }
                }
            }
        }

        parents = new int[islandCnt + 1];
    }

    public static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) - 1;
    }

    public static void kruskal() {
        int cnt = 0;
        int sum = 0;

        for (int i = 1; i <= islandCnt; i++) { // 모든 섬을 자신을 대표자로
            parents[i] = i;
        }

        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();

            if (union(bridge.from, bridge.to)) {
                sum += bridge.dist;
                if (++cnt == islandCnt - 1)
                    break;
            }
        }

        ans = (cnt != islandCnt-1) ? -1 : Math.min(sum, ans);
    }

    public static int find(int a) { // 서로소 집합 find 연산. a 의 대표자를 찾는 메서드
        if (a == parents[a])
            return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) { // a 와 b 를 하나의 집합으로 합쳐주는 메서드
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            parents[aRoot] = bRoot;
            return true;
        }

        return false;
    }

    static class Island { // 섬 정보 (정점)
        int x;
        int y;

        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Bridge implements Comparable<Bridge> { // 다리 정보 (간선)
        int from; // 출발 섬 번호
        int to; // 도착 섬 번호
        int dist; // 다리 길이

        public Bridge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Bridge o) { // 다리 길이 짧은 순서로 오름차순 정렬조건 추가
            return this.dist - o.dist;
        }
    }
}
