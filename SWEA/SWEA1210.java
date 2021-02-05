import java.util.Scanner;

public class SWEA1210 {

	static int[][] d = { { 0, 1 }, { 0, -1 }, { -1, 0 } };
	static int num, iy, ix;
	static int[][] map;
	public static void main(String[] args) {
		map = new int[100][100];
		Scanner sc = new Scanner(System.in);
		for (int t = 0; t < 10; t++) {
			
			num = sc.nextInt();
			
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					map[i][j] = sc.nextInt();

					if(map[i][j] == 2) {
						iy = i;
						ix = j;
					}
				}
			}
			


			while(true) {
				for (int i = 0; i < 3; i++) {
					int ty = iy + d[i][0];
					int tx = ix + d[i][1];
					if(ty>=0 && ty<100 && tx>=0 && tx<100 && map[ty][tx] == 1) {
						map[ty][tx] = 0;
						iy = ty;
						ix = tx;
						break;
					}
				}
				if(iy == 0) {
					System.out.println("#"+num+" "+ix);
					break;
				}
			}
		}
	}
}
