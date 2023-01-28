import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941_소문난칠공주 {
	static char[][] map;
	static int ans;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0 ,-1};
	static int[] py, px;
	static int[] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		
		py = new int[25];
		px = new int[25];
		
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		
		for (int i = 0; i < 25; i++) {
			py[i] = i/5;
			px[i] = i%5;
		}
		
		selected = new int[7];
		Arrays.fill(selected, -1);
		// 조합으로 7개를 뽑아서 연결이 되는지 확
		find(0, 0, 0, 0);
		
		
		System.out.println(ans);
	}
	private static void find(int sCnt, int yCnt, int cnt, int srcIdx) {
		if(cnt == 7) {
			checkLink();
			return;
		}
		
		if(srcIdx == 25) return;
		
		selected[cnt] = srcIdx; 
		if(map[py[srcIdx]][px[srcIdx]] == 'S') {
			find(sCnt+1, yCnt, cnt+1, srcIdx+1);
		} else {
			if(yCnt < 3) find(sCnt, yCnt+1, cnt+1, srcIdx+1);
		}
		
		
		find(sCnt, yCnt, cnt, srcIdx+1);
				
		
	}
	private static void checkLink() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[7];
		q.offer(selected[0]);
		visited[0] = true;
		
		int cnt = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = py[cur] + dy[d];
				int nx = px[cur] + dx[d];
				if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
				for (int i = 1; i < 7; i++) {
					if(visited[i]) continue;
					if(ny == py[selected[i]] && nx == px[selected[i]]) {
						visited[i] = true;
						q.offer(selected[i]);
						cnt++;
					}
				}
			}
		}
		
		if(cnt == 7) ans++;
		
	}

}
