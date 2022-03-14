import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ16954_움직이는미로탈출 {
	static char[][] map = new char[8][8];
	static int ans;
	static PriorityQueue<Pos> wall = new PriorityQueue<>((o1, o2)->o2.y-o1.y);
	static PriorityQueue<Pos> tmpWall = new PriorityQueue<>((o1, o2)->o2.y-o1.y);
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
		
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '#') wall.add(new Pos(i, j));
			}
		}
		
		find();
		
		System.out.println(ans);
	}
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1, 0};
	private static void find() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(7, 0));

		while(!q.isEmpty()) {
			
			int size = q.size();
			// 캐릭터부터 이동
			while(size --> 0) {

				Pos cur = q.poll();
				
				// 현재 자리에 벽이 이동해 있다면 더 이상 움직일 수 없음
				if(map[cur.y][cur.x]=='#') continue;
				
				if(cur.y == 0 && cur.x == 7) {
					ans = 1;
					return;
				}

				for (int d = 0; d < 9; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=8 || nx>=8) continue;
					if(map[ny][nx]=='#') continue;
					
					q.offer(new Pos(ny, nx));
				}
			}
			
			// 벽 이동
			while(!wall.isEmpty()) {
				Pos cur = wall.poll();
				map[cur.y][cur.x] = '.';
				
				int ny = cur.y+1;
				int nx = cur.x;
				if(ny>=8) continue;
				tmpWall.offer(new Pos(ny, nx));
				map[ny][nx] = '#';
			}
			
			while(!tmpWall.isEmpty()) {
				Pos cur = tmpWall.poll();
				wall.offer(cur);
			}
			
		}
		
	}
	private static void moveWall() {
		for(int y = 7 ; y >= 0 ; y--) {
			for(int x = 0 ; x < 8 ; x++) {
				if(y == 0) map[y][x] = '.';
				else map[y][x] = map[y-1][x];
			}
		}
	}
}
