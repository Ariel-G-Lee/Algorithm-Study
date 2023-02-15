import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2174_로봇시뮬레이션 {
	static int A, B, N, M;
	static int[][] map;
	static Pos[] robot;
	static StringTokenizer st;
	static class Pos{
		int x;
		int y;
		int dir;
		public Pos(int x, int y, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		map = new int[B+1][A+1];
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		robot = new Pos[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[y][x] = i;
			char d = st.nextToken().charAt(0);
			int dir = 0;
			switch(d){
				case 'N':
					dir = 2;
					break;
				case 'E':
					dir = 1;
					break;
				case 'S':
					dir = 0;
					break;
				case 'W':
					dir = 3;
					break;
			}
			robot[i] = new Pos(x, y, dir);
		}
		
		boolean flag = true;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			
			int checkN = check(n, c, cnt);
			if (checkN == -1){
				flag = false;
				System.out.print("Robot "+n+" crashes into the wall");
				break;
			} else if (checkN > 0) {
				flag = false;
				System.out.print("Robot "+n+" crashes into robot "+checkN);
				break;
			}
		}
		
		if(flag) System.out.println("OK");
		
	}
	private static int check(int n, char c, int cnt) {
		Pos cur = robot[n];
		if (c == 'R') {
			int tmp = cur.dir - cnt;
			while(tmp < 0) {
				tmp += 4;
			}
			cur.dir = tmp;
		} else if (c == 'L'){
			cur.dir = (cur.dir + cnt)%4;
		} else {
			int ny = cur.y;
			int nx = cur.x;
			for(int i=0; i<cnt; i++) {
				ny += dy[cur.dir];
				nx += dx[cur.dir];
				if(ny<=0 || nx<=0 || ny>=(B+1) || nx>=(A+1)) return -1;
				if(map[ny][nx] != 0) return map[ny][nx];
			}
			map[cur.y][cur.x] = 0;
			map[ny][nx] = n;
			cur.x = nx;
			cur.y = ny;
		}
		return 0;
	}
}
