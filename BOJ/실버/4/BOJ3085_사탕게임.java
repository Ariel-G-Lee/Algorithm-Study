import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ3085_사탕게임 {
	static int N, ans;
	static char[][] map;
	// 오른쪽, 아래쪽
	static int[] dy = {0, 1};
	static int[] dx = {1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 2; d++) {
					// 범위에 들어오는지 체크
					if(i+dy[d]<0 || i+dy[d]>=N || j+dx[d]<0 || j+dx[d]>=N) continue;
					
					// 일단 인접한 오른쪽, 아래쪽이란 교환
					char tmp = map[i][j];
					map[i][j] = map[i+dy[d]][j+dx[d]];
					map[i+dy[d]][j+dx[d]] = tmp;
					findCandy();
					// 원래대로 돌리기
					tmp = map[i][j];
					map[i][j] = map[i+dy[d]][j+dx[d]];
					map[i+dy[d]][j+dx[d]] = tmp;
				}
			}
		}
		
		System.out.println(ans);
		
		
	}
	private static void findCandy() {
		// 그 칸에서부터 행-열로 다 확인(최대값 확인)
		int cnt = 0;
		// 행
		for (int i = 0; i < N; i++) {
			// 1개는 고정으로 들어가고 뒤에서 비교 들어가니까 1
			cnt = 1;
			for (int j = 1; j < N; j++) {
				// 앞의 색과 일치한다면 카운트를 올린다.
				if(map[i][j-1] == map[i][j]) cnt++;
				// 앞의 색과 다르다면 현재까지 카운트를 대소 비교해서 저장하고
				// 카운트를 1로 초기화
				else {
					ans = Math.max(ans, cnt);
					cnt = 1;
				}
			}
			ans = Math.max(ans, cnt);
		}
		
		for (int j = 0; j < N; j++) {
			// 1개는 고정으로 들어가고 뒤에서 비교 들어가니까 1
			cnt = 1;
			for (int i = 1; i < N; i++) {
				// 앞의 색과 일치한다면 카운트를 올린다.
				if(map[i-1][j] == map[i][j]) cnt++;
				// 앞의 색과 다르다면 현재까지 카운트를 대소 비교해서 저장하고
				// 카운트를 1로 초기화
				else {
					ans = Math.max(ans, cnt);
					cnt = 1;
				}
			}
			ans = Math.max(ans, cnt);
		}
		
	}
}
