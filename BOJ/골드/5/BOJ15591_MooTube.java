import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15591_MooTube {
	static int N, Q;
	static StringTokenizer st;
	static ArrayList<ArrayList<Info>> list;
	static class Info{
		int e;
		int w;
		public Info(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Info(b, w));
			list.get(b).add(new Info(a, w));
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			int ans = find(K, V);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	private static int find(int k, int v) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(v);
		visited[v] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (Info next : list.get(cur)) {
				if(!visited[next.e] && next.w>=k) {
					q.offer(next.e);
					visited[next.e] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
