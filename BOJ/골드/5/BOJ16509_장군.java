import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16509_장군 {
	static int ey, ex, sy, sx, ans;
	static boolean[][] visited;
	static StringTokenizer st;
	static int next[][][] = {
			// 상
			{{-1,0}, {-1,-1}, {-1,-1}},
			{{-1,0}, {-1,1}, {-1,1}},
			// 우
			{{0,1}, {-1,1}, {-1,1}},
			{{0,1}, {1,1}, {1,1}},
			// 하
			{{1,0}, {1,1}, {1,1}},
			{{1,0}, {1,-1}, {1,-1}},
			// 좌
			{{0,-1}, {1,-1}, {1,-1}},
			{{0,-1}, {-1,-1}, {-1,-1}}
	};
	static class Pos{
		int y;
		int x;
		int cnt;
		public Pos(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		ey = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		
		ans = -1;
		
		find();
		
		System.out.println(ans);
	}
	private static void find() {
		visited = new boolean[10][9];
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(sy, sx, 0));
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.y == ey && cur.x == ex) {
				ans = cur.cnt;
				return;
			}
			
			for (int d = 0; d < 8; d++) {
				int ny = cur.y;
				int nx = cur.x;
				boolean flg = true;
				for (int dx = 0; dx < 3; dx++) {
					ny += next[d][dx][0];
					nx += next[d][dx][1];
					if(ny<0 || ny>=10 || nx<0 || nx>=9 || (dx!=2 && ny == ey && nx == ex)) {
						flg = false;
						break;
					}
				}
				if(flg) {
					if(visited[ny][nx]) continue;
					q.offer(new Pos(ny, nx, cur.cnt+1));
				}
			}
		}
	}
}
