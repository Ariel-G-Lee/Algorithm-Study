import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
	static int[] p;
	static boolean[] tgt;
	static int[][] map;
	static int N, min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		p = new int[N+1];
		tgt = new boolean[N+1]; // a 선거구 뽑기
		map = new int[N+1][];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			map[i] = new int[c];
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;

		for (int i = 0; i < args.length; i++) {
			
		}
		find(1,0);
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
	}
	
	public static void find(int srcIdx, int cnt) { // cnt는 구역이 나눠지지 않을 경우를 위해서 계산
		if(srcIdx == N+1) {
			if(cnt == N || cnt == 0) return; // 구역이 나눠지지 않는 경우
			if(check()) {
				int sum = 0;
				for (int i = 1; i < p.length; i++) {
					if(tgt[i]) sum+=p[i];
					else sum-=p[i];
				}
				min = Math.min(Math.abs(sum), min);
			}
			
			return;
		}
		
		// 선택하고
		tgt[srcIdx] = true;
		find(srcIdx+1, cnt+1); 
		// 선택하지 않고
		tgt[srcIdx] = false;
		find(srcIdx+1, cnt);
		
	}
	
	public static boolean check() {
		boolean[] ck = new boolean[N+1];
		
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if(tgt[i]) {
				queue.offer(i);
				ck[i] = true;
				break;
			}
		}
		
		int idx = 0;
		while(!queue.isEmpty()) {
			idx = queue.poll();
			
			if(tgt[idx]) {
				int s = map[idx].length;
				for (int i = 0; i < s; i++) {
					int next = map[idx][i];
					if(ck[next]) continue;
					if(tgt[next]) {
						queue.offer(next);
						ck[next] = true;
					}
				}
			}
		}
		
		
		for (int i = 1; i <= N; i++) {
			if(!tgt[i]) {
				queue.offer(i);
				ck[i] = true;
				break;
			}
		}
		
		idx = 0;
		while(!queue.isEmpty()) {
			idx = queue.poll();
			
			if(!tgt[idx]) {
				int s = map[idx].length;
				for (int i = 0; i < s; i++) {
					int next = map[idx][i];
					if(ck[next]) continue;
					if(!tgt[next]) {
						queue.offer(next);
						ck[next] = true;
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if(!ck[i]) return false;
		}
		return true;
	}
	
}
