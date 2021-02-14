import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10451 {
	static int T, N, count;
	static int[] arr;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			visit = new boolean[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			count = 0;
			for (int i = 1; i <= N; i++) {
				if(!visit[i]) {
					dfs(i);
					count++;
				}
			}
			
			System.out.println(count);
		}
	}
	static void dfs(int n) {
		int next = arr[n];
		if(visit[next]) return;
		visit[next] = true;
		dfs(next);
	}
}
