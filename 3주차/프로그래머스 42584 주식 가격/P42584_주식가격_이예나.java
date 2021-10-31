package study_3week;

class P42584_주식가격_이예나 {
    public int[] solution(int[] prices) {
        
        int [] answer =  new int [prices.length];
        for(int i=0;i<prices.length;i++){
            boolean check=true;
            for(int j=i+1;j<prices.length;j++){
                if(prices[j]<prices[i]){
                    check=false;
                    answer[i]=j-i;
                    break;
                }
            }
            if(check)
             answer[i]=prices.length-i-1;
        }
       
        return answer;
    }
}
