import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1309_동물원 {
	static int N, ans;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][3];
		
		// dp[i][0] : 해당 줄에 안놓는 경우
		// dp[i][1] : 줄의 왼쪽에 놓는 경우
		// dp[i][2] : 줄의 오른쪽에 놓는 경우
		// 첫줄에 사자를 배치하는 경우
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for (int i = 2; i <= N; i++) {
			// 이번 줄에 안놓는다면 앞 줄이 어떤 조건이든 상관 없음
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%9901;
			// 이번 줄의 왼쪽에 놓는다면 앞 줄에 안놓았거나 오른쪽에 놓아져 있어야함
			dp[i][1] = (dp[i-1][0] + dp[i-1][2])%9901;
			// 이번 줄의 오른쪽에 놓는다면 앞 줄에 안놓았거나 왼쪽에 놓아져 있어야함
			dp[i][2] = (dp[i-1][0] + dp[i-1][1])%9901;
		}
		
		ans = (dp[N][0] + dp[N][1] + dp[N][2])%9901;
		
		System.out.println(ans);
		
		
	}
}
