import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11047_동전0 {
	static int N, K, ans;
	static int[] coins; // 동전 종류
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N]; 
		
		for (int i = 0; i < N; i++) { // 동전 종류 입력
			coins[i] = Integer.parseInt(br.readLine()); 
		}
		
		for (int i = N-1; i >= 0; i--) { // 동전의 가치가 큰 것부터
			if(K == 0) break; // 더 이상 계산해야할 금액이 없으면 종료
			if(coins[i] <= K) { // 계산해야할 금액보다 동전의 가치가 작을 때만
				ans += K / coins[i]; // 나눈 몫(개수)을 추가 
				K %= coins[i]; // 나눈 나머지 - 다음 계산해야 할 금액
			}
		}
		
		System.out.println(ans);
	}
}
