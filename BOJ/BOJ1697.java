import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
	static int N, K;
	static int[] visit;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visit = new int[100001];
		if(N==K) System.out.println(0);
		else find(N);
		
	}
	static void find(int n) {
		queue = new LinkedList<>();
		queue.offer(n);
		visit[n] = 1;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			for (int i = 0; i < 3; i++) {
				int next;
				if(i==0) next = num-1;
				else if(i==1) next = num+1;
				else next = num*2;
				
				if(next==K) {
					System.out.println(visit[num]);
					return;
				}
				if(next >= 0 && next < 100001 && visit[next]==0) {
					queue.offer(next);
					visit[next] = visit[num]+1;
				}

			}
		}
	}
}
