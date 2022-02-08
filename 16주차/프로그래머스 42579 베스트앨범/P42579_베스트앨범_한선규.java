import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class Solution {
    class Music implements Comparable<Music> {
        int no;
        int play;
        
        public Music(int no, int play) {
            this.no = no;
            this.play = play;
        }
        
        @Override
        public int compareTo(Music o) {
            return o.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, PriorityQueue<Music>> musicsByGenre = new HashMap<>();
        Map<String, Integer> playByGenre = new HashMap<>();
        List<Integer> answers = new ArrayList<>();
        
        for (int i = 0; i < genres.length; i++) {
            // 장르별 재생곡에 저장되지 않은 장르라면
            if (!musicsByGenre.containsKey(genres[i])) {
                PriorityQueue<Music> pq = new PriorityQueue<>();
                pq.offer(new Music(i, plays[i]));
                musicsByGenre.put(genres[i], pq);
                playByGenre.put(genres[i], plays[i]);
                continue;
            }
            // 이미 탐색된 장르라면
            PriorityQueue<Music> pq = musicsByGenre.get(genres[i]);
            pq.offer(new Music(i, plays[i]));
            musicsByGenre.put(genres[i], pq);
            playByGenre.put(genres[i], playByGenre.get(genres[i]) + plays[i]);
        }
        
        while (playByGenre.size() > 0) {
            // String maxGenre = Collections.max(playByGenre.entrySet(), (m1, m2) -> m1.getValue() - m2.getValue()).getKey();
            String maxGenre = Collections.max(playByGenre.entrySet(), Map.Entry.comparingByValue()).getKey();
            playByGenre.remove(maxGenre);
            
            PriorityQueue<Music> pq = musicsByGenre.get(maxGenre);
            answers.add(pq.poll().no);
            if (!pq.isEmpty()) {
                answers.add(pq.poll().no);
            }
        }
        
        answer = answers.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}