import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes,new Comparator<int[]>(){
            public int compare(int[] o1,int [] o2){
                if(o1[0] == o2[0])
                    return o1[1] - o2[1];
                else
                    return o1[0]-o2[0];
            }
        });
        
        int tmp = routes[0][1];
        
        for(int i=1;i<routes.length;i++){
            if(routes[i][0]>tmp){
                answer++;
                tmp = routes[i][1];
            }
        }
        
        return answer;
    }
}