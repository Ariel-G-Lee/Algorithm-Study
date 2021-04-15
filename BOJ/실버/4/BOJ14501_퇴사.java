package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501_퇴사 {
	static int N, max;
	static int[][] work;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		max = Integer.MIN_VALUE;
		
		work = new int[N+1][2];
		dp = new int[N+2];

		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			work[i][0] = Integer.parseInt(st.nextToken());
			work[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = N; i > 0; i--) {
			int next = i+work[i][0];
			// 현재 상담을 선택했을때 날짜를 벗어난다면
			if(next > N+1) {
				dp[i] = dp[i+1];
			} else {
				// 현재 날짜에 상담하지 않았을때의 이익과 / 현재 날짜에 상담했을 때의 이익 중 최대 이익
				dp[i] = Math.max(dp[i+1],dp[next]+work[i][1]);
			}
		}

		System.out.println(dp[1]);
		
	}
}
