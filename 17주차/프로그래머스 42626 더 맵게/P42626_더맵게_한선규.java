import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int sco : scoville) {
            pq.offer(sco);
        }
        
        while (pq.size() > 1) {
            int min = pq.poll();
            int mix = min + (pq.poll() * 2);
            pq.offer(mix);
            answer++;
            if (pq.peek() >= K)
                return answer;
        }
        
        return -1;
    }
}