import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        
        for (String s : operations) {
            StringTokenizer st = new StringTokenizer(s);
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            // 삽입 연산이라면
            if ("I".equals(op)) {
                maxPq.offer(num);
                minPq.offer(num);
            }
            // 삭제 연산이라면
            else if ("D".equals(op)) {
                // 큐에 값이 존재한다면
                if (!maxPq.isEmpty()) {
                    int val = 0;
                    // 최댓값 삭제
                    if (num == 1) {
                        val = maxPq.poll();
                        minPq.remove(val);
                    }
                    // 최솟값 삭제
                    else if (num == -1) {
                        val = minPq.poll();
                        maxPq.remove(val);
                    }
                }
            }     
        }
        
        if (!maxPq.isEmpty()) {
            answer[0] = maxPq.peek();
            answer[1] = minPq.peek();
        }
        
        return answer;
    }
}