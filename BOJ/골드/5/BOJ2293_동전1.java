package part3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293_동전1 {
	static int n, k;
	static int[] coin, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		coin = new int[n];
		dp = new int[k+1];
		
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		
		// 동전의 가지수를 하나씩 늘려가면서 방법을 찾는다.
		// 1  2  3  4  5  6  7  8  9 10
		// 1  1  1  1  1  1  1  1  1  1	- 1원짜리 사용시 방법은 1가지
		// 1  2  2  3  3  4  4  5  5  6
		// 위에서 2원짜리까지 사용시 5을 만드는 방법은 1원짜리만 사용 -> 현재 dp[5]
		// 3을 만들 수 있는 방법에 2를 더하면 5가 됨 -> dp[3]+2
		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				dp[j] += dp[j-coin[i]];
			}
		}
		
		System.out.println(dp[k]);
		
		
	}
}
