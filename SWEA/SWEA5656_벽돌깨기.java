import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656_벽돌깨기 {
	static int T, N, W, H, total, ans;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int[][] map = new int[H][W];
			total = 0;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) total++;
				}
			}
			ans = Integer.MAX_VALUE;
			find(0, map, total);
			if(ans == Integer.MAX_VALUE) System.out.println("#"+t+" "+0);
			else System.out.println("#"+t+" "+ans);
		}
	}

	private static void find(int cnt, int[][] map, int remain) {
		if (cnt == N) {
			ans = Math.min(ans, remain);
			return;
		}

		int[][] copy = new int[H][W];

		// 열 별로 최고 위치를 찾아서 연속적으로 벽돌깨기
		for (int c = 0; c < W; c++) {
			
			int top = 0;
			// 맨 위의 index 찾기
			while (top < H && map[top][c] == 0) top++;
			
			// 해당 열에 벽돌이 하나도 없을 경우
			if (top == H) continue;
			copyArr(copy, map);

			// 벽돌을 깨러간다~ 깬 벽돌 수
			int bBrick = breakBrick(copy, top, c);

			// 이제 빈칸을 정리!
			relocate(copy);
			
			// 다음 단계 벽돌 깨기
			// 현재 배열과 남은 벽돌 개수 전달
			find(cnt+1, copy, remain-bBrick);

		}
	}

	private static void relocate(int[][] map) {
		for (int c = 0; c < W; c++) {
			
			// 가장 아래에서부터 정렬해 올라가기
			int bottom = H-1;
			
			while(bottom>0) {
				// 0을 만났다면 위에 있는지 확인해서 당긴다!
				if(map[bottom][c] == 0) {
					
					int nb = bottom-1;
					// 0이 아닌 숫자를 찾을 때까지
					while(nb>0 && map[nb][c] == 0) nb--;
					
					// 찾아서 끌어다 넣는다
					map[bottom][c] = map[nb][c];
					map[nb][c] = 0;
				}
				bottom--;
			}
		}

	}

	private static int breakBrick(int[][] map, int top, int c) {
		// 깬 벽돌 갯수 저장
		int brick = 0;

		Queue<Brick> q = new LinkedList<>();

		if (map[top][c] > 1) q.offer(new Brick(top, c, map[top][c]));
		// 깬 벽돌 표시
		map[top][c] = 0;

		brick++;

		while (!q.isEmpty()) {
			Brick cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = cur.y;
				int nx = cur.x;
				for (int d = 0; d < cur.d - 1; d++) {
					ny += dy[i];
					nx += dx[i];
					if (ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == 0) continue;

					q.offer(new Brick(ny, nx, map[ny][nx]));
					map[ny][nx] = 0;
					brick++;
				}
			}
		}
		return brick;
	}

	private static void copyArr(int[][] copy, int[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copy[i][j] = map[i][j];
			}
		}

	}

	static class Brick {
		int y;
		int x;
		int d;

		public Brick(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	}

}
