import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_경사로 {
	static int N, L, ans;
	static int[][] map;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		checkRow();
		checkCol();
		
		System.out.println(ans);

	}
	private static void checkCol() {
		for (int i = 0; i < N; i++) {
			int lastIdx = -1; // 경사로 설치한 다음 위치
			int j = 1;
			while(j<N) {
				if(Math.abs(map[j-1][i] - map[j][i]) > 1) break;
				
				// 단차가 있는데
				if(map[j-1][i] > map[j][i]) { // 윗쪽이 높음
					// 경사로를 만들 수 없다면 해당 col는 끝
					if(j+L-1 >= N) break;
					
					if(!checkForward(1, map[j][i], i, j)) break;
					else { // 경사로를 만들 수 있다면
						lastIdx = j+L-1;
						j = j+L;
					}
				} else if(map[j-1][i] < map[j][i]){ // 아래쪽이 높음
					// 경사로를 만들 수 없다면 해당 col는 끝
					if(j-L < 0 || lastIdx >= j-L) break;
					
					if(!checkBackward(1, map[j-1][i], i, j-1)) break;
					else {
						lastIdx = j-1;
						j++;
					}
				} else {
					j++;
				}
				
				if(j == N) ans++;
				
			}

		}
	}
	private static void checkRow() {

		for (int i = 0; i < N; i++) {
			int lastIdx = -1; // 경사로 설치한 다음 위치
			int j = 1;
			while(j<N) {
				if(Math.abs(map[i][j-1] - map[i][j]) > 1) break;
				
				// 단차가 있는데
				if(map[i][j-1] > map[i][j]) { // 왼쪽이 높음
					// 경사로를 만들 수 없다면 해당 row는 끝
					if(j+L-1 >= N ) break;
					
					if(!checkForward(0, map[i][j], i, j)) break;
					else { // 경사로를 만들 수 있다면
						lastIdx = j+L-1;
						j = j+L;
					}
				} else if(map[i][j-1] < map[i][j]){ // 오른쪽이 높음
					// 경사로를 만들 수 없다면 해당 row는 끝
					if(j-L < 0 || lastIdx >= j-L) break;
					
					if(!checkBackward(0, map[i][j-1], i, j-1)) break;
					else {
						lastIdx = j-1;
						j++;
					}
				} else {
					j++;
				}
				
				if(j == N) ans++;
				
			}

		}
		
	}
	private static boolean checkBackward(int d, int n, int i, int j) {
		if(d == 0) {
			for (int k = j-1; k > j-L; k--) {
				if(n!=map[i][k]) return false;
			}
			return true;
		} else {
			for (int k = j-1; k > j-L; k--) {
				if(n!=map[k][i]) return false;
			}
			return true;
		}
	}
	private static boolean checkForward(int d, int n, int i, int j) {
		if(d == 0) {
			for (int k = j+1; k < j+L; k++) {
				if(n!=map[i][k]) return false;
			}
			return true;
		} else {
			for (int k = j+1; k < j+L; k++) {
				if(n!=map[k][i]) return false;
			}
			return true;
		}
		
	}
}
