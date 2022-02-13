import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int sco: scoville) {
            pq.offer(sco);
        }
        
        while(!pq.isEmpty()) {
            int s = pq.poll();
            if(s < K && !pq.isEmpty()) {
                int s2 = pq.poll();
                
                pq.offer(s + s2 * 2);
                answer++;
            } else if(s < K && pq.isEmpty()) {
                answer = -1;
                break;
            }
        }

        return answer;
    }
}