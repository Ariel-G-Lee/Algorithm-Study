package over;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2003_수들의합2 {
	static int N, M, sum, s, e, ans; // s - 시작 포인터 e - 끝 포인터
	static int[] number;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		number = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		s = 0;
		e = 0;
		sum = 0;
		while(true) {
			if(sum >= M) { // 합보다 크거나 같으면 
				sum -= number[s++]; // 시작 포인터를 뒤로 옮기면서 현재 시작 포인터 값을 뺀다.
			} else if(e == N){ // 끝 포인터가 끝까지 왔다면
				break; // 종료
			} else if(sum < M) { // 합보다 작으면
				sum += number[e++]; // 끝 포인터를다 크면 뒤로 옮기면서 현재 끝 포인터 값을 더한다.
			}
			
			if(sum == M) ans++; // M값과 같다면 카운트 +1
		}
		
		System.out.println(ans);
		
	}
}
