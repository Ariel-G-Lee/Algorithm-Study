import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486 {
	static int N, B, ans, M;
	static int[] h;
	static boolean[] select;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1486_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			h = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;

//			for (int i = 0; i < N; i++) {
//				M = i+1;
//				select = new boolean[N];
//				find(0,0);
//			}
			
			select = new boolean[N];
			find(0);
			
			System.out.println("#"+t+" "+(ans-B));
		}
	}
	

//	static void find(int cnt, int start) {
//		if(cnt == M) {
//			int sum = 0;
//			for (int i = 0; i < N; i++) {
//				if(select[i]) sum+=h[i];
//			}
//			
//			// B랑 같거나 넘는 경우만 확인
//			if(sum >= B) {
//				ans = ans > sum ? sum : ans;
//			}
//			return;
//		}
//		
//		for (int i = start; i < N; i++) {
//			select[i] = true;
//			find(cnt+1, i+1);
//			select[i] = false;
//		}
//	}
	
	static void find(int cnt) {
	if(cnt == N) {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if(select[i]) sum+=h[i];
		}
		
		// B랑 같거나 넘는 경우만 확인
		if(sum >= B) {
			ans = ans > sum ? sum : ans;
		}
		return;
	}
	
		select[cnt] = true;
		find(cnt+1);
		select[cnt] = false;
		find(cnt+1);
	}
}
	
