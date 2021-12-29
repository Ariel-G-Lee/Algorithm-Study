package over;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3184_양 {
	static int R, C, sCnt, wCnt;
	static char[][] map;
	static boolean[][] visit;
	static StringTokenizer st;
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
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visit[i][j] && map[i][j] != '#') find(i, j);	
			}
		}
		
		System.out.println(sCnt+" "+wCnt);
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find(int i, int j) {
		int tmpSCnt = 0;
		int tmpWCnt = 0;
		
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(i, j));
		visit[i][j] = true;
		
		if(map[i][j] == 'o') tmpSCnt++;
		else if(map[i][j] == 'v') tmpWCnt++;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if(ny<0 || nx<0 || ny>=R || nx>=C || map[ny][nx] == '#' || visit[ny][nx]) continue;
				q.offer(new Pos(ny, nx));
				visit[ny][nx] = true;
				if(map[ny][nx] == 'o') tmpSCnt++;
				else if(map[ny][nx] == 'v') tmpWCnt++;
				
			}
		}

		if(tmpSCnt > tmpWCnt) sCnt += tmpSCnt;
		else wCnt += tmpWCnt;
		
		
	}
}
