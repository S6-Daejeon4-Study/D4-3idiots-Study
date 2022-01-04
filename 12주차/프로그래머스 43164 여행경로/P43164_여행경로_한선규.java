import java.util.*;

class Solution {
    
    static final String BEGIN = "ICN";

    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        List<String> routes = new ArrayList<>();
        boolean[] used = new boolean[tickets.length];
        
        Arrays.sort(tickets, (t1, t2) ->
            t1[0].equals(t2[0]) ? t1[1].compareTo(t2[1]) : t1[0].compareTo(t2[0])
        );
        
        dfs(tickets, routes, used, BEGIN, BEGIN, 0);
        
        answer = routes.get(0).split(" ");
        
        return answer;
    }
    
    public void dfs(String[][] tickets, List<String> routes, boolean[] used, String begin, String route, int cnt) {
        int ticketCnt = tickets.length; // 보유한 항공권 개수
        
        if (cnt == ticketCnt) { // 모든 항공권을 사용한 경로만 routes 에 추가
            routes.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!used[i] && tickets[i][0].equals(begin)) {
                used[i] = true; // 항공권 사용
                String dest = tickets[i][1]; // 현재 항공권으로 갈 수 있는 도착지 정보
                dfs(tickets, routes, used, dest, route+" "+dest, cnt + 1);
                used[i] = false; // 항공권 미사용
            }
        }
    }
}