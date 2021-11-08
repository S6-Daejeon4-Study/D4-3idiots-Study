package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B12865_평범한배낭_이예나 {

	static class item{
		int weight; //한 아이템의 무게
		int worth; //한 아이템의 가치
		item(int we,int wo){
			this.weight = we;
			this.worth = wo;
		}
	}
	static int N,K;
	static item []bags; // 아이템들의 정보
	static int [][]sum; // knapsack 2차원 테이블
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bags = new item[N+1];
		sum = new int[N][K+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(bf.readLine());
			bags[i] = new item(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
		}
		
		for(int i=0;i<N;i++) {
			int nowWorth = bags[i].worth;
			int nowWeight = bags[i].weight;
			for(int j=1;j<=K;j++) {
				if(i==0 && nowWeight<=j) { //처음 아이템은 무게만 만족하면 넣을 수 있음
					sum[i][j]=nowWorth;
				}
				if(i!=0) {
					if(nowWeight<=j) { //넣을 수 있는 무게일 떄
						sum[i][j]=Math.max(sum[i-1][j], sum[i-1][j-nowWeight]+nowWorth); // 이전값과 비교해서 더 큰값을 삽입
					}
					else sum[i][j]=sum[i-1][j];
				}
			}
		}
		System.out.println(sum[N-1][K]);
	}

}
