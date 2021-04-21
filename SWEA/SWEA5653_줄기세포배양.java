package self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 처음 생성된 시간+life까지 비활성화
// 처음 생성된 시간+life*2가 되면 죽음
// 한번 퍼뜨리고 나면 더이상 퍼뜨릴 필요가 없다
public class SWEA5653_줄기세포배양 {
	static int T, N, M, K;
	static int[][] map;
	static boolean[][] visit;
	
	static class Cell{
		int y;
		int x;
		int life;
		int act; // 활성화되는 시간
		int die; // 죽는 시간
		public Cell(int y, int x, int life, int act, int die) {
			super();
			this.y = y;
			this.x = x;
			this.life = life;
			this.act = act;
			this.die = die;
		}

	}
	
	static Queue<Cell> q; // 모든 세포를 포함한 queue
	static PriorityQueue<Cell> pq; // 퍼뜨리기 위한 queue(life가 높은 순으로)
	static final int MAX = 400;
	static final int MAX_HALF = MAX/2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 세로크기
			M = Integer.parseInt(st.nextToken()); // 가로크기
			K = Integer.parseInt(st.nextToken()); // 시간
			
			map = new int[MAX][MAX];
			visit = new boolean[MAX][MAX];
			
			q = new LinkedList<>();
			pq = new PriorityQueue<>((o1, o2) -> o2.life - o1.life);
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp != 0) {
						map[MAX_HALF-N+i][MAX_HALF-M+j] = tmp;
						q.offer(new Cell(MAX_HALF-N+i,MAX_HALF-M+j, tmp, tmp, tmp*2));
						visit[MAX_HALF-N+i][MAX_HALF-M+j] = true;
					}
				}
			}
			
			
			// 시간마다 활성화 되는 애들을 체크해서 pq에 넣고
			// pq에 들어간 애들은 퍼뜨린다!
			for (int k = 1; k <= K; k++) {
				check(k);
				spread(k);
			}
			
			System.out.println("#"+t+" "+q.size());
		}
		
		
	}
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};


	private static void spread(int t) {
		while(!pq.isEmpty()) {
			Cell c = pq.poll();
			
			if(t < c.die) continue;
			
			for (int d = 0; d < 4; d++) {
				int ny = c.y + dy[d];
				int nx = c.x + dx[d];
				
				if(visit[ny][nx]) continue;
				visit[ny][nx] = true;
				map[ny][nx] = c.life;
				q.offer(new Cell(ny, nx, c.life, t+c.life, t+c.life*2));
				
			}
			
		}
		
	}

	
	private static void check(int t) {
		int size = q.size();
		
		for (int i = 0; i < size; i++) {
			Cell c = q.poll();
			
			if(c.act+1 == t) pq.add(c); // 활성화
			else if(c.act >= t) q.add(c); // 활성화 되기 전
			else if(c.act < t && c.die > t) q.add(c); // 활성화되서 죽기 전
		}
		
	}
	
}
