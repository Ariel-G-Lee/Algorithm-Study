import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16235_나무재테크 {
	static int N, M, K;
	static int[][] curNut, inNut;
	static ArrayList<TreeInfo> trees;
	static Queue<TreeInfo> dieTrees;
	static StringTokenizer st;
	static class TreeInfo{
		int y;
		int x;
		int age;
		public TreeInfo(int y, int x, int age) {
			super();
			this.y = y;
			this.x = x;
			this.age = age;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		curNut = new int[N][N];
		inNut = new int[N][N];
		trees = new ArrayList<>();
		dieTrees = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				curNut[i][j] = 5;
				inNut[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			trees.add(new TreeInfo(y-1, x-1, age));
		}
		
		while(K-->0) {
			// 나이만큼 양분을 먹는다.
			spring();
			// 죽은 나무는 영양분이 된다.
			summmer();
			// 나무 번식
			fall();
			// 영양분 주기
			winter();
		}
		
		System.out.println(trees.size());
	}
	
	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				curNut[i][j] += inNut[i][j];
			}
		}
		
	}

	static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static void fall() {
		int size = trees.size();
		
		for (int i = 0; i < size; i++) {
			TreeInfo cur = trees.get(i);
			if(cur.age%5 == 0) {
				
				for (int d = 0; d < 8; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
					
					trees.add(new TreeInfo(ny, nx, 1));
				}
			}
		}
		
	}
	private static void summmer() {
		while(!dieTrees.isEmpty()) {
			TreeInfo cur = dieTrees.poll();
			curNut[cur.y][cur.x] += (cur.age/2);
		}
		
	}
	private static void spring() {
		ArrayList<TreeInfo> tmp = new ArrayList<>();
		Collections.sort(trees, (o1, o2)-> o1.age - o2.age);
		
		for (TreeInfo cur : trees) {
			int age = cur.age;
			// 영양분을 먹을 수 없다면
			if(age>curNut[cur.y][cur.x]) dieTrees.offer(cur);
			else { // 먹을 수 있다면
				curNut[cur.y][cur.x] -= cur.age; // 나이 만큼 영양분 먹고
				cur.age = cur.age+1; // 나이 1 증가
				tmp.add(cur);
			}
		}
		
		trees = tmp;
		
	}
}
