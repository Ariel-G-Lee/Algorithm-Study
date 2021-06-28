import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2146_다리만들기 {
	static int N, ans;
	static int[][] map;
	static boolean[][] visit;
	static class Pos{
		int y;
		int x;
		int cnt; // 다리 길이를 저장할 변수
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		public Pos(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1; // -1로 임시 표기(나중에 라벨링하기 위해서)
			}
		}
		
		// 섬마다 숫자로 라벨링
		int label = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 섬이고 방문한 적이 없다면 labeling
				if(map[i][j] == -1 && !visit[i][j]) labeling(i, j, label++);
			}
		}
		
		
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) { // 바다가 아니라면
					visit = new boolean[N][N]; // 방문배열 초기화
					makeBridge(i, j); // 다리 만들기 시도
				}
			}
		}
		
		System.out.println(ans);

	}
	private static void makeBridge(int y, int x) {
		int landLabel = map[y][x];

		Queue<Pos> q = new LinkedList<>();
		
		q.offer(new Pos(y,x,0));
		visit[y][x] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				// 범위 밖, 이미 방문, 같은 섬이라면 continue
				if(ny<0 || ny>=N || nx<0 || nx>=N || visit[ny][nx] || map[ny][nx] == landLabel) continue;
				
				if(map[ny][nx] == 0) { // 바다라면 cnt를 하나 추가해서 큐에 넣는다
					q.offer(new Pos(ny, nx, cur.cnt+1)); 
				} else { // 다른 섬을 만났다면
					ans = Math.min(ans, cur.cnt);
					return;
				}
				visit[ny][nx] = true;
			}
		}
		
		
	}
	private static void labeling(int y, int x, int label) {
		Queue<Pos> q = new LinkedList<>();
		
		map[y][x] = label;
		q.offer(new Pos(y,x));
		visit[y][x] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				// 범위 밖, 이미 방문, 바다라면 continue
				if(ny<0 || ny>=N || nx<0 || nx>=N || visit[ny][nx] || map[ny][nx] == 0) continue;
				map[ny][nx] = label; // 같은 숫자로 labeling
				q.offer(new Pos(ny, nx)); // 큐에 넣고
				visit[ny][nx] = true; // 방문처리
			}
		}
		
	}
}
