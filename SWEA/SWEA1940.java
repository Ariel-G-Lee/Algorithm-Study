import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1940 {
	static int T, N, v, s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			int distance = 0;
			int currentSpeed = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				v = Integer.parseInt(st.nextToken());
				if(v == 1 || v== 2) { // 가속이나 감속이면 가속도 값을 받는다
					s = Integer.parseInt(st.nextToken());
					if(v==1) currentSpeed+= s; // 가속
					else if(v==2) { // 감속
						if(currentSpeed < s) { // 감속 속도가 현재 속도보다 작다면 0 
							currentSpeed = 0;
						} else {
							currentSpeed-=s; // 아니라면 감속
						}
					}
				} 
				distance += currentSpeed; // 시*속 = 거리
			}
			
			System.out.println("#"+t+" "+distance);
		}
	}
}
