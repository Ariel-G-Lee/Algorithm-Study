package self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10836_여왕벌 {
	static int N, M;
	static int arr[][], num[];
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], 1);
		}
		
		num = new int[3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			num[0] = Integer.parseInt(st.nextToken());
			num[1] = Integer.parseInt(st.nextToken());
			num[2] = Integer.parseInt(st.nextToken());
			
			growup();
			
			
		}
		
		// 촤측에서 올라갈수록 상단에서 우측으로 갈수록 숫자가 커짐
		// 결국 왼쪽, 왼쪽위, 위 중에 가장 크게 증가하는 숫자는 위임
		// 위랑 숫자가 똑같아진다.
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				arr[i][j] = arr[0][j];
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	// 최상단, 최좌단 숫자 늘려가기
	private static void growup() {
		for (int j = N-1; j > 0; j--) {
			if(num[2] != 0) {
				arr[0][j] += 2;
				num[2]--;
			} else if (num[1] != 0) {
				arr[0][j] += 1;
				num[1]--;
			}
		}
		
		for (int i = 0; i < N ; i++) {
			if(num[2] != 0) {
				arr[i][0] += 2;
				num[2]--;
			} else if (num[1] != 0) {
				arr[i][0] += 1;
				num[1]--;
			}
			
		}
		
	}
}
