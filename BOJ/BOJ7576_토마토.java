package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_토마토 {
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static int M, N, ans, total;
	static int[][] tomato;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomato = new int[N][M];
		visit = new boolean[N][M];
		Queue<Pos> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if(tomato[i][j] ==1) {
					q.offer(new Pos(i, j));
				} else if (tomato[i][j] == 0) total++;
			}
		}

		
		while(!q.isEmpty()) {
			ans++;
			int size = q.size();

			while(size-->0) {
				Pos cur = q.poll();
				int y = cur.y;
				int x = cur.x;
				
				if(visit[y][x]) continue;
				visit[y][x] = true;

				
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if(ny<0 || ny>=N || nx<0 || nx>=M || visit[ny][nx] || tomato[ny][nx] != 0) continue;
					
					tomato[ny][nx] = 1;
					total--;
					q.offer(new Pos(ny, nx));
				}

				
			}

		}
		if(total == 0) System.out.println(ans-1);
		else System.out.println(-1);
		
		
	}
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
}
