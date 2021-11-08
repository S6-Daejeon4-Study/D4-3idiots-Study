package study_3week;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P49189_가장먼노드_이예나 {
	static int[] far; //거리를 담은 배열
	static boolean[] visited; // 노드 방문 여부
	static int maxLen;  // 가장 먼 노드의 거리 
	static List<Integer>[] graph; //양방향 그래프

	public int solution(int n, int[][] edge) {
		int answer = 0;
		visited = new boolean[n + 1];
		graph = new List[n + 1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		far = new int[n + 1];
		for (int i = 0; i < edge.length; i++) { //양방향으로 노드를 넣어줌
		
			graph[edge[i][0]].add(edge[i][1]);
			graph[edge[i][1]].add(edge[i][0]);
		}
		findNext(1); //1번이 기준
		for (int i = 1; i <= n; i++) { //n개까지 돌면서 가장 긴 거리만큼의 노드개수를 센다
			if (far[i] == maxLen)
				answer++;

		}

		return answer;
	}

	static void findNext(int index) { //bfs 수행

		Queue<Integer> queue = new LinkedList<>();
		queue.add(index);
		visited[index] = true;
		far[index] = 0;
		maxLen = 0;
		while (!queue.isEmpty()) {
			int front = queue.poll();
			for (int i = 0; i < graph[front].size(); i++) {
				int next = graph[front].get(i);
				if (visited[next])
					continue;

				queue.add(next);
				visited[next] = true;
				far[next] = far[front] + 1;
				maxLen = Math.max(maxLen, far[next]);
			}
		}

	}

}
