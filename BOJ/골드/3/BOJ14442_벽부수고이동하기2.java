import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14442_벽부수고이동하기2 {
	static int N, M, K, ans;
	static int[][] map;
	static class Info{
		int y;
		int x;
		int dist;
		int cnt;
		public Info(int y, int x, int dist, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}	
		}
		
		find();
		
		System.out.println(ans);
	}
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find() {
		boolean[][][] visited = new boolean[N][M][K+1];
		PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2)->o1.dist-o2.dist);
		
		pq.offer(new Info(0,0,1,0));
		visited[0][0][0] = true;
		
		while(!pq.isEmpty()) {
			Info cur = pq.poll();
			
			if(cur.y==N-1 && cur.x==M-1) {
				ans = cur.dist;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if(cur.cnt<K && map[ny][nx]==1 && !visited[ny][nx][cur.cnt+1]) {
					pq.offer(new Info(ny, nx, cur.dist+1, cur.cnt+1));
					visited[ny][nx][cur.cnt+1] = true;
				}
				if(map[ny][nx]==0 && !visited[ny][nx][cur.cnt]) {
					pq.offer(new Info(ny, nx, cur.dist+1, cur.cnt));
					visited[ny][nx][cur.cnt] = true;
				}
			}
		}
		ans = -1;
	}
}
