import java.util.*;
class Solution
{
    public int solution(String s){
        Stack<String>stack = new Stack<>();
        stack.push(s.substring(0,1));
        for(int i=1;i<s.length();i++){
            if(stack.size()==0){
                stack.push(s.substring(i,i+1));
                continue;
            }
            String front = stack.peek();
            if(s.substring(i,i+1).equals( front) ){
                stack.pop();
            }
            else stack.push(s.substring(i,i+1));
            
        }
        if(stack.size()==0) return 1;
        return 0;
    }
 
}