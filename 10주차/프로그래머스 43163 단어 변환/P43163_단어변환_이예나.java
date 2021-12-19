package study_3week;

import java.util.StringTokenizer;

public class P43163_단어변환_이예나 {
	 public int solution(String begin, String target, String[] words) {
	        int answer = 0;
	        ans = Integer.MAX_VALUE;
	        visited = new boolean[words.length+1];
	        boolean find = false;
	        for(int i=0;i<words.length;i++){
	            if(words[i].equals(target)) {
	                find = true;
	                break;
	            }
	        }
	        if(!find) return 0;
	        dfs(begin,target,0,words);
	        answer = ans;
	        return answer;
	        
	      
	    }
	    
	    public static boolean diff(String bef, String aft){
	        int cnt=0;
	        for(int i=0;i<bef.length();i++){
	            if(bef.charAt(i) == aft.charAt(i)) cnt++;
	        }
	        if(cnt==bef.length()-1) return true;
	        return false;
	    }
	    static boolean visited[] ;
	    static int ans;
	    public static void dfs(String begin, String target, int time, String [] words){
	        
	        if(begin.equals(target)){
	            ans = Math.min(ans, time);
	            return;
	        }
	        for(int i=0;i<words.length;i++){
	            if(visited[i]) continue;
	            if(!diff(begin, words[i])) continue;
	            visited[i] = true;
	            dfs(words[i], target,time+1,words);
	            visited[i] = false;
	        }
	        
	        
	    }

}
