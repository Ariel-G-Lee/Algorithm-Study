import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1774_우주신과교감 {
	static int N, M;
	static double ans;
	static StringTokenizer st;
	static ArrayList<Edge> list;
	static int[] parents;
  	static class Edge{
  		int e1;
		int e2;
		double w;
		public Edge(int e1, int e2, double w) {
			super();
			this.e1 = e1;
			this.e2 = e2;
			this.w = w;
		}
	}
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][2];
		
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[i][0] = x;
			map[i][1] = y;
		}
		
		list = new ArrayList<>();
		
		for (int i = 1; i < N; i++) {
			for (int j = i+1; j <= N; j++) {
				double dist = Math.sqrt(Math.pow(map[j][0] - map[i][0], 2)+Math.pow(map[j][1]-map[i][1], 2));
				list.add(new Edge(i, j, dist));
			}
		}
		
		Collections.sort(list, (o1, o2) -> {
			if(o1.w>o2.w) return 1;
			else return -1;
		});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}

		doKruskal();
		
		System.out.println(String.format("%.2f", ans));
		
	}
	private static void doKruskal() {
		for (int i = 0; i < list.size(); i++) {
			Edge cur = list.get(i);
			
			if(find(cur.e1) != find(cur.e2)) {
				union(cur.e1, cur.e2);
				ans += cur.w;
			}
		}
		
	}
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa>pb) parents[pb] = pa;
		else parents[pa] = pb;
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
}
