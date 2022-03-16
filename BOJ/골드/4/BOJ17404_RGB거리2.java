import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404_RGB거리2 {
	static int N, ans;
	static int[][] map, dp;
	static final int INF = 1000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][3];
		dp = new int[N+1][3];
		
		ans = INF;
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < 3; i++) {
			// RED로 시작을 하는 경우 나머지 경우는 INF
			// GREEN로 시작을 하는 경우 나머지 경우는 INF
			// BLUE로 시작을 하는 경우 나머지 경우는 INF
			for (int j = 0; j < 3; j++) {
				if(i==j) dp[1][j] = map[1][j];
				else dp[1][j] = INF;
			}
			
			for (int j = 2; j <= N; j++) {
				dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + map[j][0];
				dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + map[j][1];
				dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + map[j][2];
			}
			
			// 첫번째 RED 를 칠했을 경우 -> 마지막에 BLUE, GREEN
			// 첫번째 GREEN 를 칠했을 경우 -> 마지막에 BLUE, RED
			// 첫번째 BLUE 를 칠했을 경우 -> 마지막에 RED, GREEN
			for (int j = 0; j < 3; j++) {
				if(i != j) ans = Math.min(ans, dp[N][j]);
			}
		}
		
		System.out.println(ans);
	}
}
