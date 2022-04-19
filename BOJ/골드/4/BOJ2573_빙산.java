import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static StringTokenizer st;
	// ice : 아직 빙산인 곳
	// check : 녹아서 물이 된 곳
	static Queue<Pos> ice, check;
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		ice = new LinkedList<>();
		check = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) ice.offer(new Pos(i, j));
			}
		}

		if(ice.size() == 0) System.out.println(0);
		else {
			find();
			System.out.println(ans);
		}
		
		
		
	}
	private static void find() {
		while(true) {
			// 녹인다.
			melting();
			ans++;
			// 한 덩어리인지 확인
			if(!check()) return;
		}
		
	}
	
	
	private static boolean check() {
		if(ice.isEmpty()) { // 한 덩어리인채로 다 녹은 경우
			ans = 0;
			return false;
		}
		// Queue 가장 앞에 있는 애로 체크 시작
		Pos s = ice.peek();
		// 한덩어리라면
		if(checkIce(s.y, s.x) == ice.size()) return true;
		// 나눠졌다면
		else return false;
	}
	private static int checkIce(int y, int x) {
		visited = new boolean[N][M];
		int cnt = 1;
		Queue<Pos> tmp = new LinkedList<>();
		tmp.offer(new Pos(y, x));
		visited[y][x] = true;
		
		while(!tmp.isEmpty()) {
			Pos cur = tmp.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx] || map[ny][nx] == 0) continue;
				visited[ny][nx] = true;
				tmp.offer(new Pos(ny, nx));
				cnt++;
			}
		}
		
		// 하나의 덩어리 사이즈 return
		return cnt;

	}
	private static void melting() {
		int iceSize = ice.size();
		
		while(iceSize --> 0) {
			Pos cur = ice.poll();
			
			int cnt = 0;
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if(map[ny][nx] == 0) cnt++;
			}
			
			// 이번에 물(0)이 되어야하는 빙산들
			// 바로 0으로 바꿔버리면 다른 빙산을 녹이는데 문제가 생긴다.
			if((map[cur.y][cur.x]-cnt) <= 0) check.offer(new Pos(cur.y, cur.x));
			else { // 0이 아니라면 높이를 낮추고 다시 Queue 넣는다.
				map[cur.y][cur.x] -= cnt;
				ice.offer(cur);
			}
		}
		
		// 0으로 바꿔주기
		while(!check.isEmpty()) {
			Pos cur = check.poll();
			map[cur.y][cur.x] = 0;
		}
		
	}
}
