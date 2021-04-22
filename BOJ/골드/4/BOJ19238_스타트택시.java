package self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19238_스타트택시 {
	static int N, M, F, ty, tx; // f 연료
	static int sy, sx, ey, ex, dest;
	static int[][] map;
	static boolean[][] visit;
	static int[][] d;
	static class Taxi{
		int y;
		int x;
		int cnt;
		public Taxi(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 태울 목표 승객
		F = Integer.parseInt(st.nextToken()); // 초기 연료 양
		
		map = new int[N+1][N+1];
		
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		ty = Integer.parseInt(st.nextToken());
		tx = Integer.parseInt(st.nextToken());

		d = new int[M+1][2];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			map[sy][sx] = i;
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			d[i][0] = ey;
			d[i][1] = ex;
		}
		
		int idx = 0;
		while(idx < M) {
			if(!findC()) break; // 고객을 찾고
			map[ty][tx] = 0; // 완료한 곳은 0으로 변경
			if(!go(dest)) break; // 목적지로 간다;
			idx++;
		}
		
		if(idx != M) System.out.println(-1);
		else System.out.println(F);
		
	}
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	
	private static boolean go(int dest) {
		visit = new boolean[N+1][N+1];
		Queue<Taxi> q = new LinkedList<>();
		
		q.offer(new Taxi(ty,tx,0));
		visit[ty][tx] = true;
		
		while(!q.isEmpty()) {
			Taxi t = q.poll();
			
			if(t.y == d[dest][0] && t.x == d[dest][1]) {
				ty = t.y;
				tx = t.x;
				F -= t.cnt;
				if(F<0) return false;
				F += (t.cnt*2);
				return true;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = t.y+dy[d];
				int nx = t.x+dx[d];
				
				if(ny<1 || nx<1 || ny>N || nx>N || map[ny][nx] == -1 || visit[ny][nx]) continue;
				visit[ny][nx] = true;
				q.offer(new Taxi(ny, nx, t.cnt+1));
				
			}
			
			
		}
		return false;
		
	}

	
	private static boolean findC() {
		visit = new boolean[N+1][N+1];
		
		Queue<Taxi> q = new LinkedList<>();
		
		
		PriorityQueue<Taxi> pq = new PriorityQueue<>(new Comparator<Taxi>() {

			@Override
			public int compare(Taxi o1, Taxi o2) {
				int tmp = o1.cnt - o2.cnt;
				if(tmp == 0) tmp = o1.y - o2.y;
				if(tmp == 0) tmp = o1.x - o2.x;
				return tmp;
			}
		});
		
		
		// 그 장소가 고객자리라면 이동할 필요 없이 목적지 설정 완료
		if(map[ty][tx] >= 1) {
			dest = map[ty][tx];
			return true;
		}
		
		q.offer(new Taxi(ty, tx, 0));
		visit[ty][tx] = true;
		
		
		while(!q.isEmpty()) {
			int size = q.size();
			// 0이 아닌 목적지가 있다면 맨 위에꺼만 꺼내오면 됨!
			if(!pq.isEmpty()) {
				Taxi t = pq.poll();
				ty = t.y;
				tx = t.x;
				F -= t.cnt;
				if(F<=0) return false;
				dest = map[t.y][t.x];
				return true;
			}

			for (int i = 0; i < size; i++) {
				Taxi t = q.poll();
			
				for (int d = 0; d < 4; d++) {
					int ny = t.y+dy[d];
					int nx = t.x+dx[d];
					
					if(ny<1 || nx<1 || ny>N || nx>N || map[ny][nx] == -1 || visit[ny][nx]) continue;
					
					if(map[ny][nx]>=1) pq.offer(new Taxi(ny, nx, t.cnt+1));
					
					visit[ny][nx] = true;
					q.offer(new Taxi(ny, nx, t.cnt+1));
				}
			}
			
			
		}
		return false;
	}
	

}
