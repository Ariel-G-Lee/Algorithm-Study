import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1873 {
	static int T, H, W, N; // 테스트 케이스 수, 맵의 높이, 맵의 너비, 사용자가 넣는 입력 개수
	static char[][] map; // 게임 맵
	static char[] userInput;
	static String str;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("1873_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 높이 입력
			W = Integer.parseInt(st.nextToken()); // 너비 입력
			map = new char[H][W]; // map 생성

			int cy = 0; // 현재 y 좌표
			int cx = 0; // 현재 x 좌표

			// 배열에 값 입력

			for (int i = 0; i < H; i++) {
				String s = br.readLine(); // 한 줄 읽어와서
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j); // char 로 쪼개서 담는다
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						// 입력받으면서 시작 좌표 저장
						cy = i;
						cx = j;
					}
				}
			}

			N = Integer.parseInt(br.readLine()); // 사용자 입력 개수
			str = br.readLine(); // 사용자 입력 한줄 받아오기
			for (int i = 0; i < str.length(); i++) {
				char input = str.charAt(i); // 각 문자마다
				if (input == 'U') { // Up을 받았을 경우\

					if ((cy-1) >= 0 && map[cy - 1][cx] == '.') { // 한칸 위가 평지라면
						map[cy][cx] = '.'; // 원래 있던 곳을 평지로 변경
						cy = cy - 1; // 현재 y 좌표 변경
					}

					map[cy][cx] = '^'; // 현재 위치에 방향 적용

				} else if (input == 'D') { // Down을 받았을 경우

					if ((cy+1) < H && map[cy + 1][cx] == '.') { // 한칸 아래가 평지라면
						map[cy][cx] = '.'; // 원래 있던 곳을 평지로 변경
						cy = cy + 1; // 현재 y 좌표 변경
					}

					map[cy][cx] = 'v'; // 현재 위치에 방향 적용

				} else if (input == 'L') { // Left를 받았을 경우

					if ((cx-1) >= 0 && map[cy][cx - 1] == '.') { // 한칸 왼쪽이 평지라면
						map[cy][cx] = '.'; // 원래 있던 곳을 평지로 변경
						cx = cx - 1; // 현재 x 좌표 변경
					}

					map[cy][cx] = '<'; // 현재 좌표에 방향 적용

				} else if (input == 'R') { // Right를 받았을 경우

					if ((cx+1) < W && map[cy][cx + 1] == '.') { // 한칸 왼쪽이 평지라면
						map[cy][cx] = '.'; // 원래 있던 곳을 평지로 변경
						cx = cx + 1; // 현재 x 좌표 변경
					}

					map[cy][cx] = '>'; // 현재 좌표에 방향 적용

				} else { // Shoot을 받았을 경우
					// 포탄의 시작 좌표
					int ty = cy;
					int tx = cx;
					// 진행 보조 변수
					int dy = 0;
					int dx = 0;
					char d = map[ty][tx];
					if (d == '^')
						dy = -1;
					else if (d == 'v')
						dy = 1;
					else if (d == '<')
						dx = -1;
					else
						dx = 1;
					while (true) {
						ty = ty + dy;
						tx = tx + dx;
						if (ty >= 0 && ty < H && tx >= 0 && tx < W) {
							if (map[ty][tx] == '#')
								break;
							if (map[ty][tx] == '*') {
								map[ty][tx] = '.';
								break;
							}
						} else {
							break;
						}

					}
				}
			}
			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}

		}

	}

}
