package com.ssafy.pro;

/**
 * @author comkkyu
 * @since 21. 10. 9
 *
 * 프로그래머스 12904 - 가장 긴 팰린드롬
 * https://programmers.co.kr/learn/courses/30/lessons/12904
 *
 */
public class P12904_가장긴팰린드롬_한선규 {

    public int solution(String s)
    {
        int len = s.length();

        for (int palin = len; palin > 1; palin--) { // 팰린드롬의 길이를 문자열의 길이부터 하나씩 감소해서 최대길이를 찾는다
            for (int start = 0; start <= len - palin; start++) { // 팰린드롬의 길이가 len 일때 검사를 시작할 start는 문자열전체길이 - 팰린드롬의길이까지가 유효한 범위가 된다.
                int end = start + palin - 1; // start 를 기준으로 길이가 len인 문자열의 end 위치
                int mid = (start + end) / 2; // start 를 기준으로 mid 값까지만 검사하면 된다.

                if (palin % 2 == 0) mid++; // 팰린드롬의 길이가 짝수일 때는 mid + 1

                boolean isPalindrome = true;

                for (int i = start; i < mid; i++) {
                    if (s.charAt(i) != s.charAt(end--)) { // 왼쪽과 오른쪽의 문자를 하나씩 앞당기면서 비교
                        isPalindrome = false; // 하나라도 시작과 끝의 문자중에 다른게 존재한다면 팰린드롬이 아니다.
                        break; // 연산횟수를 줄이기 위해 바로 반복문을 빠져나와서 다음 start 에 대해서 검사할 수 있도록 한다.
                    }
                }

                if (isPalindrome) return palin;
            }
        }

        return 1; // 가장작은 팰린드롬의 길이는 자기자신이 부분수열이 되는 경우
    }

}
