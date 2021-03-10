import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {
	static int N, M, max;
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<Pos> v;
	static ArrayList<Pos> n;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		v = new ArrayList<>();
		n = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if(tmp == 2) v.add(new Pos(i,j));
				else if (tmp == 0) n.add(new Pos(i,j));
			}
		}
		
		max = -1;
		setWall(0,0);
		System.out.println(max);
		
		
	}
	
	public static void setWall(int cnt, int idx) {
		if(cnt == 3) {
			setVirus();
			return;
		}
		
		if(idx>=n.size()) return;
		Pos p = n.get(idx);
		map[p.y][p.x] = 1;
		setWall(cnt+1, idx+1);
		map[p.y][p.x] = 0;
		setWall(cnt, idx+1);
	}
	
	public static void setVirus() {
		visit = new boolean[N][M];
		Queue<Pos> q = new LinkedList<>();
		
		for (int i = 0; i < v.size(); i++) {
			q.offer(v.get(i));
		}
		
		while(!q.isEmpty()) {
			Pos pp = q.poll();
			visit[pp.y][pp.x] = true;
			for (int d = 0; d < 4; d++) {
				int ny = pp.y + dy[d];
				int nx = pp.x + dx[d];
				if(ny>=0 && ny<N && nx>=0 && nx<M 
						&& map[ny][nx] == 0 && !visit[ny][nx]) {
					visit[ny][nx] = true;
					q.offer(new Pos(ny, nx));
				}
			}
		}
		find();
		
	}
	
	public static void find() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 && !visit[i][j]) {
					cnt++;
				}
			}
		}
		
		max = Math.max(max, cnt);
		
	}
}
class Pos {
	int y;
	int x;
	
	public Pos(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
