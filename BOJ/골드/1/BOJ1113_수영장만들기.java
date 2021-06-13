import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1113_수영장만들기 {
	
	static int N, M, ans, maxh;
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] tmpVisited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tmpVisited = new boolean[N][M];
		
		maxh = -1;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				maxh = Math.max(maxh, map[i][j]);
			}
		}
//		System.out.println(maxh);
		
		find();
		System.out.println(ans);

	}
	private static void find() {
		for (int h = 2; h <= maxh; h++) {
			tmpVisited = new boolean[N][M]; // 해당 높이에 대한 방문체크 배열	
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] < h && !tmpVisited[i][j]) { // 현재 물 높이보다 낮고, 방문한적 없다면
						ans += cal(h, i, j); // 물 채울 면적 찾기
					}
				}
			}
		}
		
	}
	
	private static int cal(int h, int y, int x) {
		int size = 0;
		boolean flag = false; // 벽과 인접해서 채울 수 없는 곳인지 확인
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(y,x));
		tmpVisited[y][x] = true;
		size++;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M) { // 면적 밖을 벗어나는 범위가 들어온다면 벽이랑 인접 -> 물 부을 수 없음
					flag = true; // 채울 수 없음 -> 체크
					continue;
				}
				if(!tmpVisited[ny][nx] && map[ny][nx] < h) {
					tmpVisited[ny][nx] = true;
					q.offer(new Pos(ny, nx));
					size++;
				}
			}
		}
		if(flag) size = 0;
		return size;
	}

	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
