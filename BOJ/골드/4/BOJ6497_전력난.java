import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ6497_전력난 {
	static int m, n, total, ans;
	static StringTokenizer st;
	static int[] parents;
	static ArrayList<Edge> list;
	static class Edge{
		int e1;
		int e2;
		int w;
		public Edge(int e1, int e2, int w) {
			super();
			this.e1 = e1;
			this.e2 = e2;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			total = 0;
			ans = 0;
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if(m+n == 0) break;
			
			list = new ArrayList<>();
			
			parents = new int[m];
			
			for (int i = 0; i < m; i++) {
				parents[i] = i;
			}
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new Edge(a, b, c));
				total += c;
				
			}
			
			Collections.sort(list, (o1, o2) -> o1.w - o2.w);
			
			for (Edge cur : list) {
				// 부모가 같아 cycle을 생성한다면 넘어간다.
				if(find(cur.e1) == find(cur.e2)) continue;
				// 아니라면 연결
				union(cur.e1, cur.e2);

				ans += cur.w;
			}

			// 절약할 수 있는 최대 비용
			System.out.println(total - ans);
			
		}


	}
	public static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa != pb) parents[pb] = pa;
	}
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
}
