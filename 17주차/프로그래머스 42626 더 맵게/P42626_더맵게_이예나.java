import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int x : scoville){
            pq.add(x);
        }
        while(true){
           
            int first = pq.poll();
            if(first>=K) break; 
             if(pq.size()<=0) {
                answer=-1;
                break;
            }
            int second = pq.poll();
            pq.add(first + second*2);
            answer++;
        }
        
        
       
        return answer;
    }
}