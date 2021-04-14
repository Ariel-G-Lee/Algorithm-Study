package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


// 1이 흰방 0이 검은방
public class BOJ2665_미로만들기 {
	static class Pos implements Comparable<Pos>{
		int y;
		int x;
		int cnt; // 여기까지 오는데 바꾼 벽의 수!
		public Pos(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pos o) {
			return this.cnt - o.cnt;
		}
		
		
	}
	static int N, ans;
	static int[][] map;
	static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		find();
		
		System.out.println(ans);
		
		
	}
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	private static void find() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		
		q.offer(new Pos(0,0,0));
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				if(ny<0 || nx<0 || ny>=N || nx>=N || visit[ny][nx]) continue;
				
				if(ny == N-1 && nx == N-1) {
					ans = cur.cnt;
					return;
				}
				// 검은방이라면 바꾼다고 생각하고 카운트를 올려서 넣는다
				if(map[ny][nx] == 0) {
					q.offer(new Pos(ny, nx, cur.cnt+1));
					visit[ny][nx] = true;
				} else { // 흰방이라면 그냥 넣는다
					q.offer(new Pos(ny, nx, cur.cnt));
					visit[ny][nx] = true;
				}
				
				
				
			}
			
		}
		
		
		
	}
}
