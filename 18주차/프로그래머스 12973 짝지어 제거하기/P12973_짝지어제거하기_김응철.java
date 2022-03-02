import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        char[] ch = s.toCharArray();
        
        Stack<Character> stack1 = new Stack<Character>();
        Stack<Character> stack2 = new Stack<Character>();
        
        for(int i=ch.length-1;i>=0;i--){
            stack1.push(ch[i]);
        }
        
        char tmp = stack1.pop();
        
        stack2.push(tmp);
        
        while(true){
        
            if(stack1.isEmpty() && stack2.isEmpty()){
                answer = 1;
                break;
            }
            else if(stack1.isEmpty() && stack2.size()!=0)
                break;
            
            tmp = stack1.pop();
            if(!stack2.isEmpty() && stack2.peek().equals(tmp)){
                stack2.pop();
            }
            else{
                stack2.push(tmp);
            }
            
        }
        
        return answer;
    }
}