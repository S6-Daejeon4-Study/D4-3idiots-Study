import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        answer = removePair(s);

        return answer;
    }
    
    public int removePair(String s) {
        if (s.length() % 2 == 1) return 0; // 문자열의 길이가 홀수라면 짝을제거해서 모든 문자를 제거할 수 없다.
        char[] cArr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        
        for (char c : cArr) {
            if (stack.isEmpty()) // stack 이 비어있다면  넣어준다.
                stack.push(c);
            else if (stack.peek() == c) // stack 에 있는 문자와 지금 탐색하는 문자와 동일하다면 짝을 지어 제거
                stack.pop();
            else // stack 에 있는 문자와 지금 탐색하는 문자가 다르다면 이 문자도 스택에 넣어준다.
                stack.push(c);
        }
        
        return stack.isEmpty() ? 1 : 0; // 최종적으로 스택에 아무런 문자가 없으면 모두 짝이 지어진 것이므로 1을 반환
    }
}