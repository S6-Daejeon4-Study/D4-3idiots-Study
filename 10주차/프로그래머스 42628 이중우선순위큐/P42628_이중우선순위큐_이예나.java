package study_3week;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P42628_이중우선순위큐_이예나 {

	public int[] solution(String[] operations) {
        int[] answer = new int[2];
        int maxV = 0;
        int minV = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<operations.length;i++){
           StringTokenizer st  = new StringTokenizer(operations[i]);
            char order  = st.nextToken().charAt(0);
            int value = Integer.parseInt(st.nextToken());
            if(order=='I'){
                pq.offer(value);
                
            }
            else if(order=='D'){
                if(pq.size()==0) continue;
                if(value==-1){ // 최솟값 삭제
                 pq.poll();  
                if(pq.size()!=0)
                minV = pq.peek();
                if(pq.size()==1)
                maxV = pq.peek();

                }
                else{ // 최댓값 삭제
                  PriorityQueue<Integer>  maxPq =  new PriorityQueue<>(Collections.reverseOrder());
                  maxPq.addAll(pq);
                    maxPq.poll();
                    pq.clear();
                    pq.addAll(maxPq);
                    if(maxPq.size()!=0)
                        maxV = maxPq.peek();
                        pq.peek();
                    if(maxPq.size()==1)
                         minV = maxPq.peek();
     
                }
            }
        }
        if(pq.size()==0) {
            minV=0; maxV=0;
        }
        if(pq.size()>1){
            minV = pq.poll();
           while(pq.size()>0){
               maxV = pq.poll();
           }
        }
        answer[1]=minV;
        answer[0] = maxV;
        
        return answer;
    }
	
}
