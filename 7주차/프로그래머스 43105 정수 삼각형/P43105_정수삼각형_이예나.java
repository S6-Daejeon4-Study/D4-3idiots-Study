package study_3week;

public class P43105_정수삼각형_이예나 {

		  
	    public int solution(int[][] triangle) {
	       
	        int answer=0;
	        int [][] dp = new int [triangle.length][triangle.length];//dp는 현재 인덱스에서 가장 큰 합계
	        dp[0][0]=triangle[0][0];
	        
	        for(int i=1;i<triangle.length;i++){
	            for(int j=0;j<triangle[i].length;j++){
	                if(j==0) {//0번쨰면 내 앞의 하나만 접근 가능
	                    dp[i][j] = dp[i-1][j]+triangle[i][j];
	                }else if(j == triangle[i].length-1){ //제일마지막이면 내 앞 하나만 접근 가능
	                    dp[i][j] = dp[i-1][j-1]+triangle[i][j];
	                }
	                else dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1])+triangle[i][j];
	                
	                if(i==triangle.length-1){ //가장 마지막 열에서 정답알 수 있음
	                    answer = Math.max(answer,dp[i][j]);
	                }
	            }
	        }
	     
	        return answer;
	    }
	
}
