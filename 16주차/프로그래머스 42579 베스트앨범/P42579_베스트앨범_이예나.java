import java.util.*;
class Solution {
    class Music implements Comparable <Music>{
        int index;
        int plays;
        public Music(int index, int plays){
            this.index= index;
            this.plays = plays;
        }
    @Override
       public  int compareTo(Music o){
            return o.plays - this.plays;
        }
    }
    class TotalPlay implements Comparable <TotalPlay>{
        String music;
        int sum;
        public TotalPlay(String music, int sum){
            this.music = music;
            this.sum=sum;
        }
        @Override
        public int compareTo(TotalPlay o){
            return o.sum - this.sum;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        HashMap<String, List<Music>> map = new HashMap<>();
        for(int i=0;i<genres.length;i++){
             List<Music> list = new ArrayList<>();
            if(!map.containsKey(genres[i])){
               
                list.add(new Music(i,plays[i]));
                
                map.put(genres[i],list);
            }else{
                list= map.get(genres[i]);
                list.add(new Music(i,plays[i]));
                Collections.sort(list);
                
            } map.put(genres[i],list);
        }
       List<TotalPlay> total = new ArrayList<>();
       for(String key: map.keySet()){
          
           int sum=0;
           for(int i=0;i<map.get(key).size();i++){
               sum+=map.get(key).get(i).plays;
           }
           total.add(new TotalPlay(key,sum));
       }
        List<Integer> ans = new ArrayList<>();
        Collections.sort(total);
        for(int i=0;i <total.size();i++){
            ans.add(map.get(total.get(i).music).get(0).index);
            if(map.get(total.get(i).music).size()>=2)
            ans.add(map.get(total.get(i).music).get(1).index);
        }
        int[] answer = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}