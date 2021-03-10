import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144 {
	static int R, C, T;
	static int[][] map;
	static int[] cx, cy;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		cx = new int[2];
		cy = new int[2];
		
		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if(tmp == -1) {
					cx[idx] = i;
					cy[idx] = j;
					idx++;
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			
			int[][] plus = new int[R][C];

			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int cnt = 0;
					if(map[i][j] > 0) {
						for (int d = 0; d < 4; d++) {
							int ny = i+dy[d];
							int nx = j+dx[d];
							if(ny>=0 && ny<R && nx>=0 && nx<C && map[ny][nx] != -1 ) {
								plus[ny][nx] += map[i][j]/5;
								cnt++;
							}
						}
						map[i][j] -= map[i][j]/5*cnt;
					}
					

				}
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += plus[i][j];
				}
			}
			
			
			setClean();
			
		}
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1) sum+=map[i][j];
			}
		}
		
		System.out.println(sum);
	}
	public static void setClean() {
		for (int i = cx[0]-1; i > 0; i--) {
			map[i][0] = map[i-1][0];
		}
		
		for (int i = 0; i < C-1; i++) {
			map[0][i] = map[0][i+1];
		}
		
		for (int i = 0; i < cx[0]; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		
		for (int i = C-1; i >= 1; i--) {
			map[cx[0]][i] = map[cx[0]][i-1];
			if(i==1) map[cx[0]][i] = 0;
		}
		
		for (int i = cx[1]+1; i < R-1; i++) {
			map[i][0] = map[i+1][0];
		}
		
		for (int i = 0; i < C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		
		for (int i = R-1; i > cx[1]; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		
		for (int i = C-1; i >= 1; i--) {
			map[cx[1]][i] = map[cx[1]][i-1];
			if(i==1) map[cx[1]][i] = 0;
		}
		
		
	}
}
