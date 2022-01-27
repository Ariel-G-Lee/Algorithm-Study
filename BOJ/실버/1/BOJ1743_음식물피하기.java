package DFSnBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1743_음식물피하기 {
	static int N, M, K, ans;
	static boolean[][] map;
	static StringTokenizer st;
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
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][M+1];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = true;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(!map[i][j]) continue;
				find(i, j);
			}
		}
		
		System.out.println(ans);
	}
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find(int y, int x) {
		int cnt = 1;
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(y, x));
		map[y][x] = false;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				if(ny<0 || nx<0 || ny>N || nx>M || !map[ny][nx]) continue;
				
				cnt++;
				map[ny][nx] = false;
				q.offer(new Pos(ny, nx));
				
			}
		}
		
		ans = Math.max(ans, cnt);
		
	}
}
