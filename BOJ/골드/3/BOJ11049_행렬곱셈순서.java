package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049_행렬곱셈순서 {
	static int N;
	static int[][] arr, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		// A에서 B까지 계산했을 때 최소값
		dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i==j) continue;
				else dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// 간격
		for (int k = 1; k < N; k++) {
			// 시작점
			for (int j = 0; j+k < N; j++) {
				int start = j;
				int end = j+k;
				// A에서 i + i+1에서 B + A*i*B
				for (int i = start; i < end; i++) {
					int tmp = dp[start][i] + dp[i+1][end] + arr[start][0]*arr[i][1]*arr[end][1];
					dp[start][end] = Math.min(dp[start][end], tmp);
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
		
	}

}
