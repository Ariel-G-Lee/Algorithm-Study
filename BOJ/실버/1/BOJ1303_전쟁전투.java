package DFSnBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1303_전쟁전투 {
	// n명이 뭉쳐 있으면 n^2의 위력을 낼 수 있다
	// 당신의 병사 : W, 적국의 병사 : B
	static int N, M, wPower, bPower;
	static char[][] map;
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
		// 가로
		N = Integer.parseInt(st.nextToken());
		// 세로
		M = Integer.parseInt(st.nextToken());
		
		map = new char[M][N];
		
		for (int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'W') wPower += Math.pow(find(i, j), 2);
				else if (map[i][j] == 'B') bPower += Math.pow(find(i, j), 2);
			}
		}
		
		System.out.println(wPower+" "+bPower);
		
	}
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static int find(int y, int x) {
		int cnt = 1;
		char letter = map[y][x];
		Queue<Pos> q = new LinkedList<>();
		map[y][x] = 'N';
		q.offer(new Pos(y, x));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				// 범위를 벗어나거나 같은 편이 아닐 경우 넘어간다.
				if(ny<0 || nx<0 || ny>=M || nx>=N || map[ny][nx] != letter) continue;
				q.offer(new Pos(ny, nx));
				map[ny][nx] = 'N';
				cnt++;
			}
			
		}
		return cnt;
	}
}
