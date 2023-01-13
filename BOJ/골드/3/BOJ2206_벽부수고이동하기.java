import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206_벽부수고이동하기{
	static int N, M, ans;
	static int[][] map;
	static StringTokenizer st;
	static class Pos{
		int y;
		int x;
		int wall;
		int cnt;
		public Pos(int y, int x, int wall, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.wall = wall;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		ans = -1;
		
		find();
		
		System.out.println(ans);
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][2];
		q.offer(new Pos(0, 0, 0, 1));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.y == N-1 && cur.x == M-1) {
				ans = cur.cnt;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if(map[ny][nx] == 0) { //  벽이 아니라면
					if(cur.wall == 0 && !visited[ny][nx][0]) { // 벽을 부수지 않고 여기까지 온적이 없다면
						q.offer(new Pos(ny, nx, 0, cur.cnt+1));
						visited[ny][nx][0] = true;
					} else if (cur.wall == 1 && !visited[ny][nx][1]) { // 벽을 부수고 여기까지 온적이 없다면
						q.offer(new Pos(ny, nx, 1, cur.cnt+1));
						visited[ny][nx][1] = true;
					}
				} else { // 벽이라면
					if(cur.wall == 0) { // 벽이 부순적이 없을 경우
						q.offer(new Pos(ny, nx, 1, cur.cnt+1)); // 벽부수고 이동
						visited[ny][nx][1] = true;
					}
				}
			}
		}
		
	}
}
