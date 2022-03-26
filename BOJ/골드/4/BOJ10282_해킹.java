import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10282_해킹 {
	static int T, n, d, c, cnt, time;
	static StringTokenizer st;
	static boolean[] visited;
	static int[] times;
	static ArrayList<ArrayList<Info>> list;
	static class Info{
		int e;
		int t;
		public Info(int e, int t) {
			super();
			this.e = e;
			this.t = t;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				list.add(new ArrayList<>());
			}
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				list.get(b).add(new Info(a, w));
			}
			
			time = 0;
			cnt = 0;
			times = new int[n+1];
			Arrays.fill(times, Integer.MAX_VALUE);
			
			find();
			
			sb.append(cnt).append(" ").append(time).append("\n");
			
		}
		System.out.println(sb);
	}
	private static void find() {
		visited = new boolean[n+1];
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(c, 0));
		times[c] = 0;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			for (Info info : list.get(cur.e)) {
				if(times[info.e]>times[cur.e]+info.t) {
					q.offer(new Info(info.e, info.t));
					times[info.e] = times[cur.e]+info.t;
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if(times[i] == Integer.MAX_VALUE) continue;
			else {
				time = Math.max(time, times[i]);
				cnt++;
			}
		}
		
	}
}
