import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190_뱀 {
	static int N, K, L, ans, dir, sy, sx;
	static int[][] map;
	static int[] time;
	static char[] rotate;
	static StringTokenizer st;
	static Queue<Pos> snake;
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	// 오른쪽 아래 왼쪽 위
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		snake = new LinkedList<>();
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			
			map[y][x] = 2;
		}
		
		L = Integer.parseInt(br.readLine());

		time = new int[L];
		rotate = new char[L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			time[i] = X;
			rotate[i] = C;
		}
		
		// 이동하다가 자기 몸에 부딪히면 끝
		// 사과를 먹으면 몸이 길어지고
		// 아니라면 몸이 길어지지 않고 이동
		// 게임이 몇초에 끝나는지 출력
		
		// 시작점
		snake.offer(new Pos(0,0));
		map[0][0] = 1;
		ans = 0;
		int idx = 0;
		while(true) {
			ans++;
			
			int ny = sy+dy[dir];
			int nx = sx+dx[dir];
			if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx] == 1) {
				System.out.println(ans);
				return;
			}
			
			sy = ny;
			sx = nx;
			snake.offer(new Pos(sy, sx));
			
			if (map[sy][sx] == 2) { // 사과를 만났다면 늘려
				map[sy][sx] = 1;
			} else { // 아니라면 이동만
				map[sy][sx] = 1;
				Pos tail = snake.poll();
				map[tail.y][tail.x] = 0;
			}
			
			if(idx<L && ans == time[idx]) {
				if(rotate[idx] == 'D') {
					dir = (dir+1)%4;
				} else {
					dir = (dir-1+4)%4;
				}
				idx++;
			}
		}
		
	}
	
}
