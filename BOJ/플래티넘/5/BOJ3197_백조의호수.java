package Simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3197_백조의호수 {
	static int R, C, ans;
	static Pos[] swan;
	static char[][] map;
	static Queue<Pos> water;
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		water = new LinkedList<>();
		
		swan = new Pos[2];
		
		int idx = 0;
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '.') 
					water.offer(new Pos(i, j));
				else if(map[i][j]=='L') {
					swan[idx++] = new Pos(i,j);
					water.offer(new Pos(i, j));
					map[i][j] = '.';
				}
			}
		}
		
		find();
		
		System.out.println(ans);
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find() {
		boolean[][] visited = new boolean[R][C];
		Queue<Pos> q = new LinkedList<>();
		q.offer(swan[0]);
		visited[swan[0].y][swan[0].x] = true;
		
		int cnt = 0;
		
		while(true) {
			Queue<Pos> next = new LinkedList<>();
			while(!q.isEmpty()) {
				Pos cur = q.poll();
				
				if(cur.y == swan[1].y && cur.x == swan[1].x) {
					ans = cnt;
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=R || nx>=C) continue;
					if(visited[ny][nx]) continue;
					
					visited[ny][nx] = true;

					// 물 근접 지역으로 이제 녹을 곳 -> 다음에 백조가 탐색할 장소
					if(map[ny][nx] == 'X') {
						next.offer(new Pos(ny, nx));
						continue;
					}
					
					q.offer(new Pos(ny, nx));
				}
			}
			// 다음날 탐색할 지점
			q = next;
			
			
			int size = water.size();
			while(size --> 0) {
				Pos cur = water.poll();
				
				for (int d = 0; d < 4; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=R || nx>=C) continue;
					if(map[ny][nx] == 'X') {
						map[ny][nx] = '.';
						water.offer(new Pos(ny, nx));
					}
				}
			}
			cnt++;
		}
		
		
	}
}
