import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966_프린터큐 {
	static int T, N, M, ans;
	static StringTokenizer st;
	static class Doc{
		int num;
		int p;
		public Doc(int num, int p) {
			super();
			this.num = num;
			this.p = p;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T --> 0) {
			ans = 0;
			
			Queue<Doc> docs = new LinkedList<>();
			PriorityQueue<Integer> priority = new PriorityQueue<>((o1, o2) -> o2 - o1);
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int p = Integer.parseInt(st.nextToken());
				docs.offer(new Doc(i, p));
				priority.offer(p);
			}
			
			while(!docs.isEmpty()) {
				int max = priority.peek();
				Doc cur = docs.poll();
				
				if(cur.p == max) {
					ans++;
					priority.poll();
					if(cur.num == M) {
						sb.append(ans).append("\n");
						break;
					}
				}
				docs.offer(cur);
			}
		}
		System.out.println(sb);
	}
}
