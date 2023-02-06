import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ3584_가장가까운공통조상 {
	static int T, N, nodeA, nodeB, ans;
	static int[] parent;
	static HashSet<Integer> pSet;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T --> 0) {
			
			N = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			
			// 부모 노드 정보 저장
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				parent[c] = p;
			}
			
			st = new StringTokenizer(br.readLine());
			
			nodeA = Integer.parseInt(st.nextToken());
			nodeB = Integer.parseInt(st.nextToken());
			
			pSet = new HashSet<>();
			
			findParentNode(nodeA);
			
			findSameParentNode(nodeB);
			
			sb.append(ans).append("\n");
			
		}
		
		System.out.println(sb);
		
		
	}
	private static void findSameParentNode(int curNode) {
		if(pSet.contains(curNode)) {
			ans = curNode;
			return;
		}
		
		findSameParentNode(parent[curNode]);
		
	}
	private static void findParentNode(int curNode) {
		
		pSet.add(curNode);
		if(curNode == 0) return;
		
		findParentNode(parent[curNode]);
	}
}
