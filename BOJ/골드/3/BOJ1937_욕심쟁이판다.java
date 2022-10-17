import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp
public class BOJ1937_욕심쟁이판다 {
	static int N, ans;
	static int[][] map, dp;
	static class Pos{
		int y;
		int x;
		int a;
		public Pos(int y, int x, int a) {
			super();
			this.y = y;
			this.x = x;
			this.a = a;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, find(i, j));
			}
		}
		
		System.out.println(ans);
		
	}
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static int find(int i, int j) {
		if(dp[i][j] != 0) return dp[i][j]; // 이미 계산 된 부

		int maxCnt = 1;
		for (int d = 0; d < 4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			
			if(ny<0 || nx<0 || ny>=N || nx>= N) continue;
			if(map[i][j] < map[ny][nx]) {
				maxCnt = Math.max(find(ny, nx) + 1, maxCnt); // 찾아 내려가서 리턴받는
				dp[i][j] = maxCnt;
			}
		}
		return maxCnt;
	}

}
