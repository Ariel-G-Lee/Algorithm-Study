package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ5557_1학년 {
	static int N;
	static int[] num;
	static long[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		// 중간에 나오는 수는 0 이상 20이하
		// N-1 까지 계산을 해서 N의 값과 같은지 비교
		dp = new long[N-1][21];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][num[0]] = 1;
		for (int i = 1; i < N-1; i++) {
			for (int j = 0; j < 21; j++) {
				if(dp[i-1][j] != 0) { // 이전 단계에서 계산할 수 있는 수라면
					if(j+num[i] <= 20) dp[i][j+num[i]] += dp[i-1][j];
					if(j-num[i] >= 0) dp[i][j-num[i]] += dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N-2][num[N-1]]);
		
	}
}
