import java.util.*;

class Solution {
    
    Map<String, String> idMap; // 채팅방에 접속한 이력이 있는 아이디 map
    final String COME_IN = "님이 들어왔습니다.";
    final String GO_OUT = "님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        String[] answer = {};
        idMap = new HashMap<>();
        int cnt = 0;
        
        for (String s : record) {
            String[] arr = s.split(" ");

            if ("Leave".equals(arr[0])) { // 채팅방에서 나가는건 닉네임 변화가 없다.
                continue;
            } else if ("Change".equals(arr[0])) {
                cnt++;
            }
            idMap.put(arr[1], arr[2]); // Map 에다가 id 에 해당하는 nickname을 계속해서 update
        }
        
        answer = new String[record.length - cnt];
        int idx = 0;
        
        // 채팅방 입/퇴장에 대해서만 현재 map에 기록된 nickname을 바탕으로 문구를 기록하면 끝!
        for (String s : record) {
            String[] arr = s.split(" ");
            if ("Enter".equals(arr[0]))
                answer[idx++] = idMap.get(arr[1]) + COME_IN;
            else if ("Leave".equals(arr[0]))
                answer[idx++] = idMap.get(arr[1]) + GO_OUT;
        }
        
        
        return answer;
    }

}