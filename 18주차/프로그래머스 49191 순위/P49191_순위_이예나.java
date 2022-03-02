import java.util.*;
class Solution {
    static boolean visited[][];
    static int [][] graph;
    public int solution(int n, int[][] results) {
         int answer = 0;
        graph = new int [n+2][n+2];
        visited = new boolean[n+2][n+2];
        for(int i=0;i<results.length;i++){
            graph[results[i][0]][results[i][1]]=1;
            graph[results[i][1]][results[i][0]]=2;
        }
          for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(graph[i][j]!=0 && !visited[i][j]){
                    dfs(i,j);
                    visited[i][j]=true;
                }
            }
          }
         for(int i=1;i<=n;i++){
             int cnt=0;
            for(int j=1;j<=n;j++){
                   if(graph[i][j]!=0) cnt++;
            }
            if(cnt == n-1) answer++;
         }
        
       
        return answer;
    }
    static void dfs(int start,int go){
       
        for(int i=0;i<graph[go].length;i++){
            if(graph[go][i]==0) continue;
            if(!visited[start][i] && graph[start][go] == graph[go][i] ){
                graph[start][i]=graph[go][i];
                dfs(start,i);
                visited[start][i]=true;
            }        
        }
    }
}