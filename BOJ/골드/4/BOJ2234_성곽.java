import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2234_성곽 {
	static int N, M, roomCnt, max, cMax;
	static int[][] map;
	static boolean[][] visit;
	static StringTokenizer st;
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
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
		
		map = new int[M][N];
		visit = new boolean[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(visit[i][j]) continue;
				max = Math.max(max,find(i, j));
				roomCnt++;
			}
		}
		
		sb.append(roomCnt).append("\n");
		sb.append(max).append("\n");
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int mask = 1; mask <= 8; mask<<=1) {
					if((map[i][j] & mask) == 0) continue;
					visit = new boolean[M][N];
					map[i][j] -= mask;
					cMax = Math.max(cMax, find(i, j));
					map[i][j] += mask;
				}
			}
		}
		sb.append(cMax).append("\n");
		System.out.println(sb);
		
	}
	private static int find(int i, int j) {
		int tmpSize = 1;
		Queue<Pos> q = new LinkedList<>();
		
		q.offer(new Pos(i, j));
		visit[i][j] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			int mask = 1;
			for (int d = 0; d < 4; d++) {
				// bit 연산을 했을때 0이 아니면 벽이 있다는것
				if((map[cur.y][cur.x] & mask) == 0) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					
					if(ny<0 || nx<0 || ny>=M || nx>=N || visit[ny][nx]) {
						mask<<=1;
						continue;
					}
					
					tmpSize++;
					visit[ny][nx] = true;
					q.offer(new Pos(ny, nx));
				}
				// shift 연산
				mask<<=1;
			}
		}
		
		return tmpSize;
		
	}
}
