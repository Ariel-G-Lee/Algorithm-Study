import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA1208 {
	private static int cnt, T;
	private static int[] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		map = new int[100];
		T = 10;

		for (int t = 1; t <= T; t++) {
			cnt = sc.nextInt(); // 덤프 횟수


			for (int i = 0; i < 100; i++) {
				map[i] = sc.nextInt();
			}
			for (int i = 0; i < cnt; i++) {
				// 정렬 -> 최소, 최대를 찾는다
				Arrays.sort(map);
				map[0]++;
				map[99]--;
				Arrays.sort(map);
			}
			
			System.out.println("#" + t + " " + (map[99] - map[0]));

		}
	}
}
