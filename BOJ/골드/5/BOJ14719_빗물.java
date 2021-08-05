import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719_빗물 {
	static int H, W, lMax, rMax, ans;
	static int[] map;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		// i가 현재 선택한 칸
		for (int i = 0; i < W; i++) {
			// 현재를 기준으로 왼쪽에서 가장 높은 블록 찾기
			lMax = 0;
			for (int j = i-1; j >= 0; j--) {
				lMax = Math.max(lMax, map[j]);
			}
			// 현재를 기준으로 오른쪽에서 가장 높은 블록 찾기
			rMax = 0;
			for (int j = i+1; j < W; j++) {
				rMax = Math.max(rMax, map[j]);
			}
			// 양쪽 높이보다 현재가 작을 때만
			if(map[i] < lMax && map[i] < rMax) {
				// 양쪽 높이 중 작은 쪽과 내 높이 차만큼 물이 고인다.
				ans += Math.min(lMax, rMax) - map[i];
			}
			
		}
		
		System.out.println(ans);
	}

}
