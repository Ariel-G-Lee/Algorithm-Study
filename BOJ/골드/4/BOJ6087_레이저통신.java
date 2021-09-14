package over;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ6087_레이저통신 {
	static int W, H, sy, sx, ans;
	static char[][] map;
	static int[][] visit;
	static class Info{
		int y;
		int x;
		int dir; // 이동 방향
		int cnt; // 거울 수
		public Info(int y, int x, int dir, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		visit = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'C') {
					sy = i;
					sx = j;
				}
			}
		}
		
		// 현재 방향을 같이 저장해서 보내자!
		// 방문했을때 이전보다 더 많은 거울 개수를 갖고 있다면 갈 필요 없음
		find();
		
		System.out.println(ans);
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find() {
		
		PriorityQueue<Info> q = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
		
		// 시작점
		q.offer(new Info(sy, sx, -1, 0));
		visit[sy][sx] = 0;
		map[sy][sx] = '.';
		
		while(!q.isEmpty()) {
			
			Info cur = q.poll();
			
			// 또 다른 C를 만났다면 return
			if(map[cur.y][cur.x] == 'C') {
				ans = cur.cnt;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				// 범위를 벗어나거나, 벽이면 넘어가
				if(ny<0 || ny>=H || nx<0 || nx>=W || map[ny][nx] == '*') continue;
				
				// 시작이거나 방향이 변하지 않고 지금 갖고 가는 거울 개수와 같거나 작을때만
				if((cur.dir == -1 || d == cur.dir) && cur.cnt <= visit[ny][nx]) {
					
					q.offer(new Info(ny, nx, d, cur.cnt));
					visit[ny][nx] = cur.cnt;
					
				} else if(cur.cnt+1 <= visit[ny][nx]) { // 방향이 변하고 지금 갖고 가는 거울 개수와 같거나 작을때만
					
					q.offer(new Info(ny, nx, d, cur.cnt+1));
					visit[ny][nx] = cur.cnt+1;
					
				}
			}
			
			
		}
		
	}
}
