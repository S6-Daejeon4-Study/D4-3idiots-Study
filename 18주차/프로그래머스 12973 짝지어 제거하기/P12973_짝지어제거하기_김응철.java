import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        char[] ch = s.toCharArray();
        
        LinkedList<Character> list = new LinkedList<>();
        
        for(int i=0;i<ch.length;i++){
            list.add(ch[i]);
        }
        
        while(true){
            if(list.size() == 0){
                answer = 1;
                break;
            }
            int flag = 0;
            for(int i=0;i<list.size()-1;i++){
                if(list.get(i).equals(list.get(i+1))){
                    list.remove(i);
                    list.remove(i);
                    break;
                }
                if(i == list.size()-2)
                    flag = 1;
            }
            if(flag == 1)
                break;
        }
        
        return answer;
    }
}