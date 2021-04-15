package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559_뿌요뿌요 {
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static char[][] map;
	static boolean[][] visit;
	static int crush, ans;
	static boolean check;
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[12][6];
		
		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		while(true) {
			visit = new boolean[12][6];
			
			check = false; // 해당번째에서 터졌는지를 체크!
			
			// 맨 아래부터 찾아가기
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if(map[i][j] != '.' && !visit[i][j]) {
						// 터질 것들을 찾는다
						find(i,j);
					}
				}
			}
			
			if(!check) break; // 터진게 없다면 stop
			else { // 터진게 있다면
				crush++; // 연쇄 증가
				down(); // 정렬
			}
			
		}
		System.out.println(crush);
		
	}
	private static void down() { // 끌어 내린다
		for (int i = 0; i < 6; i++) {
			int r = 11;
			
			while(r>0) {
				if(map[r][i] == '.') {
					int nr = r-1;
					while(nr>0 && map[nr][i] == '.') nr--;
					
					map[r][i] = map[nr][i];
					map[nr][i] = '.';
				}
				r--;
			}
			
			
		}
		
	}
	private static void find(int i, int j) {
		
		Queue<Pos> q = new LinkedList<>(); // 찾기위해 저장할 큐
		ArrayList<Pos> del = new ArrayList<>(); // 지워야할 것을 저장할 리스트
		
		char p = map[i][j]; // 비교 대상이 될 색상
		
		q.offer(new Pos(i,j));
		visit[i][j] = true;
		
		while(!q.isEmpty()) {
			Pos tmp = q.poll();
			del.add(new Pos(tmp.y, tmp.x));
			
			for (int d = 0; d < 4; d++) {
				int ny = tmp.y+dy[d];
				int nx = tmp.x+dx[d];
				if(ny<0 || nx<0 || ny>=12 || nx>= 6 || visit[ny][nx] || map[ny][nx]=='.') continue;
				if(map[ny][nx] == p) {					
					q.offer(new Pos(ny, nx));
					visit[ny][nx] = true;
				}
			}
		}
		
		if(del.size() >= 4) {
			check = true;
			for (Pos pos : del) {
				map[pos.y][pos.x] = '.';
			}
		}
		
		
		
	}
}
