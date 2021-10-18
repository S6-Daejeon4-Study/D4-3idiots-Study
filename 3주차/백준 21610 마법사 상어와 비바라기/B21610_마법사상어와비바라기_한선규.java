package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 10. 16
 *
 * 백준 21610 - 마법사 상어와 비바라기
 * https://www.acmicpc.net/problem/21610
 *
 */
public class B21610_마법사상어와비바라기_한선규 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 왼쪽부터 시계방향 (1 ~ 8) , 0번 인덱스는 사용 X
    private static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static int N, M;
    private static Queue<Point> clouds; // 구름 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        clouds = new LinkedList<>();
        // 비바라기 시전시 생기는 초기 구름 정보
        clouds.add(new Point(N-1, 0));
        clouds.add(new Point(N-1, 1));
        clouds.add(new Point(N-2, 0));
        clouds.add(new Point(N-2, 1));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            visited = new boolean[N][N];
            move(d, s); // 구름 이동 후 물의 양 1 증가
            sideAdd(); // 대각선 물이 들어있는 바구니 수만큼 물의 양 증가
            remove(); // 물의 양 1 증가된 구름의 위치를 제외하고 물의 양 2이상 인곳에 구름 생성  (물의 양 2 감소)
        }

        System.out.println(sum());
    }

    private static void move(int d, int s) { // 구름 이동 후 물의 양 1 증가시키는 메서드
        // d 방향으로 s 칸만큼 구름 이동
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < clouds.size(); j++) {
                Point cur = clouds.poll();
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!isInside(nx, ny)) {
                    if (nx == -1) nx = N - 1;
                    else if (nx == N) nx = 0;

                    if (ny == -1) ny = N - 1;
                    else if (ny == N) ny = 0;
                }

                clouds.add(new Point(nx, ny));
            }
        }

        // 이동 완료된 구름이 있는 영역은 물의 양 1 증가
        for (Point c : clouds) {
            visited[c.x][c.y] = true; // 기존 구름의 위치 방문 체크
            map[c.x][c.y]++;
        }

    }

    private static void sideAdd() { // 물복사버그 마법
        for (Point cloud : clouds) {
            for (int d = 2; d <= 8; d += 2) { // 대각선은 dx[], dy[] 배열의 2, 4, 6, 8 인덱스
                int nx = cloud.x + dx[d];
                int ny = cloud.y + dy[d];

                if (isInside(nx, ny)) {
                    if (map[nx][ny] > 0)
                        map[cloud.x][cloud.y]++;
                }
            }
        }

        clouds.clear(); // 큐를 비워준다.
    }

    private static void remove() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !visited[i][j]) {
                    map[i][j] -= 2;
                    clouds.add(new Point(i, j));
                }
            }
        }

    }

    private static int sum() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += map[i][j];
            }
        }

        return sum;
    }

    private static void print() { // 디버깅 가즈앗~~!!!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static boolean isInside(int x, int y){
        return x >= 0 && x < N  && y >= 0 && y < N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
