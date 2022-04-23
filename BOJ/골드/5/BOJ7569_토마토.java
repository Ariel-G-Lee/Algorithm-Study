import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569_토마토 {
	static int N, M, H, untomato, ans;
	static int[][][] map;
	static Queue<Pos> tomato;
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
		
		st = new StringTokenizer(br.readLine());
		
		tomato = new LinkedList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][M][N];
		
		
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if(map[h][i][j] == 1) tomato.offer(new Pos(h, i, j)); 
					else if(map[h][i][j] == 0) untomato++;
				}
			}
		}
		
		if(untomato != 0) {
			find();
		}
		
		System.out.println(ans);
		
	}
	// 위 아래, 왼 오, 뒤 앞
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 0, -1, 1};
	private static void find() {
		
		while(!tomato.isEmpty()) {
			
			if(untomato == 0) return;
			int cnt = tomato.size();
			ans++;
			while(cnt --> 0) {
				Pos cur = tomato.poll();

				for (int d = 0; d < 6; d++) {
					int nz = cur.z+dz[d];
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(nz<0 || ny<0 || nx<0 || nz>=H || ny>=M || nx>=N || map[nz][ny][nx] != 0) continue;
					
					untomato--;
					map[nz][ny][nx] = 1;
					tomato.offer(new Pos(nz, ny, nx));
				}				

			}
		}
		if(untomato != 0) ans = -1;
		
	}
}
