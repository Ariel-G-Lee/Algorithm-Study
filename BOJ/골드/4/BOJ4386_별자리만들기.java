import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386_별자리만들기 {
	static int n;
	static double ans;
	static int[] parents;
	static List<Star> list;
	static class Star{
		int n;
		double y;
		double x;
		public Star(int n, double y, double x) {
			super();
			this.n = n;
			this.y = y;
			this.x = x;
		}
	}
	static class Edge implements Comparable<Edge>{
		int s1;
		int s2;
		double d;
		public Edge(int s1, int s2, double d) {
			super();
			this.s1 = s1;
			this.s2 = s2;
			this.d = d;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(d, o.d);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double y = Double.parseDouble(st.nextToken());
			double x = Double.parseDouble(st.nextToken());
			list.add(new Star(i, y, x));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				pq.offer(new Edge(i, j, Math.sqrt((Math.pow(list.get(i).y-list.get(j).y, 2)+Math.pow(list.get(i).x-list.get(j).x, 2)))));
			}
		}
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(find(cur.s1) != find(cur.s2)) {
				union(cur.s1, cur.s2);
				ans += cur.d;
			}
		}
		
		System.out.format("%.2f", ans);
		
	}
	
	public static void union(int s1, int s2) {
		int ps1 = find(s1);
		int ps2 = find(s2);
		
		if(ps1 > ps2) parents[ps1] = ps2;
		else parents[ps2] = ps1;
	}
	
	public static int find(int s) {
		if(parents[s] == s) return s;
		
		return parents[s] = find(parents[s]);
	}

}
