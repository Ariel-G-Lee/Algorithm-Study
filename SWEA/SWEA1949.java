import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1949 {
	static int N, K, max, min, maxh;
	static int[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1949_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			Queue<Pos> queue = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			maxh = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > maxh) {
						maxh = map[i][j];
						queue.clear();
						queue.offer(new Pos(i,j));
					} else if (map[i][j] == maxh) {
						queue.offer(new Pos(i,j));
					}
					
				}
			}
			
			max = Integer.MIN_VALUE;
			
			for (Pos pos : queue) {
				visit = new boolean[N][N];
				find(pos.y, pos.x, 1, false);
			}	
			System.out.println("#"+t+" "+max);
			
		}
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	public static void find(int y, int x, int cnt, boolean flag) {
		
		visit[y][x] = true;
		max = Math.max(max, cnt);

		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(!check(ny,nx) || visit[ny][nx]) continue;
			if(map[ny][nx] < map[y][x]) {
				find(ny, nx, cnt+1, flag);
			} else {
				if(!flag) {
					for (int i = 0; i <= K; i++) {
						if(map[ny][nx]-i < map[y][x]) {
							map[ny][nx] -= i;
							find(ny, nx, cnt+1, true);
							map[ny][nx] += i;	
						}
					}
				}
				
			}
		}
		visit[y][x] = false;
		
	}
	
	public static boolean check(int y, int x) {
		if(y>=0 && y<N && x>=0 && x<N) return true;
		return false;
	}
}

class Pos{
	int y;
	int x;
	public Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
