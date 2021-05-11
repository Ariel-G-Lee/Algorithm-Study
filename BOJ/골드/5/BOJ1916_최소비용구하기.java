package self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916_최소비용구하기 {
	static int N, M;
	static int[] cost;
	static boolean[] visit;
	static StringTokenizer st;
	static int start, end;
	static ArrayList<ArrayList<Edge>> list = new ArrayList<ArrayList<Edge>>();
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
		
		// 도시 개수
		N = Integer.parseInt(br.readLine());
		
		// 버스 개수
		M = Integer.parseInt(br.readLine());
		
		visit = new boolean[N+1];
		cost = new int[N+1];
		
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Edge>());
		}
		
		for (int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list.get(s).add(new Edge(e,w));
		}
		
		st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		
		dijkstra();
		
		System.out.println(cost[end]);
		
	}
	private static void dijkstra() {
		cost[start] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visit[cur.e]) continue;
			visit[cur.e] = true;
			
			for (Edge next : list.get(cur.e)) {
				// 다른데서 오는 곳보다 현재에서 오는 것이 비용이 더 적다면 바꿔준다.
				if(cost[next.e] > cost[cur.e]+next.w) {
					cost[next.e] = cost[cur.e]+next.w;
					next.w = cost[next.e];
					pq.offer(next);
				}
			}
		}
		
	}
}
