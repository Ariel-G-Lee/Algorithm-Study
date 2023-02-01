import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14923_미로탈출 {
	static int N, M, Hy, Hx, Ey, Ex, ans;
	static int[][] map;
	static StringTokenizer st;
	static class Pos{
		int y;
		int x;
		int flag;
		public Pos(int y, int x, int flag) {
			super();
			this.y = y;
			this.x = x;
			this.flag = flag;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		Hy = Integer.parseInt(st.nextToken())-1;
		Hx = Integer.parseInt(st.nextToken())-1;
		
		st = new StringTokenizer(br.readLine());
		
		Ey = Integer.parseInt(st.nextToken())-1;
		Ex = Integer.parseInt(st.nextToken())-1;
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
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
		boolean[][][] visited= new boolean[N][M][2];
		q.offer(new Pos(Hy, Hx, 0));
		visited[Hy][Hx][0] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size --> 0) {
				Pos cur = q.poll();
				
				if(cur.y == Ey && cur.x == Ex) {
					ans = cnt;
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
					if(!visited[ny][nx][1] && map[ny][nx] == 1 && cur.flag == 0) {
						q.offer(new Pos(ny, nx, 1));
						visited[ny][nx][1] = true;
					} else if(!visited[ny][nx][cur.flag] && map[ny][nx] == 0) {
						q.add(new Pos(ny, nx, cur.flag));
						visited[ny][nx][cur.flag] = true;
					}

				}
			}
			cnt++;
		}
		
	}
}
