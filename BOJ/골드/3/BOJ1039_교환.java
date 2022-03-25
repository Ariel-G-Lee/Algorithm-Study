import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1039_교환 {
	static int K, N, size, ans;
	static boolean[][] visited;
	static class Info{
		int num;
		int cnt;
		public Info(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		String tmp = st.nextToken();
		N = Integer.parseInt(tmp);
		K = Integer.parseInt(st.nextToken());
		
		size = tmp.length();
		ans = -1;
		
		find();
		
	}
	private static void find() {
		visited = new boolean[1000001][K+1];
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(N,0));
		visited[N][0] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			if(cur.cnt == K) {
				ans = Math.max(ans, cur.num);
				continue;
			}
			
			for (int i = 0; i < size-1; i++) {
				for (int j = i+1; j < size; j++) {
					int nextNum = swap(i,j,cur.num);
					if(nextNum == -1 || visited[nextNum][cur.cnt+1]) continue;
					q.offer(new Info(nextNum, cur.cnt+1));
					visited[nextNum][cur.cnt+1] = true;
				}
			}
		}
		
		System.out.println(ans);
		
		
	}
	private static int swap(int i, int j, int inputNum) {
		String input = inputNum+"";
		int inputSize = input.length();
		int[] arr = new int[inputSize];
		for (int k = 0; k < inputSize; k++) {
			arr[k] = input.charAt(k)-'0';
		}
		if(i==0 && arr[j]==0) return -1;
		
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
		int result = 0;
		int mul = 1;
		for (int k = inputSize-1; k >= 0; k--) {
			result += (arr[k]*mul);
			mul*=10;
		}
		return result;
	}
}
