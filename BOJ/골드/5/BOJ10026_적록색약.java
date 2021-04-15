package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10026_적록색약 {
	static char[][] map, cmap;
	static int N, cnt, ccnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		cmap = new char[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char tmp = s.charAt(j);
				map[i][j] = tmp;
				if (tmp == 'G')
					tmp = 'R';
				cmap[i][j] = tmp;
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(cmap[i]));
//		}

		// 일반적인 사람이 봤을때 구분
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 'o') {
					find(i, j, map[i][j]);
					cnt++;
				}
			}
		}
		
		// 적녹색약이 사람이 봤을때 구분
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cmap[i][j] != 'o') {
					cfind(i, j, cmap[i][j]);
					ccnt++;
				}
			}
		}
		
		

		System.out.println(cnt + " " + ccnt);

	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	private static void find(int y, int x, char p) {

		map[y][x] = 'o';

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 'o' || map[ny][nx] != p)
				continue;

			find(ny, nx, p);
		}

	}

	private static void cfind(int y, int x, char p) {

		cmap[y][x] = 'o';

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || cmap[ny][nx] == 'o' || cmap[ny][nx] != p)
				continue;

			cfind(ny, nx, p);
		}

	}
}
