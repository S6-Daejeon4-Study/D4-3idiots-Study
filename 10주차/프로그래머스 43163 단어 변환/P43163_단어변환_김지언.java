import java.util.*;

class Solution {
    public class Node {
        String word;
        int depth;
        
        public Node(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(begin, 0));
        
        while(!queue.isEmpty()){
            Node origin = queue.poll();
            
            if(origin.word.equals(target)) return origin.depth;
            
            for(int i = 0; i < words.length; i++) { // 모든 단어 중에서
                if(visited[i]) continue; // 방문했으면 패스 
                if(checkString(origin.word, words[i])) {
                    queue.offer(new Node(words[i], origin.depth + 1));
                    visited[i] = true;
                }
            }
        }
        
        return 0;
    }
    
    public boolean checkString(String origin, String word) {
        int cnt = 0; // 일치하지 않는 알파벳 수
        
        for(int i = 0; i < origin.length(); i++) {
            if(origin.charAt(i) != word.charAt(i))
                cnt++;
        }
        
        return cnt == 1? true: false;
    }
}