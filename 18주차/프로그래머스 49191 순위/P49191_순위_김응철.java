import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] arr = new int[n+1][n+1];
        
        for(int i=0;i<results.length;i++){
            arr[results[i][0]][results[i][1]] = 1; // 이기면 1
            arr[results[i][1]][results[i][0]] = 2; // 반대는 졌으니까 2
        }
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(i==j) continue;
                if(arr[i][j] == 1){
                    for(int k=1;k<n+1;k++){
                        if(arr[j][k]==1){
                            arr[i][k] = 1;
                            arr[k][i] = 2;
                        }
                    }
                    
                }
                else if(arr[i][j] == 2){
                    for(int k=1;k<n+1;k++){
                        if(arr[j][k]==2){
                            arr[i][k] = 2;
                            arr[k][i] = 1;
                        }
                    }
                }
            }
        }
        
        for(int i=1;i<n+1;i++){
            int cnt=0;
            for(int j=1;j<n+1;j++){
                if(arr[i][j]!=0)
                    cnt++;
            }
            if(cnt==n-1)
                answer++;
        }
        
        return answer;
    }
}