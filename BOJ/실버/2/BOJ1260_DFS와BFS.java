package DFSnBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_DFS와BFS {
	static int N, M, V;
	static ArrayList<ArrayList<Integer>> list;
	static StringTokenizer st;
	static boolean[] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		// 정점 개수
		N = Integer.parseInt(st.nextToken());
		// 간선 수
		M = Integer.parseInt(st.nextToken());
		// 탐색 시작 정점 번호
		V = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(list.get(n1).contains(n2)) continue;
			// 간선은 양방향이다.
			list.get(n1).add(n2);
			list.get(n2).add(n1);
		}
		
		// 오름차순으로 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(list.get(i));
		}
		
		sb = new StringBuilder();
		visited = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		visited = new boolean[N+1];
		bfs();
		sb.append("\n");
		
		System.out.println(sb);
		
		
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(V);

		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(!visited[cur]) {
				visited[cur] = true;
				sb.append(cur).append(" ");
			}
			for (int i = 0; i < list.get(cur).size(); i++) {
				if(visited[list.get(cur).get(i)]) continue;
				q.offer(list.get(cur).get(i));
			}
			
		}
		
	}
	private static void dfs(int cur) {

		visited[cur] = true;
		sb.append(cur).append(" ");
		
		for (int i = 0; i < list.get(cur).size(); i++) {
			if(visited[list.get(cur).get(i)]) continue;
			dfs(list.get(cur).get(i));
		}
		
		
	}
}
