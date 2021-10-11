package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 10. 10
 *
 * 백준 21611 - 마법사 상어와 블리자드
 * https://www.acmicpc.net/problem/21611
 *
 * 달팽이 배열형태로 만드는 것만 완성시킴...
 * 문제에서 제시한 조건에 따라서 블리잗 -> 폭발 -> 구슬이동 -> 구슬변화
 * 4단계의 로직을 추가적으로 구현할 필요가 있음.
 * 2주차 스터디 미완성해결과제로 3주차 스터디에 복습과제로 진행!!
 *
 */
public class B21611_마법사상어와블리자드_한선규 {
    private static int[][] map;
    private static Point[] pos;
    private static int[] dx = {0, 1, 0, -1}; // 좌, 하, 우, 상
    private static int[] dy = {-1, 0, 1, 0};
    private static int N, M;
    private static Point shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int d, s;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pos = new Point[N*N+1];
        shark = new Point(N/2, N/2, 0);


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeSnail();

        System.out.println("디버깅");

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            blizzard(d, s);
            move();
            explosion();
            group();
        }
    }

    private static void makeSnail() { // 달팽이 배열 생성
        int sx = shark.x; // 마법사 상어의 x 좌표
        int sy = shark.y; // 마법사 상어의 y 좌표
        int d = 0; // 달팽이 배열의 시작방향은 마법사 상어를 기준으로 왼쪽
        int cnt = 1; // 2번씩 방향을 바꿀때마다 배열의 개수가 하나씩 증가
        int idx = 1; // 1번부터 ~ N*N-1 번까지 매칭시키기 위해서

        while(idx != pos.length - 1) {
            for (int i = 0; i < 2; i++) { // 방향을 2번씩 바꿀때마다
                for (int j = 0; j < cnt; j++) { // 채워야하는 배열의 개수가 하나씩 증가한다.
                    sx += dx[d];
                    sy += dy[d];
                    pos[idx] = new Point(sx, sy, map[sx][sy]);
                    idx++;
                    if (idx == pos.length-1) return;
                }
                d = (d + 1) % 4;
            }
            cnt++;
        }
    }

    private static void blizzard(int d, int s) { // blizzard 마법 시전
        for (int i = 1; i <= s; i++) {
            int nx = shark.x + i * dx[d];
            int ny = shark.y + i * dy[d];

        }
    }

    private static void move() { // 파괴된 후 구슬 앞당기기

    }

    private static void explosion() { // 4개이상 연속하는 구슬 폭발 (더이상 폭발하는 구슬이 없을때까지 반복)

    }

    private static void group() { // 구슬 변화 단계

    }

    private static int changeDir(int d) { // 달팽이 배열 순서로 되어있는 dir 방향(좌, 하, 우, 상)을 문제에서 제시된 방향(상, 하, 좌, 우)로 바꿔주는 메서드
        if (d == 3) return 0;
        if (d == 2) return 1;
        if (d == 4) return 2;
        if (d == 1) return 3;
        return 0;
    }

    static class Point { // (x, y) 좌표에 num 이라는 구슬이 들어가 있음
        int x, y, num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
