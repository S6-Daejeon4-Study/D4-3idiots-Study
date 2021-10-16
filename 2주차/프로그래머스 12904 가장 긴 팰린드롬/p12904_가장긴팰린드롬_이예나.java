package day1010;

public class p12904_가장긴팰린드롬_이예나 {

	    public int solution(String s)
	    {   int maxLen = 1;
	        
	        
	        for(int i=0;i<s.length();i++){
	            for(int j=s.length()-1;j>=i;j--){
	                int start = i;
	                int last=j;
	                boolean check=true;
	                for(int k=0;k<j;k++){
	                    if(maxLen>last-start+1) break;
	                    if(start+k>= last-k) break;
	                    if(s.charAt(start+k) != s.charAt(last-k)){
	                        check=false;
	                        break;
	                    }
	                }
	                if(check){
	                    maxLen = Math.max(maxLen,last-start+1);
	                }
	                
	            }
	                
	                
	            }
	        
	        
	        
	        return maxLen;
	    }
	}

