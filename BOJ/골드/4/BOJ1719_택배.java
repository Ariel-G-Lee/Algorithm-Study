import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1719_택배 {
	static int n, m;
	static int[] cost, route;
	static boolean[] visit;
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> list;
	static StringBuilder sb = new StringBuilder();
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
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		
		list = new  ArrayList<ArrayList<Edge>>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<Edge>());
		}
		
		for (int i = 0; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.get(a).add(new Edge(b, v));
			list.get(b).add(new Edge(a, v));
		}
		

		for (int i = 1; i <= n; i++) {
			dijkstra(i);
		}
		
		System.out.println(sb);
		
		
	}
	private static void dijkstra(int start) {
		cost = new int[n+1];
		route = new int[n+1];
		Arrays.fill(cost, 999999);
		visit = new boolean[n+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);

		
		route[start] = start;
		cost[start] = 0;
		
		
		pq.offer(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visit[cur.e]) continue;
			visit[cur.e] = true;
			
			for (Edge next : list.get(cur.e)) {
				if(cost[next.e] > cost[cur.e]+next.w) {
					cost[next.e] = cost[cur.e]+next.w;
					route[next.e] = cur.e;
					pq.offer(new Edge(next.e, cost[next.e]));
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if(i == start) sb.append("-");
			else sb.append(find(i, start));
			sb.append(" ");
		}
		sb.append("\n");

	}
	private static int find(int i, int start) {
		while(route[i] != start) {
			i = route[i];
		}
		return i;
	}

}
