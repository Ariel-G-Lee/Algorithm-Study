import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// dp+다익스트라
public class BOJ10217_KCMTravel {
	static int T, N, M, K, ans;
	static StringTokenizer st;
	static ArrayList<ArrayList<Info>> list;
	static int[][] dp;
	static class Info{
		int e;
		int c;
		int d;
		public Info(int e, int c, int d) {
			super();
			this.e = e;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			dp = new int[N+1][M+1];
			for (int i = 0; i <= N; i++) {
				list.add(new ArrayList<>());
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				list.get(u).add(new Info(v, c, d));
			}
			
			ans = Integer.MAX_VALUE;

			find();

			if(ans==Integer.MAX_VALUE) sb.append("Poor KCM").append("\n");
			else sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void find() {
		// dp[i][j]는 i 노드까지 j 비용을 들여 올 수 있는 최소 시간
		PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2)->o1.d - o2.d);
		
		dp[1][0] = 0;
		pq.offer(new Info(1, 0, 0));
		
		while(!pq.isEmpty()) {
			Info cur = pq.poll();
			
			if(cur.e==N) {
				ans = cur.d;
				break;
			}
			
			for (Info info : list.get(cur.e)) {
				int nextCost = cur.c+info.c;
				if(nextCost>M) continue;
				int nextTime = cur.d + info.d;
				if(dp[info.e][nextCost] > nextTime) {
					dp[info.e][nextCost] = nextTime;
					pq.offer(new Info(info.e, nextCost, dp[info.e][nextCost]));
				}
			}
		}
		
	}
}
