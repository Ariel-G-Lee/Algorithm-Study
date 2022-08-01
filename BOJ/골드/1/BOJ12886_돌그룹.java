import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12886_돌그룹 {
	static int A, B, C, ans;
	static boolean[][] visited;
	static class Info{
		int a;
		int b;
		int c;
		public Info(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[1501][1501];
		find();
		
		System.out.println(ans);
	}
	private static void find() {
		// 전체 돌의 갯수는 변하지 않는다.
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(A, B, C));
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			if(cur.a == cur.b && cur.b == cur.c) {
				ans = 1;
				return;
			}
			if(visited[cur.a][cur.b]) continue;
			visited[cur.a][cur.b] = true;
			
			// 큰거에서 작은 것을 빼고, 작은것은 2배
			if(cur.a != cur.b) {
				if(cur.a>cur.b) {
					q.offer(new Info(cur.a-cur.b, cur.b*2,cur.c));
				} else {
					q.offer(new Info(cur.a*2, cur.b-cur.a,cur.c));
				}
			}
			if(cur.a != cur.c) {
				if(cur.a>cur.c) {
					q.offer(new Info(cur.a-cur.c, cur.b,cur.c*2));
				} else {
					q.offer(new Info(cur.a*2, cur.b,cur.c-cur.a));
				}
			}
			if(cur.b != cur.c) {
				if(cur.b>cur.c) {
					q.offer(new Info(cur.a, cur.b-cur.c,cur.c*2));
				} else {
					q.offer(new Info(cur.a, cur.b*2,cur.c-cur.b));
				}
			}
		}
		
		
	}
}
