import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프림
public class BOJ1647_도시분할계획_프림 {
	static int N, M, ans;
	static boolean[] visit;
	static StringTokenizer st;
	static class Edge{
		int e;
		int w;
		public Edge(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
	}
	// 모든 집을 다 연결
	// 집을 연결하는 길의 유지비가 최대인 것을 빼서 마을을 분리
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> list = new ArrayList<>();
		visit = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			// 양방향
			list.get(A).add(new Edge(B,C)); 
			list.get(B).add(new Edge(A,C));
		}
		
		int count = 0;
		int max = Integer.MIN_VALUE;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)->o1.w - o2.w);
		
		pq.offer(new Edge(1, 0)); // 첫번째 집에서 시작
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visit[cur.e]) continue;
			visit[cur.e] = true; // 방문처리
			ans += cur.w; // 유지비 추가
			
			max = Math.max(max, cur.w); // 분리된 두 마을 사이의 길의 최대 유지비 저장

			if(++count == N) break; // 모두 연결되었으면 break
			
			// 현재 집에서 연결된 모든 길을 탐색
			for (Edge node : list.get(cur.e)) {
				if(visit[node.e]) continue; // 이미 길이 연결된 집이라면 넘어간다.
				pq.offer(node);
			}
			
		}
		
		System.out.println(ans - max); // 
	}
}
