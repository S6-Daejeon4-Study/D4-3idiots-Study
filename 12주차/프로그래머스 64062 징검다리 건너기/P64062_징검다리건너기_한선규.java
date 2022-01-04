class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 200000000;
        
        while (min <= max) {
            int mid = (min + max) / 2;
            if (isJump(stones, k, mid)) { // mid 인원이 징검다리를 건널 수 가 있다면 
                answer = mid++; // mid 인원이 징검다리를 건널 수 있으므로 answer 에 저장
                min = mid; // mid+1 ~ max 범위의 mid 에 대해서 다시 징검다리를 건널 수 있는 인원이 있는지 탐색필요
            } else { // mid 인원이 징검다리를 건널 수 없으므로
                max = --mid; // mid 인원보다 한명 적은 인원을 max로 다시 구한 mid 에 대해서 탐색필요
            }
        }
        
        return answer;
    }
    
    public boolean isJump(int[] stones, int k, int mid) {
        int jump = 0;
        for (int stone : stones) {
            if (stone < mid) { // 현재 탐색할 stone의 값이 건너려는 인원보다 적다면 점프해야 될 상황이 되버림
                jump++;
                if (jump == k) return false; // jump 해야될 돌의 개수가 k 와 동일하다면 해당 인원으로는 건널 수 없음
            } else { // 다시 0으로 바꿔야 연속된 돌의 개수에 대해서 파악할 수 있음
                jump = 0;
            }
        }
        
        return true;
    }
}