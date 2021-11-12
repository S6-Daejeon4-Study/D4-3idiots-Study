import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][];
        dp[0] = new int[]{triangle[0][0]};
        
        for(int i = 1; i < triangle.length; i++){
            dp[i] = new int[triangle[i].length]; 
            for(int j = 0; j < triangle[i].length; j++){
                // 위 칸으로 대각선 방향으로 한 칸 왼쪽 존재
                if(i-1 >= 0 && j-1 >= 0 )
                    dp[i][j] = Math.max(dp[i][j], triangle[i][j] + dp[i-1][j-1]);
                
                // 위 칸으로 대각선 방향으로 한 칸 오른쪽 존재
                if(i-1 >= 0 && j < triangle[i-1].length)
                    dp[i][j] = Math.max(dp[i][j], triangle[i][j] + dp[i-1][j]);
                
                answer = Math.max(answer, dp[i][j]);
            }
        }
        
        return answer;
    }
}