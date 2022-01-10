import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0, mid, total;
        
        Arrays.sort(times);
        
        long min = 1;
        long max = (long)n * times[times.length-1];
        
        while(min<=max){
            mid = (min + max) / 2;
            total = 0;
            
            for(int i=0;i<times.length;i++){
                total += mid /times[i];
            }
            
            if(total < n)
                min = mid + 1;
            else{
                max = mid - 1;
                answer = mid;
            }
            
        }
        
        return answer;
    }
}