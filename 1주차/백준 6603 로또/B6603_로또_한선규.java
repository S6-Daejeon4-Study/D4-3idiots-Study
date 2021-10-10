package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @date 21. 10. 3
 *
 * 백준 6603 - 로또
 * https://www.acmicpc.net/problem/6603
 *
 * k 개의 원소를 가지는 집합 S에서 6개의 수를 뽑는 조합의 수를 묻는 문제이다.
 * 재귀를 이용해서 kC6 가지의 경우의 수에 대해서 각각의 값을 StringBuilder 를 활용해서 문제에서 요구하는 출력의 형태로 만들어주었다.
 *
 */
public class B6603_로또 {

    private static int[] S, results;
    private static boolean[] selected;
    private static final int N = 6; // 6개의 번호를 뽑는다.
    private static int K;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            if (K == 0) break;

            S = new int[K];
            results = new int[N];
            selected = new boolean[K];

            for (int i = 0; i < K; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            comb(0,0);
            comb2(0, 0);

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void comb(int start, int cnt) { // 조합
        if (cnt == N) { // 6개의 수를 모두 뽑았다면
            for (int i = 0; i < N; i++) { // 문제에서 오름차순으로 출력을 요구했지만
                sb.append(results[i]).append(" "); // 입력이 오름차순으로 주어졌기 떄문에 별다른 처리없이 StrginBuilder 에 추가해주었다.
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < K; i++) {
            if (!selected[i]) {
                selected[i] = true;
                results[cnt] = S[i];
                comb(i + 1, cnt + 1);
                selected[i] = false;
            }
        }
    }

    private static void comb2(int target, int cnt) {
        if (cnt == N) {
            for (int i = 0; i < K; i++) {
                if (selected[i]) {
                    sb.append(S[i]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        if (target == K) {
            return;
        }

        selected[target] = true;
        comb2(target + 1, cnt + 1);

        selected[target] = false;
        comb2(target + 1, cnt);
    }
}
