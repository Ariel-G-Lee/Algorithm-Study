import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N과 M(2)
 * 중복X 조합
 */
public class BOJ15650 {
	static int N, M;
	static int[] tmp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tmp = new int[9];
		
		find(0,1);
	}
	static void find(int cnt, int start) { // 몇번째인지를 세는 cnt, 시작 idx를 표시하는 start
		if(cnt == M) { // 최종 카운트와 같다면
			for(int i=0;i<M;i++) { // 배열 출력
				System.out.print(tmp[i]);
				if(i==M-1) System.out.println("");
				else System.out.print(" ");
			}
			return; 
		}
		
		for (int i = start; i <= N; i++) {
			tmp[cnt] = i; // 수를 넣는다
			find(cnt+1, i+1); // 다음 수는 지금 수보다 큰 수여야하므로 현재 i보다 1크게 설정
			
		}
	}
}
