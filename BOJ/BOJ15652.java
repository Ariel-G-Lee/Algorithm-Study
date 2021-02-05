import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N과 M(4)
 * 중복O 조합
 */
public class BOJ15652 {
	static int M, N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder("");
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		find(0, 1);
		System.out.println(sb.toString());
	}
	
	static void find(int cnt, int start) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i]);
				if(i == M-1) break;
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = start; i <= N; i++) {
			arr[cnt]= i;
			find(cnt+1, i); // 오름차순으로 나와야하므로 현재와 index에서 시작
		}
	}
}
