import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분집합
public class SWEA5215 {
	static int T, N, L, taste;
	static StringTokenizer st;
	static int[][] list;
	static boolean[] select;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			list = new int[N][2];
			select = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			taste = 0;
			find(0);
			System.out.println("#"+t+" "+taste);
			
		}

		
	}
	static void find(int cnt) {
		if(cnt == N) {
			int tmpT = 0;
			int tmpC = 0;
			for (int i = 0; i < N; i++) {
				if(select[i]) {
					tmpT += list[i][0];
					tmpC += list[i][1];
				}
			}
			if(tmpC <= L && tmpT > taste) taste = tmpT;
			return;
		}
		select[cnt] = true;
		find(cnt+1);
		select[cnt] = false;
		find(cnt+1);
	}

}
