import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11404_플로이드 {
	static int n, m;
	static int[][] arr;
	static final int INF = 9999999;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		arr = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) { // 자기자신으로 가는게 아니면 큰수로 초기화
				if(i != j && arr[i][j] == 0) arr[i][j] = INF;
			}
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다. -> 최소로
			arr[a][b] = Math.min(arr[a][b], c); 
		}
		
		// 경유지
		for (int k = 1; k <= n; k++) {
			// 출발지
			for (int i = 1; i <= n; i++) {
				if(i==k) continue; // 경유지와 출발지가 같다면 다음 출발지로
				// 목적지
				for (int j = 1; j <= n; j++) {
					if(j==k || i==j) continue; // 경유지와 목적지가 같거나 출발지와 도착지가 같다면 패스
					if(arr[i][j] > arr[i][k] + arr[k][j]) { // 경유지를 지나가는 것이 더 짧다면
						arr[i][j] = arr[i][k] + arr[k][j]; // 갱신
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(arr[i][j] == INF) sb.append(0).append(" ");
				else sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}
}
