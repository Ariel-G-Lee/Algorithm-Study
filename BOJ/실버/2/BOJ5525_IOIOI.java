package over;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5525_IOIOI {
	static int N, M, S, ans;
	static String str, pn;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		str = br.readLine();
		
		StringBuilder sb = new StringBuilder("I");
		for (int i = 0; i < N; i++) {
			sb.append("OI");
		}
		
		pn = sb.toString();
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(pn.charAt(cnt) == str.charAt(i)) { // 두 문자가 같다면
				cnt++;
			} else { // 두 문자가 다르고
				if(str.charAt(i) == 'I') { // str의 문자가 I라면 비교 시작
					cnt = 1; // 다음 비교는 1번째랑 하면 됨
				} else { // O라면
					cnt = 0;
				}
			}
			if(cnt == 2*N+1) { // pn의 끝까지 비교했다면
				ans++; // 정답 개수에 추가
				cnt-=2; // IOIOIOIOI 에서 맨 앞 I의 다음 I가 시작점
			}

		}
		
		System.out.println(ans);
		
	}
}
