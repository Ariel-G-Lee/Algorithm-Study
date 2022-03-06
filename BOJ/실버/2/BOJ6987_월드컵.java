package Simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6987_월드컵 {
	static int[] win, draw, lose;
	static int[] A = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] B = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };
	static boolean isPossible;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		win = new int[6];
		draw = new int[6];
		lose = new int[6];

		for (int i = 0; i < 4; i++) {

			int sum = 0;

			isPossible = false;

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 6; j++) {
				win[j] = Integer.parseInt(st.nextToken());
				draw[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());
				sum += (win[j] + draw[j] + lose[j]);
			}

			if (sum == 30) {
				find(0);
			}

			if (isPossible)
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");

		}

		System.out.println(sb);

	}

	private static void find(int cnt) {
		if (isPossible)
			return;

		if (cnt == 15) {
			isPossible = true;
			return;
		}

		int teamA = A[cnt];
		int teamB = B[cnt];

		// A 가 승리
		if (win[teamA] > 0 && lose[teamB] > 0) {
			win[teamA]--;
			lose[teamB]--;
			find(cnt + 1);
			win[teamA]++;
			lose[teamB]++;
		}
		// B 가 승리
		if (lose[teamA] > 0 && win[teamB] > 0) {
			lose[teamA]--;
			win[teamB]--;
			find(cnt + 1);
			lose[teamA]++;
			win[teamB]++;
		}
		// 무승부
		if (draw[teamA] > 0 && draw[teamB] > 0) {
			draw[teamA]--;
			draw[teamB]--;
			find(cnt + 1);
			draw[teamA]++;
			draw[teamB]++;
		}

	}
}
