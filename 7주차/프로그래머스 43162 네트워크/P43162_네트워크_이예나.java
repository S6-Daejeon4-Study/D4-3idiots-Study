package study_3week;

import java.util.ArrayList;
import java.util.List;

public class P43162_네트워크_이예나 {
	static List<Integer>[] graph;//그래프
	static boolean[] visited; // 방문여부

	public int solution(int n, int[][] computers) {

		graph = new List[n + 1];
		visited = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (computers[i][j] == 1 & i != j) { //나 자신이 아니고 다른 노드이면 넣어줌
					graph[i].add(j);
				}

			}
		}
		int answer = 0;

		for (int i = 0; i < n; i++) {
			if (!visited[i]) { //방문하지 않았으면 새로운 네트워크 생성
				connection(i);
				answer++;
			}
		}
		return answer;
	}

	static void connection(int node) {//dfs

		visited[node] = true;
		for (int i = 0; i < graph[node].size(); i++) {
			if (visited[graph[node].get(i)])
				continue;
			visited[graph[node].get(i)] = true;
			connection(graph[node].get(i));
		}

	}
}
