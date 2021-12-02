import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20061_모노미노도미노2 {
	static int N, t, x, y, point, cnt;
	static boolean[][] blue, green;
	static int[] blueCol, greenRow;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		blue = new boolean[4][6];
		blueCol = new int[6];
		green = new boolean[6][4];
		greenRow = new int[6];

		while (N -- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			t = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			// 놓을 수 있는 곳에 블록을 놓고
			placeOnMap();
			
			// 한줄이 지워지는지 확인하고 지워졌으면 당기고
			removeLine();
			
			// 0, 1 자리에 있는지 확인하고 있으면 지우고 당기고
			lightLine();
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if(blue[i][j]) cnt++;
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j]) cnt++;
			}
		}
		System.out.println(point);
		System.out.println(cnt);
	}

	private static void lightLine() {
		if(blueCol[1] > 0) moveBlue(5);
		if(blueCol[1] > 0) moveBlue(5);
		
		if(greenRow[1] > 0) moveGreen(5);
		if(greenRow[1] > 0) moveGreen(5);
	}

	private static void removeLine() {
		while(true) {
			boolean flag = true;
			for (int i = 0; i < 6; i++) {
				if(blueCol[i] == 4) {
					moveBlue(i);
					point++;
					flag = false;
					break;
				}
			}
			if(flag) break;
		}
		
		while(true) {
			boolean flag = true;
			for (int i = 0; i < 6; i++) {
				if(greenRow[i] == 4) {
					moveGreen(i);
					point++;
					flag = false;
					break;
				}
			}
			if(flag) break;
		}
		
	}

	private static void moveBlue(int idx) {
		for (int i = idx; i > 0; i--) {
			blue[0][i] = blue[0][i-1];
			blue[1][i] = blue[1][i-1];
			blue[2][i] = blue[2][i-1];
			blue[3][i] = blue[3][i-1];
			blueCol[i] = blueCol[i-1];
		}
		blue[0][0] = blue[1][0] = blue[2][0] = blue[3][0] = false;
		blueCol[0] = 0;
	}
	
	private static void moveGreen(int idx) {
		for (int i = idx; i > 0; i--) {
			green[i][0] = green[i-1][0];
			green[i][1] = green[i-1][1];
			green[i][2] = green[i-1][2];
			green[i][3] = green[i-1][3];
			greenRow[i] = greenRow[i-1];
		}
		green[0][0] = green[0][1] = green[0][2] = green[0][3] = false;
		greenRow[0] = 0;
	}

	private static void placeOnMap() {
		int bIdx = 0;
		int bNext = bIdx + 1;
		if (t == 3) {
			bNext = bIdx + 2;
		}
		// 일단 1차로 현재 놓을 수 있는 자리를 찾는다.
		while (true) {
			if (bNext >= 6 || green[bNext][x] || (t==2 && green[bNext][x+1]))
				break;
			bNext++;
			bIdx++;
		}

		// 다음 자리에 갈 수 없다면 현재 자리에서 놓기
		green[bIdx][x] = true;
		greenRow[bIdx]++;
		if (t == 2) {
			green[bIdx][x + 1] = true;
			greenRow[bIdx]++;
		} else if (t == 3) {
			green[bIdx + 1][x] = true;
			greenRow[bIdx + 1]++;
		}
		
		int gIdx = 0;
		int gNext = gIdx + 1;
		if (t == 2) {
			gNext = gIdx + 2;
		}
		// 일단 1차로 현재 놓을 수 있는 자리를 찾는다.
		while (true) {
			if (gNext >= 6 || blue[y][gNext] || (t==3 && blue[y+1][gNext]))
				break;
			gNext++;
			gIdx++;
		}

		// 다음 자리에 갈 수 없다면 현재 자리에서 놓기
		blue[y][gIdx] = true;
		blueCol[gIdx]++;
		if (t == 2) {
			blue[y][gIdx + 1] = true;
			blueCol[gIdx + 1]++;
		} else if (t == 3) {
			blue[y + 1][gIdx] = true;
			blueCol[gIdx]++;
		}

	}

}
