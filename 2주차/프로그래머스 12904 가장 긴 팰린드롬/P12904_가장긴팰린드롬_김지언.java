package level3;

class Solution {
	public int solution(String s) {
		int answer = 0;

		for (int i = 0; i < s.length(); i++) {
			answer = Math.max(answer, findPalindrome(i, s));
		}

		return answer;
	}

	private int findPalindrome(int i, String s) {
		int len = 0;

		for (int j = 1; j <= s.length(); j++) { // 펠린드롬이 홀수 길이일 경우
			if (i - j < 0 || i + j >= s.length() || s.charAt(i - j) != s.charAt(i + j)) { // 더 이상 펠린드롬이 아니면
				len = Math.max(len, (j - 1) * 2 + 1); // 값 넣어주기
				break;
			}
		}

		for (int j = 1; j <= s.length(); j++) { // 펠린드롬이 짝수 길이일 경우
			if (i - j + 1 < 0 || i + j >= s.length() || s.charAt(i - j + 1) != s.charAt(i + j)) {
				len = Math.max(len, (j - 1) * 2); // 값 넣어주기
				break;
			}
		}

		return len;
	}
}