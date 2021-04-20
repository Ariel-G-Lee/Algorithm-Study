package self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2930_힙 {

	static int T, N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			StringBuilder sb = new StringBuilder("#"+t);
			
			PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2-o1);
			
			N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int op = Integer.parseInt(st.nextToken());
				if(op==1) {
					q.offer(Integer.parseInt(st.nextToken()));
				} else {
					if(q.isEmpty()) {
						sb.append(" -1");
						continue;
					}
					sb.append(" "+q.poll()); // 가장 위에 있는것이 최대 
				}
				
			}
			System.out.println(sb.toString());
		}
		
	}
	 
	 
	 
}
