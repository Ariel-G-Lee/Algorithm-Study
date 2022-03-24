import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2533_사회망서비스SNS {
	static int N;
	static int[][] dp;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][2];
		list = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			list.get(n1).add(n2);
			list.get(n2).add(n1);
		}
		
		visited = new boolean[N+1];
		find(1, -1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	private static void find(int curNode, int parent) {
		// 얼리어답터가 아닌 경우
		dp[curNode][0] = 0;
		// 얼리어답터인 경우
		dp[curNode][1] = 1;
		
		for (Integer nextNode : list.get(curNode)) {
			if(nextNode == parent) continue;
			find(nextNode, curNode);
			dp[curNode][0] += dp[nextNode][1]; // 자식이 무조건 얼리어답터여야한다.
			dp[curNode][1] += Math.min(dp[nextNode][0], dp[nextNode][1]); // 자식이 얼리어답터 이거나 아니거나
		}
		
	}
}
