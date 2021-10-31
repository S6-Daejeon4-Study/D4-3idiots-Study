package com.ssafy.pro;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        // #1. 작업 대기 목록 큐 생성 (소요시간이 짧은 순서로)
        PriorityQueue<int[]> pq = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);

        // #2. 요청이 들어오는 작업을 일단 요청순서에 따른 오름차순 정렬
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);

        // #3. 가장 첫번째 요청부터 쭉 처리해 보자
        int idx = 0; // 시작 인덱스
        int len = jobs.length; // 작업 요청의 개수
        int time = 0; // 현재 시각

        while (idx < len || !pq.isEmpty()) {
            // 현재 탐색하는 작업의 요청되는 시점이 현재 시각 이하라면 대기목록에 넣어주자!
            if (idx < len && jobs[idx][0] <= time) {
                pq.offer(jobs[idx]);
                idx++;
                continue;
            }

            // 작업 요청 처리하는 부분
            if (pq.isEmpty()) { // 현재 대기중인 작업목록이 존재하지 않는 다면
                time = jobs[idx][0]; // 현재시각을 현재 처리해야할 작업의 시작시간으로 변경
            } else { // 대기 목록이 존재한다면
                int[] job = pq.poll();
                answer += time - job[0] + job[1]; // 해당 작업이 요청부터 종료까지 기다린 시간을 추가
                time += job[1]; // 해당 작업이 소요되는 시간만큼 현재 시간 추가
            }
        }

        answer /= len;

        return answer;
    }
}
