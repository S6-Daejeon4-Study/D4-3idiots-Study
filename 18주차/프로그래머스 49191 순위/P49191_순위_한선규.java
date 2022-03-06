class Solution {
  public int solution(int n, int[][] results) {
      int answer = 0;
      int[][] game = new int[n+1][n+1]; // (0: 결과모름, 1: 승리, -1: 패배)
      int len = results.length;
      
      for (int[] r : results) { // 주어진 기록에 대해서
          game[r[0]][r[1]] = 1; // r[0] 선수는 r[1] 선수로부터 승리했다는 기록
          game[r[1]][r[0]] = -1; // r[1] 선수는 r[0] 선수에게 패배했다는 기록
      }
      
      for (int k = 1; k <= n; k++) {
          for (int i = 1; i <= n; i++) {
              for (int j = 1; j <= n; j++) {
                  if (game[i][k] == 1 && game[k][j] == 1) { // k번 선수가 i번 선수에게 패배했지만 j번 선수에게는 승리했다면 i번 선수는 j번 선수에게도 승리
                      game[i][j] = 1;
                      game[j][i] = -1;
                  }
              }
          }
      }
      
      for (int i = 1; i <= n; i++) {
          int cnt = 0;
          for (int j = 1; j <= n; j++) { // i번 선수가 승리또는 패배한 기록의 개수를 파악
              if (game[i][j] == 1 || game[i][j] == -1)
                  cnt++;
          }
          if (cnt == n - 1) // 총 n명의 선수가 경기하는데 n-1 경기에 대한 승패를 안다면 정확한 순위 계산이 가능
              answer++;
      }
      
      return answer;
  }
}