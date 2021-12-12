package study_3week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4811_알약_이예나 {

	static long [][]dp;
	public static void main(String[] args) throws  IOException {

		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		dp = new long[32][32];
		makeDp();
		while(true) {
			int n = Integer.parseInt(bf.readLine());
			if(n==0) break;
			System.out.println(dp[n][n]);
			
		}
	}

	
	static void makeDp() {
		
		for(int h=0;h<=30;h++) {
			
			for(int w=0;w<=30;w++) {
				if(w<h) continue;
				if(h ==0) dp[w][h] =1;
				else dp[w][h] = dp[w-1][h]+dp[w][h-1];
				
			}
			
		}
		
	}
	
}
