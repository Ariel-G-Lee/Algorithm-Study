import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991_로봇청소기 {
	static int w, h, ans, dust;
	static char[][] map;
	static int[][] dis;
	static boolean[] select;
	static List<Pos> list;
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
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w+h == 0) break;
			
			map = new char[h][w];
			list = new ArrayList<>(); 
			
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'o') {
						list.add(0, new Pos(i,j));
					} else if (map[i][j] == '*') {
						list.add(new Pos(i,j));
					}
				}
			}
			ans = Integer.MAX_VALUE;
			dust = list.size();
			claenUp();
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static void claenUp() {
		dis = new int[dust][dust];

		for (int i = 0; i < dust; i++) {
			for (int j = i+1; j < dust; j++) {
				int moveCnt = findDis(list.get(i), list.get(j));
				if(moveCnt == -1) {
					ans = -1;
					return;
				} else {
					dis[i][j] = dis[j][i] = moveCnt;
				}
			}
		}
		select = new boolean[dust];
		findMin(0, 0, 0);
	}
	private static void findMin(int idx, int cnt, int sum) {
		if(cnt == dust-1) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = 1; i < dust; i++) {
			if(select[i]) continue;
			select[i] = true;
			findMin(i, cnt+1, sum+dis[idx][i]);
			select[i] = false;
		}
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	private static int findDis(Pos p1, Pos p2) {
		boolean[][] visit = new boolean[h][w];
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(p1);
		visit[p1.y][p1.x] = true;
		
		int moveCnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size --> 0) {
				Pos cur = q.poll();
				if(cur.y == p2.y && cur.x == p2.x) return moveCnt;
				
				for (int d = 0; d < 4; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if(ny<0 || nx<0 || ny>=h || nx>=w || visit[ny][nx] || map[ny][nx] == 'x') continue;
					q.offer(new Pos(ny, nx));
					visit[ny][nx] = true;
				}
			}
			moveCnt++;
		}
		return -1;
	}
}
