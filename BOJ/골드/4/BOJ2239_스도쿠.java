import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239_스도쿠 {
	static final int N = 9;
	static int size;
	static int[][] map;
	static ArrayList<Pos> list;
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j)-'0';
				// 0인 애들을 list에 넣는다.
				if(map[i][j] == 0) list.add(new Pos(i,j));
			}
		}
		
		// 채우기 위한 리스트 크기 확인
		size = list.size();
		find(0);
		
	}
	private static void find(int idx) {
		
		// 0인 부분을 다 채웠으면
		if(idx == size) {
			// 출력하고 종료
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		// 현재 idx의 좌표값을 꺼낸다.
		Pos cur = list.get(idx);
		
		for (int i = 1; i <= N; i++) {
			// 그 값을 놨을 때 행, 열, 사각형 검사 -> 값이 중복된다면 다음 값으로 넘어간다.
			if(!rowCheck(cur.y, i) || !colCheck(cur.x, i) || !squareCheck(cur.y, cur.x, i)) continue;
			
			// 나중에 원상복구 시키기 위해 저장
			int origin = map[cur.y][cur.x];
			
			// 놓아도 되는 값이라고 판단된다면 놓고
			map[cur.y][cur.x] = i;
			
			// 다음 idx의 값 찾으러 가기
			find(idx+1);
			
			map[cur.y][cur.x] = origin;
		}
	}
	
	// 체크하는 범위 내에 그 값이 이미 있으면 false 리턴
	private static boolean rowCheck(int y, int num) {
		
		for (int i = 0; i < N; i++) {
			if(map[y][i] == num) return false;
		}
		return true;
	}
	
	private static boolean colCheck(int x,  int num) {
		for (int i = 0; i < N; i++) {
			if(map[i][x] == num) return false;
		}
		return true;
	}
	
	private static boolean squareCheck(int y, int x, int num) {
		int sy = y/3*3;
		int sx = x/3*3;
		for (int i = sy; i < sy+3; i++) {
			for (int j = sx; j < sx+3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}
}
