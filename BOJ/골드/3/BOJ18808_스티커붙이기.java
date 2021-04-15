import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18808 {
	static int N, M, K, R, C, cnt;
	static boolean[][] board;
	static int[][] s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new boolean[N][M];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			s = new int[R][C];
			
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			put(0);
			
			
		}
		System.out.println(cnt);
		
	}
	static void put(int d) {
		if(d==4) return;
		
		for (int i = 0; i <= N-R; i++) {
			for (int j = 0; j <= M-C; j++) {
				if(check(i, j)) return;
			}
		}
		
		rotate();
		put(d+1);
	}
	
	static void rotate() {
		int[][] tmp = new int[C][R];
		
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				tmp[i][j] = s[R-1-j][i];
			}
		}
		
		int t = C;
		C = R;
		R = t;
		s = tmp;
	}
	
	static boolean check(int y, int x) {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(board[i+y][j+x] && s[i][j] == 1) {
					return false;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(s[i][j] == 1) {
					cnt++;
					board[i+y][j+x] = true;
				}
			}
		}
		
		return true;
		
	}
}
