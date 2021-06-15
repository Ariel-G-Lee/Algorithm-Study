import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추 {
	static int T, M, N, K, ans; // M 가로길이 N 세로길이 K 배추가 심어져있는 위치 개수 ans 필요한 지렁이 수
	static int[][] map;
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
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로길이
			N = Integer.parseInt(st.nextToken()); // 세로길이
			K = Integer.parseInt(st.nextToken()); // 배추가 심어져있는 위치 개수
			
			map = new int[N][M];
			
			for (int i = 0; i < K; i++) { // 배추가 심어져 있는 곳 1로 표시
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 1) { // 배추가 있는 곳이라면
						ans++;
						check(i, j); // 인접한 배추 찾기
					}
				}
			}
			
			System.out.println(ans);
			
		}
	}
	private static void check(int i, int j) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(i, j));
		map[i][j] = 0; // 방문표시
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) { // 사방 탐색하면서 인접한 배추 확인
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx] != 1) continue; // 범위를 넘거나 배추가 아니면 넘어간다.
				q.offer(new Pos(ny, nx));
				map[ny][nx] = 0; // 방문 표시
			}
		}
		
	}
}
