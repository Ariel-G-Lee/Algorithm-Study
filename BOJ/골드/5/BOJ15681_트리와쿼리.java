import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15681_트리와쿼리 {
	static int N, R, Q;
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> list, tree;
	static int[] parent, sub;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<>();
		tree = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N+1; i++) {
			list.add(new ArrayList<>());
			tree.add(new ArrayList<>());
		}
		
		parent = new int[N+1];
		sub = new int[N+1];
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			list.get(n1).add(n2);
			list.get(n2).add(n1);
		}
		
		makeTree(R, -1);
		findSubTree(R);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int U = Integer.parseInt(br.readLine());
			sb.append(sub[U]).append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static void findSubTree(int cur) {
		// 해당 정점도 서브트리에 속한 정점이다.
		sub[cur] = 1;
		
		// 연결된 애들을 밑에서부터 찾아올라오기
		for (int n : tree.get(cur)) {
			findSubTree(n);
			sub[cur] += sub[n];
		}
		
	}
	private static void makeTree(int cur, int p) {
		for (int n : list.get(cur)) {
			// 연결된 n이 부모 노드가 아니라면
			if(n != p) {
				// 자식 트리에 넣고
				tree.get(cur).add(n);
				// 그 노의 부모는 현재 노드가 된다.
				parent[n] = cur;
				makeTree(n, cur);
				
			}
		}
		
	}
}
