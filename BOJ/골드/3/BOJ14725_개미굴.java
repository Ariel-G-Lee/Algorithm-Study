import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ14725_개미굴 {
	static int N;
	static StringTokenizer st;
	static StringBuilder sb;
	static class Trie{
		String name; // 이름
		ArrayList<Trie> list; // 자식
		
		public Trie(String name) {
			this.name = name;
			list = new ArrayList<>();
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		Trie tree = new Trie(""); // root
		Trie node;

		while(N --> 0) {
			st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			node = tree;
			
			while(c-->0) {
				String a = st.nextToken();
				int idx = -1;
				
				for (int i = 0; i < node.list.size(); i++) { // 동일한 이름을 갖는 자식 노드가 있는지 찾는다
					if(node.list.get(i).name.equals(a)) {
						idx = i;
						break;
					}
				}
				
				if(idx == -1) { // 해당 레벨에 없는 자식 노드라면
					node.list.add(new Trie(a)); // 노드를 넣고
					node = node.list.get(node.list.size()-1); // 노드 위치 이동
				} else { // 이미 해당 레벨에 있는 자식 노드
					node = node.list.get(idx); // 노드 위치 이동
				}
				
			}
		}
		
		sb = new StringBuilder();
		print(tree, -1);
		System.out.println(sb);
	}
	private static void print(Trie tree, int dept) {
		Collections.sort(tree.list, (o1, o2) -> o1.name.compareTo(o2.name));
		
		if(dept != -1) {
			for (int i = 0; i < dept; i++) {
				sb.append("--");
			}
			sb.append(tree.name).append("\n");
		}
		
		for(Trie n : tree.list) {
			print(n, dept+1);
		}
		
	}
}
