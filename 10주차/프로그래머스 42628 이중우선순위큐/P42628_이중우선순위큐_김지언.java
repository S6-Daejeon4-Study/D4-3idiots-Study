import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> rpq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String op: operations) {
            String[] tmp = op.split(" ");
            if(tmp[0].equals("I")) {
                pq.offer(Integer.parseInt(tmp[1]));
            }
            else if(tmp[1].equals("-1")) {
                pq.poll(); // 최솟값 삭제
            } else if(tmp[1].equals("1")) {
                rpq.clear();
                rpq.addAll(pq);
                rpq.poll(); // 최대값 삭제
                pq.clear();
                pq.addAll(rpq);
            }
        }
        rpq.clear();
        rpq.addAll(pq);
        
        
        return pq.size() != 0? new int[]{rpq.poll(), pq.poll()} : new int[]{0, 0};
    }
}