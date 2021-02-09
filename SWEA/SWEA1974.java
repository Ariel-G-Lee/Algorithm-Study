import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1974 {
	static int T;
	static int[][] arr;
	static boolean flag;
	static boolean[] select;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("1974_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 스도쿠 배열 생성
			arr = new int[9][9];
			
			// 숫자 입력
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// flag 초기화
			flag = true;
			
			// 가로줄 검사
			// 행 번호
			for (int i = 0; i < 9; i++) {
				if (!check1(i)) {
					flag = false;
					break;
				}
			}
			if (flag == false) {
				System.out.println("#"+t+" "+0);
				continue;
			}

			// 세로줄 검사
			// 열 번호
			for (int j = 0; j < 9; j++) {
				if (!check2(j)) {
					flag = false;
					break;
				}
			}
			if (flag == false) {
				System.out.println("#"+t+" "+0);
				continue;
			}
			
			// 3x3 검사
			// 시작 좌표
			for (int i = 0; i <= 6; i+=3) {
				for (int j = 0; j <= 6; j+=3) {
					if(!check3(i,j)) {
						flag = false;
						break;
					}
				}
			}
			if (flag == false) {
				System.out.println("#"+t+" "+0);
				continue;
			}
			
			// 모두 체크를 끝냈을 경우
			System.out.println("#"+t+" "+1);
		}

	}

	static boolean check1(int i) {
		select = new boolean[10]; // 0번째 사용 안함
		for (int j = 0; j < 9; j++) {
			if (select[arr[i][j]]) return false; // 이미 사용된 숫자가 또 사용 됐을 경우 return false
			select[arr[i][j]] = true;
		}
		return true;
	}

	static boolean check2(int j) {
		select = new boolean[10]; // 0번째 사용 안함
		for (int i = 0; i < 9; i++) {
			if (select[arr[i][j]]) return false; // 이미 사용된 숫자가 또 사용 됐을 경우 return false
			select[arr[i][j]] = true;
		}
		return true;
	}
	
	static boolean check3(int x, int y) {
		select = new boolean[10]; // 0번째 사용 안함
		// 시작 좌표에서 각 +3 까지 돌면서 체크
		for (int i = x; i < x+3; i++) {
			for (int j = y; j < y+3; j++) {
				if(select[arr[i][j]]) return false; // 이미 사용된 숫자가 또 사용 됐을 경우 return false
				select[arr[i][j]] = true;
			}
		}
		return true;
	}
}
