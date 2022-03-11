package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2281_데스노트 {
	static int n, m, ans;
	static int[] num;
	static int[][] dp; // [인덱스][이름수]
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[n];
		dp = new int[1000][1002];
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		ans = find(1, num[0]+1);
		
		System.out.println(ans);
		
	}
	private static int find(int idx, int lineCnt) {
		if(idx == n) {
			// 마지막 라인의 남은 칸은 계산하지 않음
			return 0;
		}
		
		// 이미 구해져 있는 경우 반환
		if(dp[idx][lineCnt] != -1) return dp[idx][lineCnt];
		
		// 다음 줄에 쓰기
		int spare = m - lineCnt + 1; // 남은 칸의 수
		
		// 현재 남은 칸의 합 + 다음 줄들에서 돌아오는 합
		// 인덱스를 늘리고 들어가는 값은 (현재 이름+공백) 뿐
		dp[idx][lineCnt] = (int) (Math.pow(spare, 2) + find(idx+1, num[idx]+1));
		
		// 현재 줄에 쓰는 것이 가능하다면
		if(lineCnt + num[idx] <= m) {
			// 다음줄에 쓰는 것과 현재 줄에 쓰는 것 중 수가 작은 것을 넣는다.
			// 인덱스를 늘리고 들어가는 값은 (이전까지 작성한 이름들+현재 이름+공백)
			dp[idx][lineCnt] = Math.min(dp[idx][lineCnt], find(idx+1, lineCnt+num[idx]+1));
		}
		
		return dp[idx][lineCnt];
		
	}
}
