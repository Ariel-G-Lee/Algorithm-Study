import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9663_NQueen {
	static int N, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 놓을 수 있는 모든 경우의 수 찾기
		// 배열에 몇번째 행에 넣는지 저장
		for (int i = 0; i < N; i++) {
			int[] line = new int[N];
			line[0] = i;
			find(line, 0);
		}
		
		System.out.println(ans);
	}
	private static void find(int[] line, int cnt) {
		if(cnt == N-1) {
			// 놓을 수 있는 개수만큼 다 놓았다면 카운트 증가
			ans++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			// 일단 놓고
			line[cnt+1] = i;
			// 놓을 수 있는 자리인지 판단한다.
			if(isOk(line, cnt+1)) {
				// 가능하다면 다음단계로
				find(line, cnt+1);
			}
		}
		
	}
	private static boolean isOk(int[] line, int cnt) {
		// 이전에 놓은 것들과 비교해서
		for (int i = 0; i < cnt; i++) {
			// 같은 행에 있거나 대각선에 있으면 놓을 수 없음
			if(line[i] == line[cnt] || (Math.abs(line[cnt] - line[i]) - Math.abs(cnt-i)) == 0) return false;
		}
		return true;
	}
}
