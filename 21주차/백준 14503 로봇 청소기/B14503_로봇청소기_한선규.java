import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14503_로봇청소기_한선규 {

    private static int N, M;
    private static final int CLEAN_COMPLETE = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 로봇청소기가 있는 장소의 크기 입력 (N * M)
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        // 로봇청소기 좌표 및 방향 입력
        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        int startDir = Integer.parseInt(st.nextToken());

        // init map
        // 0: 청소가능, 1: 벽
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(map, startRow, startCol, startDir));
    }

    private static int bfs(int[][] map, int botRow, int botCol, int botDir) {
        int[] dr = {-1, 0, 1, 0}; // 위부터 시계방향
        int[] dc = {0, 1, 0, -1}; // 위부터 시계방향
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>(); // 현재 로봇청소기의 행, 열, 방향 저장할 큐
        q.offer(new int[]{botRow, botCol, botDir});
        map[botRow][botCol] = CLEAN_COMPLETE; // 청소완료

        while (!q.isEmpty()) {
            int curRow = q.peek()[0]; // 현재 로봇청소기의 행 좌표
            int curCol = q.peek()[1]; // 현재 로봇청소기의 열 좌표
            int curDir = q.poll()[2]; // 현재 로봇청소기의 방향

            for (int d = curDir - 1; d >= curDir - 4; d--) {
                int nextDir = d < 0 ? d + 4 : d;
                int nextRow = curRow + dr[nextDir];
                int nextCol = curCol + dc[nextDir];
                // 배열범위 밖이거나 청소할 수 없는 구역이라면
                if (!isInside(nextRow, nextCol) || map[nextRow][nextCol] != 0) {
                    if (nextDir == curDir) { // 4방향을 모두 탐색했는데 청소할 곳이 없다면 후진해야함
                        int prevRow = curRow - dr[nextDir];
                        int prevCol = curCol - dc[nextDir];
                        if (!isInside(prevRow, prevCol) || map[prevRow][prevCol] == 1)
                            return cnt;
                        q.offer(new int[]{prevRow, prevCol, nextDir});
                    }
                } else {
                    if (map[nextRow][nextCol] == 0) {
                        map[nextRow][nextCol] = CLEAN_COMPLETE;
                        cnt++;
                        q.offer(new int[]{nextRow, nextCol, nextDir});
                        break;
                    }
                }
            }
        }

        return cnt;
    }

    private static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}