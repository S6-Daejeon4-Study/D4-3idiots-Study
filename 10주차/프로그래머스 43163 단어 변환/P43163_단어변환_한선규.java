import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        if (Arrays.stream(words).anyMatch(target::equals)) {
            // words 에 target 이 존재한다면 변환 탐색 시도
            answer = bfs(begin, target, words);
        }
        // 없다면 그대로 0을 return
        
        return answer;
    }
    
    public int bfs(String begin, String target, String[] words) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        int cnt = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String s = q.poll();
                
                if (s.equals(target))
                    return cnt;
                
                for (String word : words) { // 변환가능한 문자에 대해서 모두 탐색
                    int k = 0;
                    
                    for (int j = 0; j < s.length(); j++) { // 같은 문자가 몇개있는지 파악
                        if (s.charAt(j) == word.charAt(j))
                            k++;
                    }
                    
                    // 한개빼고 모두 같은 문자로 구성된 문자열이라면
                    if (k == s.length() - 1) {
                        q.offer(word);
                    }
                        
                }
            }
            
            cnt++;
        }
        
        return cnt;
    }
}