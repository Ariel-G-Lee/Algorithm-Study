import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1445_일요일아침의데이트 {
	static class Pos{
		int y;
		int x;
		int gCnt;
		int gsideCnt;
		public Pos(int y, int x, int gCnt, int gsideCnt) {
			super();
			this.y = y;
			this.x = x;
			this.gCnt = gCnt; // 지나가는 쓰레기 수
			this.gsideCnt = gsideCnt; // 지나가는 쓰레기 옆 수
		}
	}
	static int N, M, sy, sx, ansG, ansGSide;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					sy = i;
					sx = j;
				}
			}
		}
		
		// 쓰레기 주변은 x로 표시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'g') {
					for (int d = 0; d < 4; d++) {
						int ny = i+dy[d];
						int nx = j+dx[d];
						if(ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx] != '.') continue;
						map[ny][nx] = 'x';
					}
				}
			}
		}
		
		find();
		
		System.out.println(ansG + " " + ansGSide);
		
		
	}
	private static void find() {
		Queue<Pos> pq = new PriorityQueue<>(new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				int res = o1.gCnt - o2.gCnt;
				if(res == 0) res = o1.gsideCnt - o2.gsideCnt;
				return res;
			}

		});
		
		pq.offer(new Pos(sy, sx, 0, 0));
		visited[sy][sx] = true;
		while(!pq.isEmpty()) {
			
			Pos cur = pq.poll();
			// 꽃이 있는 곳이라면 종료
			if(map[cur.y][cur.x] == 'F') {
				ansG = cur.gCnt;
				ansGSide = cur.gsideCnt;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx]) continue;
				// 쓰레기가 있는 곳이라면 쓰레기 카운트+1
				if(map[ny][nx] == 'g') pq.offer(new Pos(ny, nx, cur.gCnt+1, cur.gsideCnt));
				// 쓰레기 옆이라면 쓰레기 옆 카운트+1
				else if(map[ny][nx] == 'x') pq.offer(new Pos(ny, nx, cur.gCnt, cur.gsideCnt+1));
				
				else pq.offer(new Pos(ny, nx, cur.gCnt, cur.gsideCnt));
				
				visited[ny][nx] = true;
			}
			
		}
		
	}
}
