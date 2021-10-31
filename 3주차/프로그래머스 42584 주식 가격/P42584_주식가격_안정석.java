package day1017;

class Solution {
    public int[] solution(int[] prices) {
        int time[] = new int[prices.length]; // 시간 체크 배열
       
        for(int i=0; i<prices.length; i++){
        	int cnt = 0; // 시간 체크용
            for(int j=i+1; j<prices.length; j++ ){ // i의 뒤쪽 인덱스를 검사해서 가격이 떨어졌으면 시간 체크 후 break; 배열의 뒤에서 두번째 인덱스는 1이 고정이고 마지막은 0이 고정으로 나온다.
                cnt++;
                if (prices[j]<prices[i])
                {
                    break;                        
                }
            }
            time[i] = cnt; 
        }
        return time; 
    }
}