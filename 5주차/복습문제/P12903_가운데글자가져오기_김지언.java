class Solution {
	public String solution(String s) {
		String answer = "";

		if (s.length() % 2 == 0) { // 짝수개일때
            // 가운데 두글자
			answer += s.charAt(s.length() / 2 - 1);
			answer += s.charAt(s.length() / 2);
		} else if (s.length() % 2 != 0) { //홀수개일때
			answer += s.charAt(s.length() / 2); // 가운데 한글자
		}

		return answer;
	}
}
