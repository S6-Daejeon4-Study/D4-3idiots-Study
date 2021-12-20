import java.util.*;

class Solution {
    
    class Node{
        
        String word;
        int depth;
        
        Node(String word, int depth){
            this.word = word;
            this.depth = depth;
        }
        
    }
    
    public int solution(String begin, String target, String[] words) {
    
        boolean[] check = new boolean[words.length];
        
        Queue<Node> queue = new LinkedList<Node>();
        
        queue.offer(new Node(begin,0));
        
        while(!queue.isEmpty()){
            Node tmp = queue.poll();
            
            for(int i=0;i<words.length;i++){
                int cnt=0;
                if(check[i]) continue;
                for(int j=0;j<begin.length();j++){
                    if(words[i].charAt(j) != tmp.word.charAt(j)){
                        cnt++;
                    }
                }
                if(cnt==1){
                    if(words[i].equals(target)){
                        return tmp.depth+1;
                    }
                    queue.offer(new Node(words[i],tmp.depth+1));
                    check[i] = true;
                }
            }
        }
        return 0;
    
    }
    
}