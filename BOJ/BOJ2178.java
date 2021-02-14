import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
	static int N, M, result;
	static int[][] arr;
	static boolean[][] visit; 
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		result = 0;
		visit[0][0] = true;
		bfs(0,0);
		System.out.println(arr[N-1][M-1]);
	}
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	static void bfs(int y, int x) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(y,x));
		visit[y][x] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p.y+dy[i];
				int nx = p.x+dx[i];
				if(ny>=0 && ny<N && nx>=0 && nx<M && arr[ny][nx]==1 && !visit[ny][nx]) {
					queue.offer(new Point(ny, nx));
					visit[ny][nx] = true;
					arr[ny][nx] = arr[p.y][p.x]+1;
				}
			}
		}
		
		
	}
	
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}
