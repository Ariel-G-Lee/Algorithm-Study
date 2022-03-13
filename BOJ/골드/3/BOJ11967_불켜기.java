import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11967_불켜기 {
	static int N, M, ans;
	static boolean[][] map;
	static ArrayList<Pos>[][] check;
	static StringTokenizer st;
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
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][N+1];
		check = new ArrayList[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <=N; j++) {
				check[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			check[x][y].add(new Pos(a,b));
		}
		
		find();
		
		System.out.println(ans);
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N+1][N+1];
		boolean[][] isMoved = new boolean[N+1][N+1];
		map[1][1] = true;
		visited[1][1] = true;
		isMoved[1][1] = true;
		q.offer(new Pos(1, 1));
		
		ans = 1;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			// 이동할 수 있는 방 체크
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<1 || nx<1 || ny>N || nx>N) continue;
				isMoved[ny][nx] = true;
			}
			
			// 현재 방에서 불을 켤 수 있는 방을 찾아
			for (Pos next : check[cur.y][cur.x]) {
				// 해당 방이 불이 켜져 있지 않으면 켠다!
				if(!map[next.y][next.x]) {
					map[next.y][next.x] = true;
					ans++;
				}
				
				// 방문한 적 없고 이동할 수 있는 방이라면 넣는다.
				if(isMoved[next.y][next.x] && !visited[next.y][next.x]) {
					q.offer(new Pos(next.y, next.x));
					visited[next.y][next.x] = true;
				}
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<1 || nx<1 || ny>N || nx>N) continue;
				// 방문한 적이 있거나 불이 꺼져있거나 이동할 수 없는 곳이라면 이동X
				if(visited[ny][nx] || !map[ny][nx] || !isMoved[ny][nx]) continue;
				q.offer(new Pos(ny, nx));
				visited[ny][nx] = true;
			}
		}
		
	}
}
