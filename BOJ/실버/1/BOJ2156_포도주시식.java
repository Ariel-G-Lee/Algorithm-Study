import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2156_포도주시식 {
	static int n;
	static int[] wine, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		wine = new int[n+1];
		dp = new int[n+1];
		for (int i = 1; i <= n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = wine[1]; // 한개일때 한개 마시는게 최대
		
		if(n>=2) {
			// 두개일때 두개 다 마시는게 최대
			dp[2] = wine[1]+wine[2];
			
			// 3개일때부터는 따져야함
			for (int i = 3; i <= n; i++) {
				// i-3까지의 최대 양 + i-1의 와인양 + 현재 i 와인 양
				// i-2까지의 최대 양 + 현재 i 와인의 양
				// i-1까지의 최대 양(i 와인 양 추가X)
				dp[i] = Math.max(dp[i-3]+wine[i-1]+wine[i], Math.max(dp[i-2]+wine[i], dp[i-1]));
			}
			
		}
		
		System.out.println(dp[n]);
		
	}
}
