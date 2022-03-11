package Preq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16197_두동전 {
	static int N, M, ans;
	static char[][] map;
	// 위 오 아래 왼
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		int y1 = 0;
		int x1 = 0;
		int y2 = 0;
		int x2 = 0;
		
		ans = Integer.MAX_VALUE;
		
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'o') {
					if(cnt == 0) {
						y1 = i;
						x1 = j;
						cnt++;
					} else {
						y2 = i;
						x2 = j;
					}
				}
			}
		}
		
		find(0, y1, x1, y2, x2);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	private static void find(int cnt, int y1, int x1, int y2, int x2) {
		
		if(cnt >= ans || cnt>=10) return;

		for (int d = 0; d < 4; d++) {
			int ny1 = y1+dy[d];
			int nx1 = x1+dx[d];
			int ny2 = y2+dy[d];
			int nx2 = x2+dx[d];
			
			int outCnt = 0;
			if(ny1<0 || nx1<0 || ny1>=N || nx1>=M) outCnt++;
			if(ny2<0 || nx2<0 || ny2>=N || nx2>=M) outCnt++;
			
			if(outCnt == 2) {
				continue;
			}
			
			if(outCnt == 1) {
				ans = Math.min(ans, cnt+1);
				return;
			}
			
			if(map[ny1][nx1] == '#') {
				ny1 = y1;
				nx1 = x1;
			}
			if(map[ny2][nx2] == '#') {
				ny2 = y2;
				nx2 = x2;
			}
			
			find(cnt+1, ny1, nx1, ny2, nx2);
		}
		
	}
}
