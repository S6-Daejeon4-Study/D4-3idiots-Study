import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) { // n: 컴퓨터의 개수, computers: 연결에 대한 정보
        int answer = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){ // 방문하지 않았으면 메서드 호출
                queue.offer(i);
                visited[i] = true;
                checkNetwork(queue, visited, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void checkNetwork(Queue<Integer> queue, boolean[] visited, int[][] computers){
        while(!queue.isEmpty()){
            int index = queue.poll();
            
            for(int i = 0; i < visited.length; i++){
                if(!visited[i] && computers[index][i]==1){ // 방문하지 않고 연결되어 있다면
                    queue.offer(i); // 큐 삽입
                    visited[i] = true; // 방문 체크
                }
            }
        }
    }
}