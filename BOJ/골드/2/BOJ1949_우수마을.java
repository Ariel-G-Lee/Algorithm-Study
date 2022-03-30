import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1949_우수마을 {
	static int N;
	static int[] people;
	static int[][] dp;
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		people = new int[N+1];
		dp = new int[N+1][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		dfs(1,-1);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
		
	}
	private static void dfs(int cur, int parent) {
		for (int child : list.get(cur)) {
			if(child == parent) continue;
			dfs(child, cur);
			// 현재가 우수 마을이 아니라면
			// 자식은 우수 마을일 수도 아닐 수도 있음
			dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
			// 현재가 우수 마을이라면 그 자식들은 모두 우수 마을이면 안됨!
			// 우수 마을끼리는 연결될 수 없음
			dp[cur][1] += dp[child][0];
		}
		
		dp[cur][1] += people[cur];
		
	}
}
