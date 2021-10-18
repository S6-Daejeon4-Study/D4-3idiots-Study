package com.ssafy.pro;

/**
 * @author comkkyu
 * @since 21. 10. 16
 *
 * 프로그래머스 42584 - 주식가격
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 *
 */
public class P42584_주식가격_한선규 {

        public int[] solution(int[] prices) {
            int len = prices.length;
            int[] answer = new int[len];

            for (int i = 0; i < len; i++) {
                int price = prices[i];

                if (i == len-1) break; // 마지막 주식가격일때는 바로 반복문을 빠져나온다. (맨 마지막의 값은 항상 0)

                for (int j = i + 1; j < len; j++) {
                    // if (price <= prices[j])
                    //     answer[i]++;
                    // else {
                    //     answer[i]++;
                    //     break;
                    // }
                    answer[i]++; // 기본적으로 초를 증가시키고
                    if (price > prices[j]) // 가격이 떨어진 구간이었다면 반복문을 빠져나온다.
                        break;
                }
            }

            return answer;
        }
}
