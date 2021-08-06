import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16938_캠프준비 {
	static int N, L, R, X, ans;
	static int[] arr;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		System.out.println(ans);
		
	}
	
	
	public static void dfs(int cnt, int srcIdx, int sum, int max, int min) {
		if(cnt >= 2) { // 문제는 2개 이상이어야한다. -> 2개 이상인 경우 모두 확인
			// 문제 난이도의 합은 L <=  <= R, 가장 어렵고 쉬운 문제의 난이도 차이는  >= X
			if(L<=sum && sum<=R && max-min>=X) {
				ans++; // 가능한 횟수에 추가
			}
		}
		
		if(srcIdx == N) return;
		
		for (int i = srcIdx; i < N ; i++) {
			// 카운트 늘리고 현재 i에서 +1
			// 이때 합, 최대, 최소 계산해서 함께 넘긴다.
			dfs(cnt+1, i+1, sum+arr[i], Math.max(max, arr[i]), Math.min(min, arr[i]));
		}
	}

}
