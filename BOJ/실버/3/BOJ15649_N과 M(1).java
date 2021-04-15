import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N과 M(1)
 * 중복X 순열
 */
public class BOJ15649 {
	static boolean[] isChecked;
	static int[] tmp;
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isChecked = new boolean[9];
		tmp = new int[9];
		find(0);
	}
	static void find(int cnt) { 
		
		if(cnt == M) { // 최종 카운트와 같다면 
			for(int i=0;i<M;i++) { // 배열 출력
				System.out.print(tmp[i]+" ");
			}
			System.out.println("");
			return; 
		}
		
		for(int i =1;i<=N;i++) { // 수의 범위 1~N
			
			if(isChecked[i]) continue; // 이미 사용하고 있다면 제외
			tmp[cnt]=i; // 사용하지 않은 숫자를 배열에 넣고
			isChecked[i]=true; // 사용하고 있다고 표시
			find(cnt+1); // 카운트 증가
			isChecked[i]=false; // 종료후에 다시 미방문으로 표시
			
		}
	}
}
