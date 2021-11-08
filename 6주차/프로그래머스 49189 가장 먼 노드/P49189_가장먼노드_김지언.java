import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public class Link {
		int node;
		Link next;

		public Link(int node, Link next) {
			this.node = node;
			this.next = next;
		}
	}

	public int solution(int n, int[][] edge) {
		int answer = 0; // 1번 노드로부터 가장 멀리 떨어진 노드 개수
		int max = 0; // 1번 노드로부터 가장 멀리 떨어진 거리
		Link[] edgeList = new Link[n + 1];

		for (int i = 0; i < edge.length; i++) {
			int from = edge[i][0];
			int to = edge[i][1];

			edgeList[from] = new Link(to, edgeList[from]);
			edgeList[to] = new Link(from, edgeList[to]);
		} // 간선 정보 저장

		Queue<Integer> queue = new LinkedList<Integer>(); // BFS
		int[] visited = new int[n + 1]; // 방문 체크 & 노드 거리 저장해놓는 배열
		queue.offer(1);

		while (!queue.isEmpty()) {
			int from = queue.poll();
			Link next = edgeList[from]; // 연결된 노드

			while (next != null) { // 연결된 노드가 있으면
				if (next.node != 1 && visited[next.node] == 0) {// 이미 방문한 노드가 아니면
					queue.offer(next.node); // 다음 노드 방문 대기
					visited[next.node] = visited[from] + 1; // 방문체크 & 거리 저장
					
					if (visited[next.node] > max) { // 거리가 기존 거리보다 더 멀면 갱신
						answer = 1;
						max = visited[next.node];
					} else if (visited[next.node] == max) { // 같으면 증가
						answer++;
					}
				}
				next = next.next; // 다음 노드 확인하러가기
			}
		}

		return answer;
	}
}
