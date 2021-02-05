import java.util.Scanner;
/*
 * 달팽이 숫자
 */
public class SWEA1954 {
	static int T, N;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			
			int number = 1;
			int count = N; // 얼마나 더할지를 결정 5->4->4->3->3->....->1->1
			int p = 1; // + or - 결정
			int y = 0; // 시작 y
			int x = -1; // 시작 x (시작하면서 +1)
			while(number <= N*N) { // 유효한 숫자까지만 출력
				
				for (int i = 0; i < count; i++) { // 열 이동
					x = x+p;
					map[y][x] = number++;
				}
				count--; // 수 줄임
				for (int i = 0; i < count; i++) { // 행 이동
					y = y+p;
					map[y][x] =number++;
				}
				p *= -1; // 열->행 반복적으로 + or -
			}
			
			System.out.println("#"+t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
}
