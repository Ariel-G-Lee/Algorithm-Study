import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2931_가스관 {
	static int R, C, my, mx, zy, zx, mdir, zdir, ay, ax;
	static Pos posM, posZ;
	static char ans;
	static char[][] map;
	static StringTokenizer st;
	static class Pos{
		int y;
		int x;
		int dir;
		public Pos(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	static char[] list = {'|', '-', '+', '1', '2', '3', '4'};
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'M'){
					my = i;
					mx = j;
				} else if(map[i][j] == 'Z') {
					zy = i;
					zx = j;
				}
			}
		}
		
		
		sb = new StringBuilder();

		// M근접이나 Z근접에서 파이프가 빠졌을때를 고려하지 않음...
		mdir = setStart(my, mx);
		zdir = setStart(zy, zx);
		if(mdir != -1) {
			findPos(mdir, my, mx, 'Z');			
		} else if (mdir == -1 && zdir != -1){
			findPos(zdir, zy, zx, 'M');
		} else {
			findLine();
		}
		
		
		System.out.println(sb);
	}
	
	private static void findLine() {
		if(my > zy) {
			sb.append(my).append(" ").append(mx+1).append(" ");
			if(mx > zx) {
				sb.append("4");
			} else if (mx == zx) {
				sb.append("|");
			} else {
				sb.append("1");
			}
		} else if (my == zy) {
			if(mx > zx) {
				sb.append(my+1).append(" ").append(mx).append(" ");
			} else {
				sb.append(my+1).append(" ").append(mx+1).append(" ");
			}
			sb.append("-");		
		} else {
			sb.append(my+2).append(" ").append(mx+1).append(" ");
			if(mx > zx) {
				sb.append("3");
			} else if (mx == zx) {
				sb.append("|");
			} else {
				sb.append("2");
			}			
		}
		
	}

	private static int setStart(int sy, int sx) {
		int dir = -1;
		for (int d = 0; d < 4; d++) {
			int ny = sy+dy[d];
			int nx = sx+dx[d];
			if(ny<0 || nx<0 || ny>=R || nx>=C) continue;
			switch(d){
			case 0:	// 좌 
				if(map[ny][nx] == '-' || map[ny][nx] == '+' || map[ny][nx] == '2' || map[ny][nx] == '1')
					dir = 0;
				break;
			case 1: // 상 
				if(map[ny][nx] == '|' || map[ny][nx] == '+' || map[ny][nx] == '1' || map[ny][nx] == '4')
					dir = 1;
				break;
			case 2: // 우 
				if(map[ny][nx] == '-' || map[ny][nx] == '+' || map[ny][nx] == '4' || map[ny][nx] == '3')
					dir = 2;
				break;
			case 3: // 하 
				if(map[ny][nx] == '|' || map[ny][nx] == '+' || map[ny][nx] == '3' || map[ny][nx] == '2')
					dir = 3;
				break;
			}
		}
		return dir;
	}
	// 좌 상 우 하 
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	private static void findPos(int dir, int y, int x, char c) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(y, x, dir));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int ny = cur.y+dy[cur.dir];
			int nx = cur.x+dx[cur.dir];
			
			char next = map[ny][nx];
			int nDir = nextDir(cur.dir, next);
			if(nDir != -1) {
				q.offer(new Pos(ny, nx, nDir));
				continue;
			}
			
			sb.append(ny+1).append(" ").append(nx+1).append(" ");
			for (int i = 0; i < 7; i++) {
				char setNext = list[i];
				map[ny][nx] = setNext;
				if(!checkDir(cur.dir, setNext, c)) continue;
				int setnDir = nextDir(cur.dir, setNext);
				
				if(isPossible(ny, nx, setnDir, c)) {
					sb.append(list[i]);
					break;
				}
			}
		}
	}
	
	private static boolean checkDir(int dir, char setNext, char c) {
		boolean flag = false;
		if(dir == 0) {
			if(setNext == '-' || setNext == '+' || setNext == '2' || setNext == '1' || setNext == c)
				flag = true;
		} else if (dir == 1) {
			if(setNext == '|' || setNext == '+' || setNext == '1' || setNext == '4' || setNext == c)
				flag = true;
		} else if (dir == 2) {
			if(setNext == '-' || setNext == '+' || setNext == '4' || setNext == '3' || setNext == c)
				flag = true;
		} else {
			if(setNext == '|' || setNext == '+' || setNext == '3' || setNext == '2' || setNext == c)
				flag = true;
		}
		return flag;
	}

	private static boolean isPossible(int y, int x, int dir, char c) {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(y, x, dir));
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			if(map[cur.y][cur.x] == c) {
				return true;
			}
			
			int ny = cur.y+dy[cur.dir];
			int nx = cur.x+dx[cur.dir];
			if(ny<0 || nx<0 || ny>=R || nx>=C) return false;
			
			char next = map[ny][nx];
			if(!checkDir(cur.dir, next, c)) return false;
			int nDir = nextDir(cur.dir, next);
			if(nDir == -1) {
				return false;
			} else {

				q.offer(new Pos(ny, nx, nDir));
			}
		}
		
		return false;
	}
	private static int nextDir(int curDir, char next) {
		int nDir = -1;
		switch(next){
		case '|':
			nDir = curDir;
			break;
		case '-':
			nDir = curDir;
			break;
		case '+':
			nDir = curDir;
			break;
		case '1':
			if(curDir == 0) nDir = 3;
			else if(curDir == 1) nDir = 2;
			break;
		case '2':
			if(curDir == 0) nDir = 1;
			else if(curDir == 3) nDir = 2;
			break;
		case '3':
			if(curDir == 2) nDir = 1;
			else if(curDir == 3) nDir = 0;
			break;
		case '4':
			if(curDir == 1) nDir = 0;
			else if(curDir == 2) nDir = 3;
			break;
		case 'M':
			nDir = curDir;
			break;
		case 'Z':
			nDir = curDir;
			break;
		}
		
		return nDir;
	}
}
