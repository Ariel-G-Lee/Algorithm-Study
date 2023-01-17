import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1374_강의실 {
	static int N, ans;
	static PriorityQueue<Lecture> pq;
	static PriorityQueue<Lecture> lq;
	static StringTokenizer st;
	static class Lecture{
		int stime;
		int etime;
		public Lecture(int stime, int etime) {
			super();
			this.stime = stime;
			this.etime = etime;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		pq = new PriorityQueue<>((o1, o2) -> {
			int tmp = o1.stime - o2.stime;
			if(tmp == 0) tmp = o1.etime - o2.etime;
			return tmp;
		});
		
		lq = new PriorityQueue<>((o1, o2) -> o1.etime - o2.etime);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			pq.offer(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Lecture l = pq.poll();
		lq.offer(l);
		
		while(!pq.isEmpty()) {
			Lecture cur = pq.poll();
			if(lq.peek().etime > cur.stime) {
				lq.offer(cur);
			} else {
				lq.poll();
				lq.offer(cur);
			}
		}
		
		ans = lq.size();
		
		System.out.println(ans);
		
	}
}
