import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1347_미로만들기 {
	static int N, dir, sy, sx, minY, minX, maxY, maxX;
	static char[][] map;
	// 하 -> 우 -> 상 -> 좌(L);
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[101][101];
		
		for (int i = 0; i < 100; i++) {
			Arrays.fill(map[i], '#');
		}
		// 초기 위치는 정 중간인 (50, 50)
		sy = 50;
		sx = 50;
		map[sy][sx]= '.';
		// 시작은 남쪽 (아래)
		dir = 0;
		
		minY = 50;
		minX = 50;
		maxY = 50;
		maxX = 50;
		
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			char c = str.charAt(i);
			
			switch(c) {
			case 'F':
				sy += dy[dir];
				sx += dx[dir];
				map[sy][sx] = '.';
				updatePoint();
				break;
			case 'R':
				dir = (dir+3)%4;
				break;
			case 'L':
				dir = (dir+1)%4;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = minY; i <= maxY; i++) {
			for (int j = minX; j <= maxX; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
	private static void updatePoint() {
		switch(dir){
		case 0:
			maxY = Math.max(maxY, sy);
			break;
		case 1:
			maxX = Math.max(maxX, sx);
			break;
		case 2:
			minY = Math.min(minY, sy); 
			break;
		case 3:
			minX = Math.min(minX, sx);
			break;
		}
	}
}
