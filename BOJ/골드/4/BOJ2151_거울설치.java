import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ2151_거울설치 {
	static int N, sy, sx, ans;
	static char[][] map;
	static class Light {
		int y;
		int x;
		int dir;
		int cnt;
		public Light(int y, int x, int dir, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '#' && !flag) {
					sy = i;
					sx = j;
					map[i][j] = '.';
					flag = true;
				}
			}
		}
		
		find();
		
		System.out.println(ans);
		
	}
	// 상 좌 하 우
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find() {
		PriorityQueue<Light> pq = new PriorityQueue<>((o1, o2)-> o1.cnt-o2.cnt);
		boolean[][][] visited = new boolean[N][N][4];
		
		// 4개의 진행 방향
		for (int d = 0; d < 4; d++) {		
			pq.offer(new Light(sy, sx, d, 0));
		}
		
		while(!pq.isEmpty()) {
			Light cur = pq.poll();
			
			if(map[cur.y][cur.x] == '#') {
				ans = cur.cnt;
				return;
			}
			
			if(visited[cur.y][cur.x][cur.dir]) continue;
			visited[cur.y][cur.x][cur.dir] = true;
			
			int ny = cur.y+dy[cur.dir];
			int nx = cur.x+dx[cur.dir];
			
			if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
			if(map[ny][nx] == '*') continue;
			
			if(map[ny][nx] == '!') {
				int ndir = -1;
				// / 모양으로 거울 두기
				ndir = changeDir(0, cur.dir);
				pq.offer(new Light(ny, nx, ndir, cur.cnt+1));
				// \ 모양으로 거울 두기
				ndir = changeDir(1, cur.dir);
				pq.offer(new Light(ny, nx, ndir, cur.cnt+1));
			}
			// 설치 안함
			pq.offer(new Light(ny, nx, cur.dir, cur.cnt));
		}
		
		
	}
	private static int changeDir(int mirrorDir, int dir) {
		int ndir = 0;
		if(mirrorDir == 0) {
			if(dir == 0) ndir = 3;
			else if(dir == 1) ndir = 2;
			else if(dir == 2) ndir = 1;
			else ndir = 0;
		} else {
			if(dir == 0) ndir = 1;
			else if(dir == 1) ndir = 0;
			else if(dir == 2) ndir = 3;
			else ndir = 2;
		}
		return ndir;
	}
}
