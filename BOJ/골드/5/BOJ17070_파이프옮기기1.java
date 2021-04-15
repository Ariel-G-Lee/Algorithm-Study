import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
	static int N, status, cnt;
	static int[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 좌표는 가장 오른쪽 아래좌표만 확인하면 됨
		int y = 1;
		int x = 2;
		find(y,x,0);
		System.out.println(cnt);
	}
	
	// 0 가로 1 세로 2 대각선
	static int[] dy = {0,1,1};
	static int[] dx = {1,0,1};
	public static void find(int y, int x, int status) {
		if(y == N && x == N) {
			cnt++;
			return;
		}
		
		
		for (int d = 0; d < 3; d++) {
			
			int ny = y+dy[d];
			int nx = x+dx[d];
			
			// status 가로 <-> 세로 상황에서는 이동 불가
			if((d==0 && status==1) || (d==1 && status==0)) continue;
		
			// 범위 밖으로 벗어나거나 벽인경우
			// 우측/아래로만 진행하므로 작은 경우는 확인 안해도 됨
			if(ny>N || nx>N || map[ny][nx] == 1) continue;
			
			// 대각선인데 현재 위치에서 오른쪽과 아래가 막혀있는경우(4칸이 차지됨)
			if(d==2 && (map[y+1][x] == 1 || map[y][x+1] == 1)) continue;
			
			
			find(ny, nx, d);
			
			
		}
	}
}
