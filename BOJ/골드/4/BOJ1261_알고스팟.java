import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261_알고스팟 {
	static int M, N;
	static int[][] map, check;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	static StringTokenizer st;
	static class Node{
		int y;
		int x;
		int d;
		public Node(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		check = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j-1) - '0';
				check[i][j] = 999999;
			}
		}		

		find();
		
		System.out.println(check[N][M]);
	}
	private static void find() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.d - o2.d);
		
		pq.offer(new Node(1,1,0));
		check[1][1] = map[1][1];
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				
				if(ny<1 || ny>N || nx<1 || nx>M) continue;
				
				// 0과 1로 구분되어 있으니까 사실상 1일때 깨고 들어가는게 해당 맵의 값을 넣는 것과 같음
				if(check[ny][nx] > check[cur.y][cur.x]+map[ny][nx]) {
					check[ny][nx] = check[cur.y][cur.x]+map[ny][nx];
					pq.offer(new Node(ny, nx, check[ny][nx]));
				}
			}	
		}
	}

}
