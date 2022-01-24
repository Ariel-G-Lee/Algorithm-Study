package part3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ3085_사탕게임 {
	static int N, ans;
	static char[][] map;
	static int[] dy = {1, 0}, dx = {0, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String tmpStr = br.readLine();
			map[i] = tmpStr.toCharArray();
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				// 상하/좌우 swap
				for (int d = 0; d < 2; d++) {
					// 범위를 넘어가는 경우 넘어간다.
					if(i+dy[d] >= N || j+dx[d] >= N) continue; 
						
					swap(i, j, d);
					
					find();
					
					swap(i, j, d);
				}
			}
		}
		System.out.println(ans);
		
	}
	private static void swap(int i, int j, int d) {
		char tmp = map[i][j];
		map[i][j] = map[i+dy[d]][j+dx[d]];
		map[i+dy[d]][j+dx[d]] = tmp;
		
	}
	private static void find() {
		int tmpCnt = 0;
		
		// 아래로 검사
		for (int j = 0; j < N; j++) {
			// 시작 카운트
			tmpCnt = 1;
			for (int i = 1; i < N; i++) {
				if(map[i-1][j] == map[i][j]) tmpCnt++;
				else {
					ans = Math.max(tmpCnt, ans);
					tmpCnt = 1;
				}
			}
			// 한줄을 다 돌았을때 최대 개수 저장
			ans = Math.max(tmpCnt, ans);
		}
		
		// 오른쪽으로 검사
		for (int i = 0; i < N; i++) {
			tmpCnt = 1;
			for (int j = 1; j < N; j++) {
				if(map[i][j-1] == map[i][j]) tmpCnt++;
				else {
					ans = Math.max(tmpCnt, ans);
					tmpCnt = 1;
				}
			}
			ans = Math.max(tmpCnt, ans);
		}
		
	}
}
