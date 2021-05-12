import java.util.LinkedList;
import java.util.Queue;

public class 게임맵최단거리 {
	public static void main(String[] args) {
		int[][] arr = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 }, { 0, 0, 0, 0, 1 } };
		int[][] arr2 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
		System.out.println(solution(arr2));
	}

	static class Pos {
		int y;
		int x;
		int cost;

		public Pos(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}

	
	static public int solution(int[][] maps) {
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		int answer = -1;
		int n = maps.length;
		int m = maps[0].length;
		boolean[][] visit = new boolean[n][m];
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0, 0, 1));
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.y==n-1 && cur.x==m-1) {
				answer = cur.cost;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=n || nx>=m) continue;
				if(maps[ny][nx] == 0 || visit[ny][nx]) continue;
				q.offer(new Pos(ny, nx, cur.cost+1));
				visit[ny][nx] = true;
				
			}
		}

		return answer;
	}

}
