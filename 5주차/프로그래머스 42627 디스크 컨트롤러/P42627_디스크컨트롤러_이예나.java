package study_3week;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class P42627_디스크컨트롤러_이예나 {
	
	   static class job implements Comparable<job>{ // 시간별로, 작업 길이 별로 정렬할 작업 리스트
	       int start;
	       int len;
	       job(int s, int l){
	           this.start = s;
	           this.len=l;
	       }
	       @Override
	       public int compareTo(job o){ //요청 시간 별로 정렬, 그 후 작업 길이별로 정렬
	           if(this.start == o.start){
	               return this.len- o.len;
	           }
	           return this.start - o.start;
	       }
	       
	   }
	  

	   public static List<job> jobList; //작업의 리스트
	   public static boolean[] visited;// 방문여부 체크
	   static int answer;// 전체 길이를 저장할 변수
	   static int Time; //현재 시간을 저장한 변수
	   
    public int solution(int[][] jobs) {
       answer= 0;
       jobList = new ArrayList<>(); //작업을 리스트로 넣어줌
       visited = new boolean[jobs.length]; //방문 여부를 체크
       for(int i=0;i<jobs.length;i++){
           jobList.add(new job(jobs[i][0],jobs[i][1]));
       }    
       Collections.sort(jobList);
       Time = jobList.get(0).start; //가장 처음 요청시간은 제일처음 작업의 요청시간임.
       makeIn(Time);
        answer=answer/jobs.length; //답은 평균값
        return answer;
   }
   

   public static void makeIn(int time){
       int find =0;
       int nowLen = Integer.MAX_VALUE;
       boolean check=false; // 요청 시간 내에 있는 작업이 있는지 체크
       for(int i =0;i < jobList.size();i++){ //요청시간 이내에 요청한 것들중 가장 길이가 짧은 작업 찾기
           if(visited[i]) continue; //이미 작업을 처리했으면 넘어감
           if(jobList.get(i).start>time) break; // 정렬되어있으므로 요청 시간 이후이면 탈출
           job temp = jobList.get(i);
           if(temp.len< nowLen){ //이전에 찾은 요청보다 길이가 짧은 작업을 찾으면 바꿔줌
               nowLen = temp.len; 
               find = i;
               check=true;
           }
       }
      
       if(check){//요청시간 내 작업이 있었음
       visited[find]=true; //찾은 것의 방문여부를 바꿔줌
       Time = time+nowLen; // 현재 시간은 요청한 시간+ 작업의 소요시간
       answer+=Time - jobList.get(find).start; //정답은 현재시간에서 작업의 요청 시간만큼 뺀것을 더한만큼임 
       makeIn(Time); //현재 시간 기준으로 다음작업 찾으러 감
       }
       else{ //요청 시간 내 작업이없음
           
           for(int i=0;i<jobList.size();i++){
               if(!visited[i]){ //방문하지 않은 값이 있으면 그값이 가장 먼저해야하는 작업임
                   visited[i]=true;
                   job temp = jobList.get(i);
                   Time = temp.start+temp.len;
                   answer+=temp.len;
                   makeIn(Time);
                   break;
               }
           }
       }
   }
   
   
   
}
