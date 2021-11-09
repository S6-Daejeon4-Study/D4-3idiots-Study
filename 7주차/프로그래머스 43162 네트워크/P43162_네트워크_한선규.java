import java.util.*;

/**
 * @author comkkyu
 * @since 21. 11. 9
 *
 * 프로그래머스 43162 네트워크 https://programmers.co.kr/learn/courses/30/lessons/43162
 *
 * bfs 탐색을 통해서 인접한 정점들에 대해서 모두 탐색을 하고 아직 방문하지 않은 정점들에 대해서 다시 bfs 탐색을 시도하도록 구현함.
 * 결국 bfs 의 호출 횟수가 네트워크의 개수가 된다.
 *
 */
public class P43162_네트워크_한선규 {

    public int solution(int n, int[][] computers) {
        int answer = 0; // 네트워크 개수

        boolean[] visited = new boolean[n]; // 컴퓨터 방문 체크 배열

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 아직 방문하지 않은 컴퓨터라면
                bfs(i, visited, computers); // bfs 탐색으로 인접한 컴퓨터에 대해서 모든 탐색을 진행
                answer++; // 인접한 컴퓨터에 대해서 탐색이 모두 완료되었으면 네트워크의 개수를 증가
            }
        }

        return answer;
    }

    public void bfs(int num, boolean[] visited, int[][] computers) {
        Queue<Integer> q = new LinkedList<>(); // 연결된 컴퓨터들을 저장할 큐
        q.offer(num);
        visited[num] = true;
        int len = computers.length;

        while (!q.isEmpty()) {
            int com = q.poll(); // 기준이 되는 컴퓨터

            for (int i = 0; i < len; i++) { // com 으로 부터 다른 컴퓨터들 사이의 관계를 살펴보자.
                if (i != com && computers[com][i] == 1 && !visited[i]) { // 자신이 아니고 연결되어있으며 아직 방문하지 않은 컴퓨터라면
                    q.offer(i); // 큐에 삽입
                    visited[i] = true; // 방문 처리
                }
            }
        }
    }
}
