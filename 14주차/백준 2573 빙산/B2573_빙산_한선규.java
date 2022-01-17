import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @since 22. 1. 17
 *
 * 백준 2573 - 빙산
 * https://www.acmicpc.net/problem/2573
 *
 */
public class B2573_Main {

    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int N, M, year;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        boolean flag = false;

        // init map
        // 배열의 첫번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        while (remainIceberg()) { // 빙산이 남아 있다면
            map = meltIceberg(); // 빙산을 녹여!
            boolean[][] visited = new boolean[N][M]; // 2차원 방문 배열 생성
            int cnt = 0;
            for (int n = 1; n < N - 1; n++) {
                for (int m = 1; m < M - 1; m++) {
                    if (!visited[n][m] && map[n][m] != 0) {
                        bfs(n, m, visited);
                        cnt++;
                    }
                }
            }
            if (cnt >= 2) {
                System.out.println(year);
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println(0);
        }

    }

    /**
     * 2차원 배열 깊은복사 메서드
     *
     * @param origin 복사할 원본 배열
     * @return origin의 값을 그대로 복사한 새로운 배열, 복사할 배열이 null일 경우 그대로 null 반환
     */
    public static int[][] copyMap(int[][] origin) {
        if (origin == null)
            return null;

        int[][] newMap = new int[origin.length][origin[0].length];

        for (int i = 0; i < origin.length; i++) {
            System.arraycopy(origin[i], 0, newMap[i], 0, origin[0].length);
        }

        return newMap;
    }

    /**
     * 빙산이 몇 그룹으로 나누어져 있는지 확인하는 메서드
     */
    public static void bfs(int x, int y, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int curX = q.peek()[0];
            int curY = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];

                if (isInside(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    /**
     * 여전히 녹을 빙산이 존재하는지 판별하는 메서드
     *
     * @return true: 빙산이 존재, false: 모든 빙산이 녹음
     */
    public static boolean remainIceberg() {
        for (int n = 1; n < N - 1; n++) {
            for (int m = 1; m < M - 1; m++) {
                if (map[n][m] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 일년이 지나서 빙산이 녹은 후의 모습을 반환하는 메서드
     *
     * @return 일년 뒤 빙산의 모습
     */
    public static int[][] meltIceberg() {
        year++;
        int[][] copyMap = copyMap(map);

        for (int n = 1; n < N - 1; n++) {
            for (int m = 1; m < M - 1; m++) {
                if (map[n][m] != 0) { // 빙산이 존재한다면
                    int cnt = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = n + dx[d];
                        int ny = m + dy[d];

                        if (map[nx][ny] == 0) {
                            cnt++;
                        }
                    }

                    copyMap[n][m] = map[n][m] < cnt ? 0 : map[n][m] - cnt;
                }
            }
        }

        return copyMap;
    }

    /**
     * 좌표 (x, y)에 대해서 배열 범위 밖을 판별하는 메서드
     *
     * @param x 행좌표
     * @param y 열좌표
     * @return true: 배열 안 범위, false: 배열 밖 범위
     */
    public static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}