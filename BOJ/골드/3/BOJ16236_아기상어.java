import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_아기상어 {
	static int N, sy, sx, ans, eatCnt, curSize, tCnt;
	static int[][] map, dist;
	static StringTokenizer st;
	static PriorityQueue<Pos> fish;
	static class Pos{
		int y;
		int x;
		int dis;
		public Pos(int y, int x, int dis) {
			super();
			this.y = y;
			this.x = x;
			this.dis = dis;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					sy = i;
					sx = j;
				} else if(map[i][j] >= 1 && map[i][j] <= 6) {
					tCnt++;
				}
			}
		}
		
		curSize = 2;
		
		if(tCnt != 0) {
			while(true) {
				find(); // 먹을 수 있는 물고기 찾는다.
				if(fish.size() == 0) {
					break;
				} else {
					Pos eatFish = fish.poll();
					eatCnt++;
					map[eatFish.y][eatFish.x] = 0;
					sy = eatFish.y;
					sx = eatFish.x;
					ans += eatFish.dis; // 이동시간 추가 
					if(eatCnt == curSize) {
						eatCnt = 0;
						curSize++;
					}
				}
			}
		}
		
		System.out.println(ans);
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};	
	private static void find() {
		Queue<Pos> q = new LinkedList<>();
		fish = new PriorityQueue<>((o1, o2) -> {
			int tmp = o1.dis - o2.dis;
			if(tmp == 0) tmp = o1.y - o2.y;
			if(tmp == 0) tmp = o1.x - o2.x;
			return tmp;
		});
		q.offer(new Pos(sy, sx, 0));
		dist = new int[N][N];
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
				if(dist[ny][nx] != 0) continue;
				if(map[ny][nx] > curSize) continue;

				dist[ny][nx] = dist[cur.y][cur.x] + 1;

				q.offer(new Pos(ny, nx, dist[ny][nx]));
				
				if(map[ny][nx] >= 1 && map[ny][nx] < curSize) {
					fish.offer(new Pos(ny, nx, dist[ny][nx]));
				}
			}
		}
	}
}
