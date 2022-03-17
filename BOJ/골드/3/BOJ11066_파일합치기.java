import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066_파일합치기 {
	//https://www.acmicpc.net/problem/11066
	static int T, K, ans;
	static int[] arr, sum;
	static int[][] dp;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());

		sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			K = Integer.parseInt(br.readLine());
			arr = new int[K+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			find();
		}
		
		System.out.println(sb);
	}
	private static void find() {
		// i번째 챕터부터 j번째 챕터를 합치는 최솟값
		dp = new int[K+1][K+1];
		sum = new int[K+1];
		
		sum[1] = arr[1];
		// 순서대로 합치기
		for (int i = 2; i <= K; i++) {
			sum[i] = sum[i-1]+arr[i];
		}
		
		// a+b, b+c, c+d
		// 인접한 두 파일 합치기
		for (int i = 1; i < K; i++) {
			dp[i][i+1] = arr[i]+arr[i+1];
		}
		
		// a+b+c의 경우
		// (a+b)+(a+b+c)
		// (b+c)+(a+b+c) 중 최소를 고르면 된다.
		
		// a+b+c+d
		// (b+c+d)+(a+b+c+d)
		// (a+b)+(c+d)+(a+b+c+d)
		// (a+b+c)+(a+b+c+d) 중 최소를 고르면 된다.
		for (int size = 2; size <= K; size++) {
			for (int i = 1; i+size <= K; i++) {
				int j = i+size;
				dp[i][j] = Integer.MAX_VALUE;
				
				for (int k = i; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k+1][j]+sum(i, j));
				}
			}
		}
		
		sb.append(dp[1][K]).append("\n");
		
	}
	private static int sum(int i, int j) {
		if(i==1) return sum[j];
		else return sum[j]-sum[i-1];
	}
}
