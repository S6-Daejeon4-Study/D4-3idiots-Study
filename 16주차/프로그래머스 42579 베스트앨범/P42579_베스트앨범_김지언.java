import java.util.*;

class Solution {
    class Music implements Comparable<Music> {
        int idx, play;
        
        public Music(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
        
        public int compareTo(Music m) {
            return m.play - this.play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, PriorityQueue<Music>> hm = new HashMap<>();
        Map<String, Integer> totalPlays = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++) {
            PriorityQueue<Music> pq = hm.get(genres[i]);
            int total = totalPlays.get(genres[i]) == null ? 0 : totalPlays.get(genres[i]);
            
            if(pq == null) {
                pq = new PriorityQueue<>();
            }
            totalPlays.put(genres[i], total + plays[i]);
            pq.offer(new Music(i, plays[i]));
            hm.put(genres[i], pq);
        }
        
        List<Integer> answer = new ArrayList<>();
        
        while(totalPlays.size() > 0) {
            String maxGenre = null;
            for(String genre : totalPlays.keySet()) {
                if(maxGenre == null || totalPlays.get(genre) > totalPlays.get(maxGenre)) {
                    maxGenre = genre;
                }
            }
            PriorityQueue<Music> pq = hm.get(maxGenre);
            answer.add(pq.poll().idx);
            if(!pq.isEmpty())
                answer.add(pq.poll().idx);
            totalPlays.remove(maxGenre);
        }
        
        return answer.stream().mapToInt(i->i).toArray();
    }
}