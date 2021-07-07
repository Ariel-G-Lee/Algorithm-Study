import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11724_연결요소의개수 {
	static int N, M, ans;
	static boolean[][] arr;
	static boolean[] visit;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		
		arr = new boolean[N+1][N+1]; // 인접 행렬
		visit = new boolean[N+1]; // 방문 배열
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = true; // 인접행렬 표시
		}
		
		for (int i = 1; i <= N; i++) {
			if(!visit[i]) { // 방문한적이 없는 정점에 대해 탐색
				ans++; // 연결 수 증가
				dfs(i); 
			}
		}
		
		System.out.println(ans);
		
		
	}
	private static void dfs(int n) {
		visit[n] = true; // 방문처리
		
		for (int i = 1; i <= N; i++) {
			if(!visit[i] && arr[n][i]) { // 방문한 적이 없고 인접해 있다면
				dfs(i); // 추가 방문
			}
		}
		
	}
	
}
