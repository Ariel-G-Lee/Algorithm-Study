import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1206 {
	static int T, N, ans;
	static int[] buildings;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = 10;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			ans = 0;
			 N = Integer.parseInt(br.readLine());
			 buildings = new int[N];
			 
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 for (int i = 0; i < N; i++) {
				 buildings[i] = Integer.parseInt(st.nextToken());
			 }
			 
			 for (int i = 2; i < N-2; i++) {
				int l = findLeft(i);
				int r = findRight(i);
				if(l<0 || r <0) continue;
				ans += Math.min(l, r);
			}
			
			 sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int findLeft(int idx) {
		int h = buildings[idx];
		int min = h;
		int cnt = 0;
		int cur = idx;
		while(true) {
			cur--;
			if(cnt == 2) break;
			min = Math.min(min, h - buildings[cur]);
			cnt++;
		}
		return min;
	}
	
	private static int findRight(int idx) {
		int h = buildings[idx];
		int min = h;
		int cnt = 0;
		int cur = idx;
		while(true) {
			cur++;
			if(cnt == 2) break;
			min = Math.min(min, h - buildings[cur]);
			cnt++;
		}
		return min;
	}
}
