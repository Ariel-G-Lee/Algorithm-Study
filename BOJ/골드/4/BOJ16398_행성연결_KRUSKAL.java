import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16398_행성연결_KRUSKAL {
	static int N;
	static long ans;
	static int[] parents;
	static ArrayList<Edge> list;

	static class Edge {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());
				if (w == 0)
					continue;
				list.add(new Edge(i, j, w));
			}
		}

		Collections.sort(list, (o1, o2) -> o1.w - o2.w);

		find();

		System.out.println(ans);

	}

	private static void find() {
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			Edge cur = list.get(i);
			if(find(cur.s) != find(cur.e)) {
				union(cur.s, cur.e);
				ans+=cur.w;
				if(++cnt == N-1) return;
			}
		}

	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pb>pa) parents[pb] = pa;
		else parents[pa] = pb;

	}

	private static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
}
