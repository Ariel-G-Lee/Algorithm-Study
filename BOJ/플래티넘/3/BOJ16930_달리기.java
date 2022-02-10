package DFSnBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16930_달리기 {
	static int N, M, K, sy, sx, ey, ex, ans;
	static char[][] map;
	static StringTokenizer st;

	static class Pos {
		int y;
		int x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j + 1] = str.charAt(j);
			}

		}

		st = new StringTokenizer(br.readLine());

		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());

		find();

		System.out.println(ans);
	}

	private static void find() {
		Queue<Pos> q = new LinkedList<>();
		int[][] visited = new int[N + 1][M + 1];

		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, -1, 0, 1 };

		q.offer(new Pos(sy, sx));

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			for (int d = 0; d < 4; d++) {
				// 최대 K 만큼 이동
				for (int i = 1; i <= K; i++) {
					int ny = cur.y + dy[d] * i;
					int nx = cur.x + dx[d] * i;
					if (ny < 1 || nx < 1 || ny > N || nx > M || map[ny][nx] == '#')
						break;
					if (visited[ny][nx] == 0) {
						q.offer(new Pos(ny, nx));
						visited[ny][nx] = visited[cur.y][cur.x] + 1;
						if (ny == ey && nx == ex) {
							ans = visited[ny][nx];
							return;
						}
					} else if (visited[ny][nx] <= visited[cur.y][cur.x]) { 
						// 같은 방향을 다시 도는 것을 방지하기 위해서
						// 가려고 하는 쪽이 현재보다 더 작은 시간으로 방문했다면 가지 않고 멈춘다
						// 이미 방문한 쪽일 것이기때문에!
						break;
					}
				}

			}
		}

		ans = -1;

	}
}
