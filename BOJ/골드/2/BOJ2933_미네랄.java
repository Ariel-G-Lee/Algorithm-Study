package Simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2933_미네랄 {
	static int R, C, N;
	static boolean dir;
	static char[][] map;
	static boolean[][] visited;
	static StringTokenizer st;
	static ArrayList<Pos> dropList;
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
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		N = Integer.parseInt(br.readLine());
		// false면 왼쪽, true면 오른쪽
		dir = false;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			if(!dir) { // 왼쪽
				for (int k = 0; k < C; k++) {
					if(map[R-h][k] == 'x') {
						map[R-h][k] = '.';
						break;
					}
				}
			} else { // 오른쪽
				for (int k = C-1; k >= 0; k--) {
					if(map[R-h][k] == 'x') {
						map[R-h][k] = '.';
						break;
					}
				}
			}
			
			visited = new boolean[R][C];
			dropList = new ArrayList<>();
			findCluster();
			if(!dropList.isEmpty()) downCluster();
			
			dir = !dir;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	private static void downCluster() {
		boolean downFlag = true;
		while(true) {
			if(!downFlag) break;
			
			for (Pos pos : dropList) {
				int ny = pos.y+1;
				int nx = pos.x;
				if(ny<0 || nx<0 || ny>=R || nx>=C || map[ny][nx]=='x') {
					downFlag = false;
					break;
				}
			}
			
			if(downFlag) {
				for (Pos pos : dropList) {
					pos.y = pos.y+1;
				}
			}
		}
		
		for (Pos pos : dropList) {
			map[pos.y][pos.x] = 'x';
		}
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};	
	private static void findCluster() {
		Queue<Pos> q = new LinkedList<>();
		
		// 바닥에 붙어있는 미네랄 찾기
		for (int i = 0; i < C; i++) {
			if(map[R-1][i] == 'x') {
				q.offer(new Pos(R-1, i));
				visited[R-1][i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				if(ny<0 || nx<0 || ny>=R || nx>=C || visited[ny][nx] || map[ny][nx] == '.') continue;
				q.offer(new Pos(ny, nx));
				visited[ny][nx] = true;
			}
		}
		
		// 바닥에 붙어있지 않은 미네랄 넣기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visited[i][j] && map[i][j] == 'x') {
					map[i][j] = '.';
					dropList.add(new Pos(i, j));
				}
			}
		}
		
	}


}
