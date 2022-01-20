package part3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2294_동전2 {
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
		
		Arrays.fill(dp, 1000000);
		
		dp[0] = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				// 동전의 개수이므로 해당 금액을 만드는
				// 현재까지의 최소 개수와 동전의 개수+1 중 작은 것을 고른다.
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
			}
		}
		
		System.out.println(dp[k] == 1000000 ? -1 : dp[k]);
	}
}
