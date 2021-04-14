package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144_미세먼지안녕 {

	static int R, C, T;
	static int[][] map;
	static int[] acy;
	static int[] acx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		acy = new int[2];
		acx = new int[2];

		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					acy[idx] = i;
					acx[idx] = j;
					idx++;
				}
			}
		}

		for (int t = 0; t < T; t++) {

			int[][] tmp = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int cnt = 0;
					if (map[i][j] > 0) {
						for (int d = 0; d < 4; d++) {
							int ny = i + dy[d];
							int nx = j + dx[d];
							if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == -1)
								continue;

							tmp[ny][nx] += map[i][j] / 5;
							cnt++;
						}
					}
					map[i][j] -= (map[i][j] / 5) * cnt;
				}

			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += tmp[i][j];
				}
			}

			for (int i = acy[0] - 1; i > 0; i--) {
				map[i][0] = map[i - 1][0];
			}

			for (int i = 0; i < C - 1; i++) {
				map[0][i] = map[0][i + 1];
			}

			for (int i = 0; i < acy[0]; i++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}

			for (int i = C - 1; i >= 1; i--) {
				map[acy[0]][i] = map[acy[0]][i - 1];
				if (i == 1)
					map[acy[0]][i] = 0;
			}

			for (int i = acy[1] + 1; i < R - 1; i++) {
				map[i][0] = map[i + 1][0];
			}

			for (int i = 0; i < C - 1; i++) {
				map[R - 1][i] = map[R - 1][i + 1];
			}

			for (int i = R - 1; i > acy[1]; i--) {
				map[i][C - 1] = map[i - 1][C - 1];
			}

			for (int i = C - 1; i >= 1; i--) {
				map[acy[1]][i] = map[acy[1]][i - 1];
				if (i == 1)
					map[acy[1]][i] = 0;
			}

		}

		int sum = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != -1)
					sum += map[i][j];
			}
		}

		System.out.println(sum);
	}

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
}
