package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 21. 10. 16
 *
 * 백준 21611 - 마법사 상어와 블리자드
 * https://www.acmicpc.net/problem/21611
 *
 * 달팽이 배열형태로 만드는 것만 완성시킴...
 * 문제에서 제시한 조건에 따라서 블리자드 -> 폭발 -> 구슬이동 -> 구슬변화
 * 4단계의 로직을 추가적으로 구현할 필요가 있음.
 * 2주차 스터디 미완성해결과제로 3주차 스터디에 복습과제로 진행!!
 *
 */
public class B21611_마법사상어와블리자드_한선규 {
    private static int[][] map;
    private static Point[] arr; // 각 위치의 번호를 저장할 달팽이 모양 배열
    private static int[] dx = {0, 1, 0, -1}; // 좌, 하, 우, 상
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] result = {0, 0, 0, 0}; // 1번, 2번, 3번 구슬이 폭발한 개수 (인덱스 매칭을 위해서 크기는 4로 생성)
    private static int N, M;
    private static Point shark; // 마법사 상어의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int d, s;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        arr = new Point[N*N]; // 1번부터 N*-1 번까지 사용
        shark = new Point(N/2, N/2, 0);


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeSnail(); // 달팽이 배열 생성
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            blizzard(d, s);
            move();
            explosion();
            group();
        }

        System.out.println(result[1] + 2*result[2] + 3*result[3]);
    }

    private static void makeSnail() { // 달팽이 배열 생성
        int sx = shark.x; // 마법사 상어의 x 좌표
        int sy = shark.y; // 마법사 상어의 y 좌표
        int d = 0; // 달팽이 배열의 시작방향은 마법사 상어를 기준으로 왼쪽
        int cnt = 1; // 2번씩 방향을 바꿀때마다 배열의 개수가 하나씩 증가
        int idx = 1; // 1번부터 ~ N*N-1 번까지 매칭시키기 위해서

        while(idx != arr.length) {
            for (int i = 0; i < 2; i++) { // 방향을 2번씩 바꿀때마다
                for (int j = 0; j < cnt; j++) { // 채워야하는 배열의 개수가 하나씩 증가한다.
                    sx += dx[d];
                    sy += dy[d];
                    arr[idx] = new Point(sx, sy, map[sx][sy]);
                    idx++;
                    if (idx == arr.length) return;
                }
                d = (d + 1) % 4;
            }
            cnt++;
        }
    }

    private static void blizzard(int d, int s) { // blizzard 마법 시전
        d = changeDir(d); // 마법 시전 방향으롭 ㅕㄴ경

        for (int i = 1; i <= s; i++) {
            int nx = shark.x + i * dx[d];
            int ny = shark.y + i * dy[d];

            if (isInside(nx, ny)) {
                map[nx][ny] = 0; // 블리자드가 시전되 곳의 구슬을 파괴
                
                for (int j = 1; j < arr.length; j++) { // 해당 좌표에 해당하는 달팽이 배열의 정보도 바꿔준다.
                    if (arr[j].x == nx && arr[j].y == ny) {
                        arr[j] = new Point(nx, ny, 0);
                        break;
                    }
                }
            } else
                break;
        }
    }

    private static void move() { // 파괴된 후 구슬 앞당기기
        // 달팽이 배열을 활용해서 파괴된 구슬을 앞당기는 작업을 수행
        int end = arr.length;
        
        for (int i = 1; i < end; i++) { // 처음부터 끝까지 탐색
            if (arr[i].num == 0) { // i 번위치가 빈칸이라면
                int tmp = i;
                while (tmp < end && arr[tmp].num == 0) { // i 번째 부터 빈칸의 개수를 파악
                    tmp++;
                }
                // 최종적으로 반복문을 빠져나오면 tmp - i 개만큼 빈칸이 존재하는 것이다.
                for (int j = i; j < tmp; j++) {
                    if (j + tmp - i >= end) // 배열의 범위를 벗어나면 안된다.
                        break;
                    arr[j].num = arr[j+tmp-i].num; // j번 빈칸에 빈칸이 아니었던 가장 가까운 위치의 구슬을 넣어준다.
                    arr[j+tmp-i].num = 0; // 해당 위치를 빈칸으로 바꿔준다.
                }
            }
        }
    }

    private static void explosion() { // 4개이상 연속하는 구슬 폭발 (더이상 폭발하는 구슬이 없을때까지 반복)
        boolean more = true;
        int end = arr.length;

        while (true) {
            if (!more)
                break;

            move();
            more = false;

            for (int i = 1; i < end - 1; i++) {
                int cnt = 1; // i 구슬의 개수를 세고 시작
                if (arr[i].num != 0 && arr[i].num == arr[i+1].num) { // 빈칸이 아닌 1, 2, 3 구슬 중에 하나라면 다음 구슬과 비교
                    cnt++; // 동일 하다면 개수를 하나 증가시키고
                    for (int j = i + 1; j < end - 1; j++) { // 다음 구슬부터 동일한 구슬이 몇개있는지 배열의 끝까지 반복
                        if (arr[j].num == arr[j+1].num) // 다음 구슬이 똑같은 구슬일 때마다
                            cnt++; // 개수 증가
                        else
                            break;
                    }

                    // 최종적으로 i 번 위치에 있는 구슬이 몇개가 연속되어있는지 파악완료

                    if (cnt >= 4) { // 4개 이상일 경우 폭발하게 된다.
                        more = true; // 4개이상 연속하는 구슬이 아직 더 존재한다.
                        result[arr[i].num] += cnt;
                        for (int k = i; k < i + cnt; k++) { // i 부터 cnt 만큼 구슬 폭발
                            arr[k].num = 0;
                        }
                    }
                }
            }
        }
    }

    private static void group() { // 구슬 변화 단계
        ArrayList<AB> lists = new ArrayList<>();
        int end = arr.length;
        
        // 동일한 번호에 대해서 그룹을 만들고 해당 정보를 AB 그룹 객체로 리스트에 저장
        for (int i = 1; i < end - 1; i++) {
            if (arr[i].num == 0) // i 번 위치가 빈칸이라면 반복문 종료 (끝에 도달한 것이다.)
                break;
            int cnt = 1;
            if (arr[i].num == arr[i+1].num) { // 동일한 번호의 구슬이라면 같은 그룹!!
                cnt++;
                for (int j = i + 1; j < end - 1; j++) {
                    if (arr[j].num == arr[j+1].num) // 동일한 번호의 구슬이라면 같은 그룹!!
                        cnt++;
                    else {
                        lists.add(new AB(cnt, arr[i].num));
                        i += cnt - 1; // i 부터 cnt 개수만큼 같은 그룹이었기 때문에 다음 탐색은 i + cnt 부터 하면된다.
                        // 반복문 증감식으로 인해 i++ 되므로 -1 연산도 같이 해준다.
                        break;
                    }
                }
            } else { // 동일한 구슬의 개수가 1개일때는 (1, 구슬번호) 를 추가해주면 된다.
                lists.add(new AB(cnt, arr[i].num));
            }
        }

        // AB 그룹 정보를 토대로 구슬을 변화
        int idx = 1;
        for (int i = 0; i < lists.size(); i++) {
            if (idx >= end)
                break;
            AB ab = lists.get(i);
            arr[idx].num = ab.a;
            idx++;
            arr[idx].num = ab.b;
            idx++;
        }
    }

    private static int changeDir(int d) { // 달팽이 배열 순서로 되어있는 dir 방향(좌, 하, 우, 상)을 문제에서 제시된 방향(상, 하, 좌, 우)로 바꿔주는 메서드
        if (d == 3) return 0;
        if (d == 2) return 1;
        if (d == 4) return 2;
        if (d == 1) return 3;
        return 0;
    }

    private static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static class Point { // (x, y) 좌표에 num 이라는 구슬이 들어가 있음
        int x, y, num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static class AB { // 하나의 구슬을 두 개의 구슬 A, B로 나눌 때 사용할 클래스
        int a, b;

        public AB(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
