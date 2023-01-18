import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638_치즈 {
	static int N, M, cheeseCnt, ans;
	static int[][] map;
	static StringTokenizer st;
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
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}
		
		while(cheeseCnt != 0) {
			findCheese();
			meltingCheese();
			ans++;
		}
		
		System.out.println(ans);
		
	}
	
	private static void meltingCheese() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] > 2) {
					map[i][j] = 0;
					cheeseCnt--;
				} else if (map[i][j] == 2) {
					map[i][j] = 1;
				}
			}
		}
		
	}

	private static void findCheese() {
		Queue<Pos> air = new LinkedList<>();
		visited = new boolean[N][M];
		air.offer(new Pos(0, 0));

		
		while(!air.isEmpty()) {
			Pos cur = air.poll();
			
			if(visited[cur.y][cur.x]) continue;
			visited[cur.y][cur.x] = true;
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx]) continue;
				if(map[ny][nx] != 0) { // 치즈라면 인접한 공기 수 증가
					map[ny][nx]++;
				} else if (map[ny][nx] == 0) { // 공기라면 방문처리하고 넣기
					air.offer(new Pos(ny, nx));
				}

			}
		}
	}
}
