import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ13023_ABCDE {
	static int N, M, ans;
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> link;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 사람 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계
		
		link = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			link.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link.get(a).add(b);
			link.get(b).add(a);
		}
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			find(i, 0);
			visited[i] = false;
			if(ans == 1) break;
		}
		
		System.out.println(ans);
		
	}
	private static void find(int cur, int cnt) {
		
		if(cnt == 4) {
			ans = 1;
			return;
		}
		
		for (int next : link.get(cur)) {
			if(visited[next]) continue;
			visited[next] = true;
			find(next, cnt+1);
			visited[next] = false;
		}
		
	}
}
