import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499_주사위굴리기 {
	static int N, M, y, x, k, frontW, frontH, back;
	static int[][] map;
	static StringTokenizer st;
	static int[] dice;
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	//    2
	// 4  1  3
	//    5
	//    6
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new int[7];
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < k; i++) {
			
			int move = Integer.parseInt(st.nextToken());
			
			// 이동할 좌표
			int ny = y + dy[move-1];
			int nx = x + dx[move-1];
			
			// 범위 밖이면 넘어간다.
			if(ny<0 || nx<0 || ny>=N || nx>= M) continue;
			
			// 범위 안에 들어오면 좌표 바꾸고
			y = ny;
			x = nx;
			
			changeDice(move); // 주사위 굴리기(각 면의 숫자들을 바꿔준다)
			
			copy(); // 바닥면 복사
			
			sb.append(dice[1]).append("\n"); // 윗면에 있는 수 출력
		
		}
		System.out.println(sb);
		
	}

	private static void copy() {
		//이동한 칸에 쓰여 있는 수가 0이면 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
		if(map[y][x] == 0) map[y][x] = dice[6];
		// 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
		// 칸에 쓰여 있는 수는 0이 된다.
		else {
			dice[6] = map[y][x];
			map[y][x] = 0;
		}
	}
	
	private static void changeDice(int move) {
		int tmp = 0;
		if(move == 1) { // 동쪽 오른쪽
			tmp = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = dice[1];
			dice[1] = tmp;
			
		} else if (move == 2) { // 서쪽 왼쪽
			tmp = dice[4];
			dice[4] = dice[1];
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = tmp;
			
		} else if( move == 3) { // 북쪽 위쪽
			tmp = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = tmp;
			
		} else { // 남쪽 아래
			tmp = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
		}
	}

}
