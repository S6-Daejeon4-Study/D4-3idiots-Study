import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int cur = -30001;
        
        Arrays.sort(routes, new Comparator<int[]>() {
        	@Override
        	public int compare(int[] o1, int[] o2) {
        		return o1[1] - o2[1];
        	}
		});

        for(int i = 0 ; i < routes.length; i++) {
        	int[] route = routes[i];
        	if(cur >= route[0])
        		continue;
        	cur = route[1];
            answer++;
        }
        
        return answer;
    }
}