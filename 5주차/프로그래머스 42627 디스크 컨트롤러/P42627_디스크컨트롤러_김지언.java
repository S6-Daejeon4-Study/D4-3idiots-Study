import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    class Job implements Comparable<Job> {
        int start, time;

        public Job(int start, int time) {
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(Job o) {
            return this.time - o.time;
        }
    }

    public int solution(int[][] jobs) { // [작업이 요청되는 시점, 작업의 소요시간]
        int answer = 0;
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) { // 요청 시점이 같으면
                    return o1[1] - o2[1]; // 소요시간 오름차순
                }
                return o1[0] - o2[0]; // 요청 시점 오름차순
            }
        }); // 작업이 요청되는 시점으로 정렬

//      System.out.println(Arrays.deepToString(jobs));

        int cnt = 0; // 디스크에 넣은 작업 계산용
        int time = jobs[cnt][0]; // 작업 시간 계산용
        PriorityQueue<Job> disk = new PriorityQueue<>(); // 작업의 소요시간이 작을 수록 우선순위가 높음
        disk.add(new Job(jobs[cnt][0], jobs[cnt][1])); // 가장 처음 들어온 작업
        cnt++;

        while (!disk.isEmpty()) { // 디스크에 있는 작업 중 가장 우선순위가 높은 작업 처리
            Job j = disk.poll();
            answer += time - j.start + j.time; // 작업의 요청부터 종료까지 걸린 시간
            time += j.time; // 작업 종료 시간 갱신

            for (int i = cnt; i < jobs.length; i++) {
                if (jobs[i][0] <= time) { // 그 동안 요청 온 작업들
                    disk.add(new Job(jobs[i][0], jobs[i][1])); // 디스크에 넣기
                    cnt++;
                } else {
                    break;
                }
            }

            if (disk.isEmpty() && cnt < jobs.length) { // 디스크가 작업을 수행하고 있지 않을 때
                time = jobs[cnt][0]; // 새로운 작업 설정
                disk.add(new Job(jobs[cnt][0], jobs[cnt++][1]));
            }
        }

        return answer / jobs.length;
    }
}
