import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234_인구이동 {
	static int N, L, R, ans;
	static int[][] map;
	static boolean[][] visit;
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
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 인구 이동 시작
		move();
		
		System.out.println(ans);
		
	}
	private static void move() {
		while(true) {
			
			// 해당 턴에 이동이 있었는지 체크
			boolean moveCheck = false;
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 이번 이동에서 이미 방문했던 곳이라면 넘어간다.
					if(visit[i][j]) continue;
					// 이동한 나라 수로 이동이 있었는지 판단하여 체크
					if(find(i, j) > 1) moveCheck = true;
				}
			}
			
			// 한번이라도 이동이 없었다면 종료
			if(!moveCheck) break;
			
			// 이동 횟수 추가
			ans++;
		}
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	private static int find(int i, int j) {
		// bfs로 이동 가능한 나라를 찾기 위해 사용
		Queue<Pos> list = new LinkedList<>();
		// 이동한 가능한 나라들 리스트
		ArrayList<Pos> group = new ArrayList<>();
		
		list.offer(new Pos(i, j));
		group.add(new Pos(i, j));
		visit[i][j] = true;
		
		int pNum = map[i][j];
		
		while(!list.isEmpty()) {
			Pos cur = list.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				if(ny<0 || nx<0 || ny>=N || nx>=N || visit[ny][nx]) continue;
				// 차이가 L이상 R이하라면 넣는다
				int diff = Math.abs(map[cur.y][cur.x] - map[ny][nx]);
				if(L<=diff && diff<=R) {
					list.offer(new Pos(ny, nx));
					group.add(new Pos(ny, nx));
					visit[ny][nx] = true;
					pNum += map[ny][nx];
				}
			}
		}
		
		// 이동 가능한 나라들이 있으면
		if(group.size()>1) {
			int recalNum = pNum/group.size();
			// 인구 이동
			for (Pos pos : group) {
				map[pos.y][pos.x] = recalNum;
			}
		}
		
		return group.size();
		
		
		
		
	}
}
