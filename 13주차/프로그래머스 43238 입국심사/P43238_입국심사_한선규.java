class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long min = 1;
        long max = 1;
        
        for (int time : times) {
            max = Math.max(time, max);
        }

        max *= n;
        
        // System.out.println(max);
        
        while (min <= max) {
            long mid = (min + max) / 2;
            long sum = 0;
            
            for (int time : times) {
                sum += mid / time;    
            }
            
            if (sum < n) { // 시간이 부족한 경우
                min = mid + 1; // 최소시간 갱신
            } else { // 시간이 충분한 경우
                max = mid - 1; // 이분탐색으로 끝까지 최소시간을 탐색
                answer = mid;
            }
        }
        
        return answer;
    }
}