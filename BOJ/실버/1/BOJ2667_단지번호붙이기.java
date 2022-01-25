package part3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2667_단지번호붙이기 {
	static int n, total;
	static int[][] map;
	static ArrayList<Integer> list;
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
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) == '1' ? -1 : 0;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] != -1) continue;
				// 하나의 단지 추가
				total++;
				find(i, j);
			}
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(total).append("\n");
		for (Integer idx : list) {
			sb.append(idx).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	private static void find(int i, int j) {
		// i, j를 기준으로 들어가니까 초기 카운트는 1
		int cnt = 1;
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(i, j));
		// 방문한 곳은 0으로 바꾼다 (visit 사용대신)
		map[i][j] = 0;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				// 범위를 벗어났고 방문했거나 -1이 아닌 곳이면 넘어간다.
				if(ny<0 || nx<0 || ny>=n || nx>=n || map[ny][nx] == 0) continue;
				q.offer(new Pos(ny, nx));
				// 단지 내의 집을 추가
				cnt++;
				// 방문 표시
				map[ny][nx] = 0;
			}
		}
		// 해당 단지의 집 개수를 list에 넣는다
		list.add(cnt);
		
	}
}
