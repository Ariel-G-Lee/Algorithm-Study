import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593_상범빌딩 {
	static int L, R, C, sz, sy, sx, ans;
	static char[][][] map;
	static boolean[][][] visit;
	// 동 서 남 북 상 하
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dx = {1, -1, 0, 0, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	static StringTokenizer st;
	static class Pos{
		int z;
		int y;
		int x;
		public Pos(int z, int y, int x) {
			super();
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L+R+C == 0) break;
			
			map = new char[L][R][C];
			visit = new boolean[L][R][C];
			
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String str = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = str.charAt(k);
						if(map[i][j][k] == 'S') {
							sz = i;
							sy = j;
							sx = k;
						}
					}
				}
				br.readLine();
			}
			
			ans = 0;
			
			escape();
			
			if(ans == 0) System.out.println("Trapped!");
			else System.out.println("Escaped in "+ans+" minute(s).");
			
			
		}
		
		
		
	}
	private static void escape() {
		Queue<Pos> q = new LinkedList<>();
		
		q.offer(new Pos(sz, sy, sx));
		visit[sz][sy][sx] = true;
		
		int time = 0;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			while(size --> 0) {
				Pos cur = q.poll();
				
				if(map[cur.z][cur.y][cur.x] == 'E') {
					ans = time;
					return;
				}
				
				for (int d = 0; d < 6; d++) {
					int nz = cur.z+dz[d];
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					// 범위를 벗어나거나, 갈 수 없는 곳이거나, 이미 방문했다면 다음
					if(nz<0 || nz>=L || ny<0 || ny>=R || nx<0 || nx>=C || map[nz][ny][nx] == '#' || visit[nz][ny][nx]) continue;
					
					q.offer(new Pos(nz, ny, nx));
					visit[nz][ny][nx] = true;
				}
			}
			
			time++;
		}
		
		
	}
}
