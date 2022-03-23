package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2579_계단_오르기_이예나 {
	static int n;
	static int []stare;
	static int []dp;
	public static void main(String[] args) throws NumberFormatException, IOException {

		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		stare = new int[n+1];
		dp = new int [n+1];
		for(int i=1;i<=n;i++) {
			stare[i]=Integer.parseInt(bf.readLine());
		}
		dp[0]=0;
		dp[1]=stare[1];
		if(n>=2)
		dp[2]=dp[1]+stare[2];
		if(n>=3)
		dp[3]=Math.max(stare[1], stare[2])+stare[3];
		for(int i=4;i<=n;i++) {
			dp[i]=Math.max(dp[i-3]+stare[i-1], dp[i-2])+stare[i];
		}

		System.out.println(dp[n]);
	}

}
