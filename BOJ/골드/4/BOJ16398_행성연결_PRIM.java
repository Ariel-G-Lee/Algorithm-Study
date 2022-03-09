import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398_행성연결_PRIM {
	static int N;
	static long ans;
	static int[][] map;
	static class Edge{
		int e;
		int w;
		public Edge(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		find();
		
		System.out.println(ans);
		
	}
	private static void find() {
		boolean[] visited = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)->o1.w - o2.w);
		pq.offer(new Edge(0,0));
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visited[cur.e]) continue;
			visited[cur.e] = true;
			
			ans+=cur.w;
			
			for (int next = 0; next < map[cur.e].length; next++) {
				if(next == cur.e || visited[next]) continue;
				pq.offer(new Edge(next, map[cur.e][next]));
			}
			
		}
		
	}
}
