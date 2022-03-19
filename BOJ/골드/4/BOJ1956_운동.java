import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1956_운동 {
	static int V, E;
	static int[][] map;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new int[V+1][V+1];
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if(i==j) continue;
				map[i][j] = 987654321;
			}
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map[a][b] = w;
		}
		
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				if(k==i) continue;
				for (int j = 1; j <= V; j++) {
					if(i==j || k==j) continue;
					if(map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			for (int j = i; j <= V; j++) {
				if(i==j) continue;
				if(map[i][j]!=987654321 && map[j][i]!=987654321) {
					ans = Math.min(ans, map[i][j]+map[j][i]);
				}
			}
		}
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
}
