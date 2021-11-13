/**
 * @author comkkyu
 * @since 21. 11. 13
 *
 * 프로그래머스 43105 정수삼각형 - https://programmers.co.kr/learn/courses/30/lessons/43105
 *
 * 가장 밑바닥까지의 경로까지 최댓값을 구하는 문제이기 때문에 이전경로의 최댓값을 활용하는 dp 알고리즘으로 구현함.
 *
 */
public class P43105_정수삼각형_한선규 {

    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        int[][] dp = new int[height][];
        dp[0] = new int[]{triangle[0][0]};

        // 왼쪽 변과 오른쪽 변으로 갈 수 있는 경로는 1가지 밖에 없다.
        for (int i = 1; i < height; i++) {
            int len = triangle[i].length;
            dp[i] = new int[len];
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][len-1] = dp[i-1][len-2] + triangle[i][len-1];
        }

        // 대각선 한 칸 오른쪽 또는 왼쪽 이동하는 경우중 최댓값으로 dp 배열 값 설정
        for (int i = 2; i < height; i++) {
            for (int j = 1; j < triangle[i].length - 1; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];

                if (i == height - 1) { // 마지막 높이의 값 중에서 최댓값을 구하면 된다.
                    answer = Math.max(dp[i][j], answer);
                }
            }
        }

        return answer;
    }
}
