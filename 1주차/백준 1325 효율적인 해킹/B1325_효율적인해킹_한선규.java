package com.ssafy.bj;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author comkkyu
 * @date 21. 10. 3
 *
 * 백준 1325 - 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 *
 * 컴퓨터간의 신뢰도 관계를 리스트로 표현했다.
 * A 가 B 를 신뢰할 경우
 * list[B].add(A) 이렇게 표현했다.
 * 
 * 시간초과 문제로 해결하지 못했다..... ㅜㅜ
 */
public class B1325_효율적인해킹 {

    private static ArrayList<Integer>[] lists;
    private static int[] counts;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N, M, max;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = 0;
        lists = new ArrayList[N + 1];
        counts = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            lists[i] = new ArrayList<>(); // i 를 신뢰하는 컴퓨터 리스트
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            lists[to].add(from); // to 를 신뢰하는 from 
        }

        for (int i = 1; i <= N; i++) {
            cnt = 0;
//            dfs(i, new boolean[N+1]);
            bfs(i, new boolean[N+1]);
            counts[i] = cnt;
            max = Math.max(max, cnt);
        }

        for (int i = 1; i <= N; i++) {
            if (max == counts[i]) {
                bw.write(i+" ");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void dfs(int n, boolean[] visited) {
        visited[n] = true; // 방문 처리
        cnt++; // cnt 증가

        for (Integer next : lists[n]) { // n 을 신뢰하는 컴퓨터들 목록에 대해서
            if (!visited[next]) { // 아직 방문안한 컴퓨터라면 해당 컴퓨터를 기준으로 신뢰도 탐색
                dfs(next, visited); // 현재까지의 방문정보를 가지고 다음 탐색 시작
            }
        }
    }

    private static void bfs(int n , boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(n);
        visited[n] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;

            for (int next : lists[cur]) {
                if (!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

}