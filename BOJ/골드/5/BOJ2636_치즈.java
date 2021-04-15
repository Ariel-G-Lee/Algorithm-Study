import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 공기와 맞닿는다면 치즈의 경계선 부분이다 -> 해당 부분을 공기로 바꿔준다.
// 외부 공기들을 큐에 넣어서 확인(치즈 부분을 안넣으므로 내부 공기를 따로 확인할 필요 없을걸...?)
// 매 시간마다 내부 공기가 외부 공기로 바뀔 수 있으므로 다시 체크
public class BOJ2636_치즈 {
	static int R, C, leftover, time, total;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 전체 치즈 개수 확인
				if (map[i][j] == 1) total++;
			}
		}
		
		
		while(true) {
			// 남은게 0이라면 끝!
			if(total == 0) break;
			// 현재 남은 치즈
			leftover = total;
			// 녹일거니까 시간 추가
			time++;
			// 녹이러 갑시다~
			melting();
		}
		
		System.out.println(time);
		System.out.println(leftover);
		
		
	}
	static class Pos{
		int y;
		int x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static void melting() {
		Queue<Pos> queue = new LinkedList<>();
		visit = new boolean[R][C];
		
		queue.offer(new Pos(0,0));
		visit[0][0] = true;
		
		while(!queue.isEmpty()) {
			
			Pos current = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = current.y+dy[d];
				int nx = current.x+dx[d];
				
				// 범위를 벗어나거나 방문한 곳이면 넘어간다
				if(ny<0 || ny>=R || nx<0 || nx>=C || visit[ny][nx]) continue;
				// 해당 부분이 치즈 경계라면 녹인다. -> 해당부분을 공기로 바꾸고 전체 카운트에서 감소
				if(map[ny][nx] == 1) {
					map[ny][nx] = 0;
					total--;
				} else queue.offer(new Pos(ny, nx)); // 해당 부분이 외부 공기라면 큐에 넣는다
				// 방문처리
				visit[ny][nx] = true;
				
			}
		}
	}

}
