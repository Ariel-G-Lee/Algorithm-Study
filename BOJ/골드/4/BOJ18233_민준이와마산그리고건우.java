import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18233_민준이와마산그리고건우 {
	static StringTokenizer st;
	static int V, E, P;
	static ArrayList<ArrayList<Edge>> list = new ArrayList<ArrayList<Edge>>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		E = Integer.parseInt(st.nextToken()); // 간선 개수
		P = Integer.parseInt(st.nextToken()); // 건우 위치 정점
		
		for (int i = 0; i <= V; i++) {
			list.add(new ArrayList<Edge>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			int e2= Integer.parseInt(st.nextToken());
			int w= Integer.parseInt(st.nextToken());
			list.get(e1).add(new Edge(e2, w));
			list.get(e2).add(new Edge(e1, w));
		}
		
		int[] startOne = dijkstra(1);
//		System.out.println(Arrays.toString(startOne));
		int[] startP = dijkstra(P);
//		System.out.println(Arrays.toString(startP));
		
		// 1에서 시작해서 건우를 도와주고(P를 방문했다가) V로 가는 경로가
		// 최단경로랑 같으면 도울 수 있음!
		if(startOne[V] == startOne[P]+startP[V]) System.out.println("SAVE HIM");
		else System.out.println("GOOD BYE");
		
		
	}
	
	private static int[] dijkstra(int start) {
		// 초기화
		boolean[] visited = new boolean[V+1]; // 방문체크
		int[] cost = new int[V+1]; // 최소 비용
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		cost[start] = 0;
		pq.offer(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visited[cur.e]) continue;
			visited[cur.e] = true;
			
			for (Edge next : list.get(cur.e)) {
				if(cost[next.e] > cost[cur.e]+next.w) {
					cost[next.e] = cost[cur.e]+next.w;
					pq.offer(new Edge(next.e,cost[next.e]));
				}
			}
		}
		return cost;
	}

	static class Edge{
		int e;
		int w;
		public Edge(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
}
