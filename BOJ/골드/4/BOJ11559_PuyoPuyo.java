import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559_PuyoPuyo {
	static char[][] map = new char[12][6];
	static int ans;
	static boolean[][] visited;
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		doGame();
		
		System.out.println(ans);
	}
	private static void doGame() {
		
		while(true) {
			int cnt = 0;
			visited = new boolean[12][6];

			for (int i = 11; i > 0; i--) {
				for (int j = 0; j < 6; j++) {
					if(map[i][j] != '.' && !visited[i][j]) {
						cnt += find(i, j, map[i][j]);
					}
				}
			}
			
			if(cnt==0) return;
			
			fall();
			
			ans++;
		}
		
		
	}
	private static void fall() {
		for (int i = 0; i < 6; i++) {
			int y = 11;
			
			while(y>0) {
				if(map[y][i] == '.') {
					int ny = y-1;
					while(ny>0 && map[ny][i] == '.') ny--;
					
					map[y][i] = map[ny][i];
					map[ny][i] = '.';
				}
				y--;
			}
		}	
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static int find(int sy, int sx, char c) {
		Queue<Pos> q = new LinkedList<>();
		ArrayList<Pos> boomList = new ArrayList<>();
		q.offer(new Pos(sy, sx));
		
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			boomList.add(new Pos(cur.y, cur.x));
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=12 || nx>=6) continue;
				if(visited[ny][nx] ||map[ny][nx] != c) continue;
				q.offer(new Pos(ny, nx));
				visited[ny][nx] = true;
			}
		}
		
		if(boomList.size()<4) return 0;
		
		for (Pos p : boomList) {
			map[p.y][p.x] = '.';
		}
		return 1;
	}
}
