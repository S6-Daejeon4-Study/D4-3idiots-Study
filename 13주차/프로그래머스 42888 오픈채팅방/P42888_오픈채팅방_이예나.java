import java.util.*;
class Solution {

    public String[] solution(String[] record) {
        List<String> ids = new ArrayList<>();
        List<String> records=new ArrayList<>();
        HashMap<String,String> users = new HashMap<>();  
   
        for(int j=0;j<record.length;j++){
            StringTokenizer st = new StringTokenizer(record[j]," ");
             String order = st.nextToken();
            if(order.equals("Enter")){
            String id = st.nextToken();
            String name = st.nextToken();
                ids.add(id);
                records.add("님이 들어왔습니다.");
                users.put(id,name);
            }
            else if(order.equals("Leave")){
               
            String id = st.nextToken();
               ids.add(id);
                records.add("님이 나갔습니다.");
                
            }
            else if(order.equals("Change")){
            String id = st.nextToken();
            String name = st.nextToken();
               
                users.put(id,name); 
            
            }
        }
        String[] answer = new String[ids.size()];
        for(int i=0;i<ids.size();i++){
            String name=users.get(ids.get(i));
            String oneAns="";
            oneAns+=name;
            oneAns+=records.get(i);
            answer[i] = oneAns;
            
        }
        return answer;
    }
        
}