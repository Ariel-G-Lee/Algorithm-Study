package over;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1476_날짜계산 {
	static int E, S, M, e, s, m, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = 0; // 연도를 올려가면서 계산
		while(true) {
			// 숫자가 일치하면 break;
			if(e==E && s==S && m==M) break;
			
			ans++; // 연도 증가
			
			e++; // 지구 수 증가
			if(e == 16) e = 1; // 범위가 벗어나면 1로
			s++; // 태양 수 증가
			if(s == 29) s = 1;
			m++; // 달 수 증가
			if(m == 20) m = 1;			
		}
		
		// 출력
		System.out.println(ans);
		
		
	}
}
