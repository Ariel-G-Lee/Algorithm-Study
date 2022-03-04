package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12026_BOJ거리 {
	static int N, ans;
	static char[] map;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N];
		dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		map = br.readLine().toCharArray();
		
		dp[0] = 0;
		for (int i = 0; i < N-1; i++) {
			char cur = map[i];
			switch(cur) {
			case 'B':
				for (int j = i+1; j < N; j++) {
					if(map[j] == 'O') {
						cal(i, j);
					}
				}
				break;
			case 'O':
				for (int j = i+1; j < N; j++) {
					if(map[j] == 'J') {
						cal(i, j);
					}
				}
				break;
			case 'J':
				for (int j = i+1; j < N; j++) {
					if(map[j] == 'B') {
						cal(i, j);
					}
				}
				break;
			}
		}
		
		ans = dp[N-1];
		if(dp[N-1] == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}
	private static void cal(int i, int j) {
		dp[j] = (int) Math.min(dp[j], dp[i]+Math.pow(j-i, 2));
	}
	
}
