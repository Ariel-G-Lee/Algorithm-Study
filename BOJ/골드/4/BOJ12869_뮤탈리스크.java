package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12869_뮤탈리스크 {
	static int N, ans;
	static int[] SCV;
	static int[][] attack = {{9,1,3}, {9,3,1}, {3,1,9}, {3,9,1}, {1,3,9}, {1,9,3}};
	static int[][][] dp;
	static boolean[][][][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		SCV = new int[3];
		
		// 최대 체력이 60
		dp = new int[61][61][61];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = Integer.MAX_VALUE;
		dfs(SCV, 0);
		
		System.out.println(ans);
	}
	private static void dfs(int[] arr, int cnt) {
		int scv1 = arr[0];
		int scv2 = arr[1];
		int scv3 = arr[2];
		
		// 이미 방문했는데 더 작은 수로 가능했다고 하면 retrun
		if(dp[scv1][scv2][scv3] != 0 && dp[scv1][scv2][scv3] <= cnt) {
			return;
		}
		dp[scv1][scv2][scv3] = cnt;
		
		if(scv1 == 0 && scv2 == 0 && scv3 == 0) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		for (int a = 0; a < 6; a++) {
			int nscv1 = scv1-attack[a][0];
			if(nscv1<0) nscv1 = 0;
			int nscv2 = scv2-attack[a][1];
			if(nscv2<0) nscv2 = 0;
			int nscv3 = scv3-attack[a][2];
			if(nscv3<0) nscv3 = 0;
			
			dfs(new int[] {nscv1, nscv2, nscv3}, cnt+1);
		}
		
	}
}
