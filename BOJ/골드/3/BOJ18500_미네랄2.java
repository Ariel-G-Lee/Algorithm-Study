import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ18500_미네랄2 {
	static int R, C, N;
	static char[][] map;
	static ArrayList<Pos> dropM;
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
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		N = Integer.parseInt(br.readLine()); // 막대를 던지는 횟수 입력
		
		boolean dir = true;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken()); // 던지는 높이 입력
			if(i%2 == 0) dir = true; // 왼쪽에서 던진다.
			else dir = false; // 오른쪽에서 던진다.
			throwStick(dir, height); // 던져서 파괴
			
			visit = new boolean[R][C]; 
			dropM = new ArrayList<>(); // 떨어트릴 미네랄을 넣을 list
			findCluster(); // 바닥에서부터 연결된 미네랄 찾기
			// 떨어트릴 미네랄이 있으면
			if(dropM.size() != 0) dropMineral(); 
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static void dropMineral() {
		boolean flag = true;
		while(true) {
			if(!flag) break;
			for (Pos pos : dropM) {
				int dy = pos.y+1;
				int dx = pos.x;
				if(dy<0 || dx<0 || dy>=R || dx>=C || map[dy][dx]=='x') { // 하나라도 더이상 내릴 수 없다면
					flag = false;
					break;
				}
			}
			if(flag) { // 내릴 수 있을 때만 하나씩 내려놓기
				for (Pos pos : dropM) {
					pos.y = pos.y+1;
				}
			}
		}
		
		for (Pos pos : dropM) {
			map[pos.y][pos.x] = 'x';
		}
		
	}
	private static void findCluster() {
		Queue<Pos> q = new LinkedList<>();
		for (int i = 0; i < C; i++) {
			if(map[R-1][i] == 'x') {
				q.offer(new Pos(R-1, i)); // 바닥에 붙어있는 미네랄을 큐에 넣고
				visit[R-1][i] = true; // 방문체크
			}
		}
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				// 범위를 벗어나고 이미 방문했거나 미네랄이 아니라면 넘겨
				if(ny<0 || nx<0 || ny>=R || nx>=C || visit[ny][nx] || map[ny][nx] == '.') continue; 
				q.offer(new Pos(ny, nx)); // 아니라면 큐에넣고
				visit[ny][nx] = true; // 방문처리
			}
		}
		
		// 방문 처리가 안된 미네랄 찾아서 list에 넣기(떨어져야할 미네랄)
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visit[i][j] && map[i][j] == 'x') {
					map[i][j] = '.';
					dropM.add(new Pos(i, j));
				}
			}
		}
		
	}
	private static void throwStick(boolean d, int h) {
		int throwH = R-h; // 실제로 파괴해야하는 높이
		if(d) { // 왼쪽에서 던짐
			for (int i = 0; i < C; i++) {
				if(map[throwH][i] == 'x') {
					map[throwH][i] = '.';
					break;
				}
			}
		} else { // 오른쪽에서 던짐
			for (int i = C-1; i >= 0; i--) {
				if(map[throwH][i] == 'x') {
					map[throwH][i] = '.';
					break;
				}
			}
		}
	}
}
