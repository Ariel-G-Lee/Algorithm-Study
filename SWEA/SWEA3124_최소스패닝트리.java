package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA3124_최소스패닝트리 {
	static class Edge implements Comparable<Edge>{
		int e;
		int w;
		public Edge(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
		
	}
	static int T, V, E;
	static long ans;
	static ArrayList<Edge>[] list;

	static boolean[] visit;
	static int[] dist;
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			ans = 0;
			visit = new boolean[V+1];

			dist = new int[V+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			list = new ArrayList[V+1];
			
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<Edge>();
			}
			
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				list[v1].add(new Edge(v2,w));
				list[v2].add(new Edge(v1,w));
			}
			
			PriorityQueue<Edge> q = new PriorityQueue<>();
			
			
			q.offer(new Edge(1,0));
			
			while(!q.isEmpty()) {
				
				Edge cur = q.poll();
				
				if(visit[cur.e]) continue;
				
				visit[cur.e] = true;
				
				ans += cur.w;
				
				for (int i = 0; i < list[cur.e].size(); i++) {
					if(visit[list[cur.e].get(i).e]) continue;
					if(dist[list[cur.e].get(i).e] > list[cur.e].get(i).w) {
						dist[list[cur.e].get(i).e] = list[cur.e].get(i).w;
						q.offer(list[cur.e].get(i));
					}
				}
			}
			
			System.out.println("#"+t+" "+ans);
			
			
			
		}
	}
}
