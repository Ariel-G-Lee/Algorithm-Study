package over;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912_연속합 {
	static int n, ans;
	static int[] number, arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		number = new int[n];
		arr = new int[n]; // 최대값 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		arr[0] = number[0];
		ans = arr[0];
		for (int i = 1; i < n; i++) {
			arr[i] = Math.max(arr[i-1]+number[i], number[i]); // 기존 값에 더하는 것과 현재 수 중에 큰 수 저장
			ans = Math.max(arr[i], ans); // 최대 값 갱신
		}

		System.out.println(ans);
		
	}
}
