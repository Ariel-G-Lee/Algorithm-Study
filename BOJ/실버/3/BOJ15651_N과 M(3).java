import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N과 M(3)
 * 중복O 순열
 */
public class BOJ15651 {
	static int M, N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder("");
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		find(0);
		System.out.println(sb.toString());
	}
	
	static void find(int cnt) {
		if(cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i]);
				if(i == M-1) break;
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			arr[cnt]= i;
			find(cnt+1);
		}
	}
}
