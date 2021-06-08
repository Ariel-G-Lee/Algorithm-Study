import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_탈출 {
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static int R, C, ans;
	static char[][] map;
	static boolean[][] visit;
	static Queue<Pos> water, dochi;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		
		water = new LinkedList<>();
		dochi = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					visit[i][j] = true;
					dochi.offer(new Pos(i, j));
				} else if (map[i][j] == '*') {
					water.offer(new Pos(i, j));
				}
			}
		}
		ans = -1;
		moveDochi();
		
		if(ans == -1) System.out.println("KAKTUS");
		else System.out.println(ans);
		
		
	}
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	private static void moveDochi() {
		int time = 0;
		int size = 0;
		while(!dochi.isEmpty()) {
			// 일단 물부터 채운다.(어차피 물이 퍼질 곳이라 이동 못함)
			size = water.size();
			while(size --> 0) {
				Pos cur = water.poll();
				for (int d = 0; d < 4; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=R || nx>=C || map[ny][nx] != '.') continue;
					map[ny][nx] = '*';
					water.offer(new Pos(ny, nx));
				}
			}
			size = dochi.size();
			while(size --> 0) {
				Pos cur = dochi.poll();
				if(map[cur.y][cur.x] == 'D') {
					ans = time;
					return;
				}
				for (int d = 0; d < 4; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=R || nx>=C || visit[ny][nx]) continue;
					if(map[ny][nx] == 'X' || map[ny][nx] == '*') continue;
					visit[ny][nx] = true;
					dochi.offer(new Pos(ny, nx));
				}
			}
			time++;
		}
		
	}
}
