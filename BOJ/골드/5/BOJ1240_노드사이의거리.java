import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1240_노드사이의거리 {
	static int N, M;
	static int[][] adj;
	static int s, e, dist;
	static StringTokenizer st;
	static class Pos{
		int e;
		int d;
		public Pos(int e, int d) {
			super();
			this.e = e;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new int[N+1][N+1];
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a][b] = adj[b][a] = c;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			find();
			sb.append(dist).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void find() {
		Queue<Pos> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(new Pos(s, 0));
		visited[s] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(cur.e == e) {
				dist = cur.d;
				return;
			}
			
			for (int i = 1; i < adj[cur.e].length; i++) {
				if(visited[i] || adj[cur.e][i] == 0) continue;
				q.offer(new Pos(i, cur.d+adj[cur.e][i]));
				visited[i] = true;
			}
		}
	}
}
