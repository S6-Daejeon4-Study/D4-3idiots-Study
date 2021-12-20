import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> qMin = new PriorityQueue<>();
        PriorityQueue<Integer> qMax = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<operations.length;i++){
            
            if(operations[i].charAt(0) == 'I'){
                String[] tmp = operations[i].split(" ");
                int num = Integer.parseInt(tmp[1]);
                System.out.println(num);
                qMin.offer(num);
            }
            
            else if(operations[i].charAt(0) == 'D' && !qMin.isEmpty()){
                if(operations[i].charAt(2) == '1'){
                    while(!qMin.isEmpty()){
                        qMax.offer(qMin.poll());
                    }
                    qMax.remove();
                    while(!qMax.isEmpty()){
                        qMin.offer(qMax.poll());
                    }
                }else{
                    qMin.remove();
                }
            }
        }
        if(!qMin.isEmpty()){
            int minNum = qMin.poll();
            while(!qMin.isEmpty()){
                qMax.offer(qMin.poll());
            }
            
            int maxNum = qMax.poll();
            answer = new int[]{
                maxNum,
                minNum      
            };
        }
        else{
            answer = new int[]{0,0};
        }
             
        return answer;
    }
}