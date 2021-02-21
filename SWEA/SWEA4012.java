import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {
	static int T, N, half, ans;
	static int[][] s;
	static int[] a, b;
	static boolean[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("4012_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 초기화
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			s = new int[N][N];
			check = new boolean[N];
			half = N/2;
			a = new int[half];
			b = new int[half];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			select(0,0);
			System.out.println("#"+t+" "+ans);
			
			
		}
	}
	public static void select(int cnt, int start) {
		if(cnt == half) {
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if(!check[i]) b[idx++] = i;
			}
			find();
			return;
		}
		
		for (int i = start; i < N; i++) {
			check[i] = true;
			a[cnt] = i;
			select(cnt+1, i+1);
			check[i] = false;
		}
	}
	
	public static void find() {
		int aSum = 0;
		for (int i = 0; i < half; i++) {
			for (int j = 0; j < half; j++) {
				if(a[i]!=a[j]) aSum+=s[a[i]][a[j]];
			}
		}
		
		int bSum = 0;
		for (int i = 0; i < half; i++) {
			for (int j = 0; j < half; j++) {
				if(b[i]!=b[j]) bSum+=s[b[i]][b[j]];
			}
		}
		
		ans = Math.min(ans, Math.abs(aSum - bSum));
		
	}
	
}
