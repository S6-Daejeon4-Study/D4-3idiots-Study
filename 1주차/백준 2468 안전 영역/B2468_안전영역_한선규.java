package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @date 21. 10. 3
 *
 * 백준 2468 - 안전 영역
 * https://www.acmicpc.net/problem/2468
 *
 * 장마철 비의 양보다 높은 건물들이 인접했을 때 안전영역이 된다.
 * 비의 양에 따라 안전영역의 개수가 최대일 때를 구하는 문제이다.
 * bfs 탐색을 통해서 물에 잠기지않는 건물에 대해서 안전 영역 개수에 대해서 탐색을
 *
 */
public class B2468_안전영역 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int N, cnt, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int min, max;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        min = 101;
        max = 0;
        ans = 1; // 어떤 지점도 물에 잠기지 않는다면 안전영역은 1개

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] < min) { // 지점의 최저 높이
                    min = map[i][j];
                }

                if (map[i][j] > max) { // 지점의 최고 높이
                    max = map[i][j];
                }
            }
        }

        for (int h = min; h < max; h++) {
            cnt = 0;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > h && !visited[i][j]) { // 안전영역이 될 수 있는 부분에 대해서
                        bfs(i,j,h); // 탐색 시작
                    }
                }
            }

            ans = Math.max(ans, cnt); // 안전영역의 개수의 최댓값을 갱신
        }

        System.out.println(ans);
    }

    private static void bfs(int x, int y, int h) { // (i, j) 부터 장마철 물에 잠기는 높이가 h 일때
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (isInside(nx, ny) && !visited[nx][ny] && map[nx][ny] > h) {
                    q.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        cnt++;
    }

    private static boolean isInside(int x, int y) { // (x, y) 가 유효한 범위 인지 판별하는 메서드
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
