import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579_앱 {
	static int N, M, ans;
	static int[] mem;
	static int[] cost;
	static int[][] dp;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;
		
		mem = new int[N];
		cost = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N][10001];
		
		for (int i = 0; i < N; i++) {
			int m = mem[i];
			int c = cost[i];
			
			for (int j = 0; j <= 10000; j++) {
				if(i==0) {
					if(j>=c) dp[i][j] = m;
				}
				else {
					if(j>=c) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-c]+m);
					else dp[i][j] = dp[i-1][j];
				}
				if(dp[i][j] >= M) ans = Math.min(ans, j);
			}
			
			
		}
		System.out.println(ans);
		
	}
}
