
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// KRUSKAL
public class SWEA1251_하나로 {
	static int N;
	static double E;
	static double[] py, px;
	static int[] parents;
	static ArrayList<Edge> list;
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		double w;
		public Edge(int s, int e, double w) {
			super();
			this.from = s;
			this.to = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("1251_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			py = new double[N+1];
			px = new double[N+1];
			
			parents = new int[N+1];
			
			makeSet();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				py[i] = Double.valueOf(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				px[i] = Double.valueOf(st.nextToken());
			}
			
			E = Double.valueOf(br.readLine());
			
			list = new ArrayList<>();
			for (int i = 1; i < N; i++) {
				for (int j = i+1; j <= N; j++) {
					double d = Math.pow(py[i]-py[j], 2)+Math.pow(px[i]-px[j], 2);
					list.add(new Edge(i,j,d));
				}
			}
			
			Collections.sort(list);
			
			long result = 0;
			int cnt = 0;
			
			for (Edge e : list) {
				if(findSet(e.from)==findSet(e.to)) continue;
				union(e.from, e.to);
				result+=e.w;
				if(++cnt == N-1) break;
			}
		
			System.out.println("#"+t+" "+Math.round(result*E));
		}
		
		
		
	}
	
	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa<pb) parents[pb] = pa;
		else parents[pa] = pb;
	}

}
