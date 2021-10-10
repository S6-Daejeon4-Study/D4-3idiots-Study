package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author comkkyu
 * @date 21. 10. 3
 *
 * 백준 8979 - 올림픽
 * https://www.acmicpc.net/problem/8979
 *
 * 금메달, 은메달, 동메달의 개수를 순서로 내림차순으로 정렬한뒤 해당 국가부터 rank 를 구하기위한 연산을 수행해서 해결했다.
 * 모든 메달의 개수가 동일할때는 rank가 그대로이고 그외의경우 ( 자신보다 잘한 국가의 수 ) + 1 이므로 현재까지 파악한 자기 이전에 있는
 * 국가의 수인 i 에 1 을 더함으로써 rank 를 구해주었다.
 *
 */
public class B8979_올림픽 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<Nation> nations;
        int N, K;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nations = new ArrayList<>(); // 1 ~ N 번 국가에 대한 정보를 저장하기 위해서 리스트 생성

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nations.add(new Nation(n, new Medal(g, s, b)));
        }

        Comparator<Nation> nationComparator = new Comparator<Nation>() {

            @Override
            public int compare(Nation o1, Nation o2) {
                return o1.medal.compareTo(o2.medal);
            }
        };

        Collections.sort(nations, nationComparator);

        nations.get(0).rank = 1;
        int idx = 0;

        for (int i = 1; i < N; i++) {
            Nation prev = nations.get(i - 1);
            Nation cur = nations.get(i);

            if (cur.no == K) {
                idx = i;
            }

            if (cur.medal.gold == prev.medal.gold && cur.medal.silver == prev.medal.silver
                && cur.medal.bronze == prev.medal.bronze) {
                cur.rank = prev.rank;
                continue;
            }

            cur.rank = i + 1;
        }

        System.out.println(nations.get(idx).rank);
    }

    static class Nation {
        int no; // 국가 번호
        Medal medal; // 국가가 소유한 금, 은, 동 메달 정보
        int rank; // 국가 순위
        
        public Nation(int no, Medal medal) {
            this.no = no;
            this.medal = medal;
        }
    }

    static class Medal implements Comparable<Medal> {
        int gold; // 금메달 개수
        int silver; // 은메달 개수
        int bronze; // 동메달 개수

        public Medal(int gold, int silver, int bronze) {
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Medal o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    return o.bronze - this.bronze;
                } else {
                    return o.silver - this.silver;
                }
            } else {
                return o.gold - this.gold;
            }
        }
    }
}
