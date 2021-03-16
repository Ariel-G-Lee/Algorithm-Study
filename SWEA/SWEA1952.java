import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952 {
	static int min;
	static int[] price, month;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1952_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			price = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			month = new int[12];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			find(0,0);
			System.out.println("#"+t+" "+min);
		}
		
	}
	
	public static void find(int idx, int sum) {
		if(idx >= 12) {
			min = Math.min(min, sum);
			return;
		}
		
		if(month[idx] != 0) {
			// 1일 이용권
			find(idx+1, sum+(month[idx]*price[0]));
			
			// 1달 이용권
			find(idx+1, sum+price[1]);
			
			// 3달 이용권
			find(idx+3, sum+price[2]);
			
			// 1년 이용권
			find(idx+12, sum+price[3]);
		} else find(idx+1, sum);
	}
}
