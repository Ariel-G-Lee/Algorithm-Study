import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA2805 {
	static int T, N;
	static int[][] farm;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = s.charAt(j) - '0';
				}
			}
			
			int center = N/2;
			int start = 0;
			int end = 0;
			int p = 0;
			int result = 0;
			for (int i = 0; i < N; i++) {
				start = center - p;
				end = center + p;
				for (int j = start; j <= end; j++) {
					result += farm[i][j];
				}
				if(i<center) p++;
				else p--;
			}
			System.out.println("#" + t + " " + result);
		}
		
	}
}
