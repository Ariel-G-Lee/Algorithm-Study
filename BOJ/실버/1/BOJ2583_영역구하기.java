import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2583 {

	static int M, N, K, count, area;
	static boolean[][] paper;
	static ArrayList<Integer> list;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		paper = new boolean[M][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			
			for (int r = sy; r < ey ; r++) {
				for (int c = sx; c < ex; c++) {
					paper[r][c] = true;
				}
			}
		}
		
//		for (int i = 0; i < M; i++) {
//			System.out.println(Arrays.toString(paper[i]));
//		}
		list = new ArrayList<>();
		count = 0; // 초기화
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				area = 0;
				dfs(i,j);
				if(area != 0) {
					list.add(area);
					count++;
				}
			}
		}
		Collections.sort(list);
		System.out.println(count);
		for(int e : list) {
			System.out.print(e+" ");
		}
	}
	// 좌 상 우 하
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	static void dfs(int y, int x) {
		if(!paper[y][x]) {
			area++; // 빈칸이면 넓이에 추가
			paper[y][x] = true;
			for (int i = 0; i < 4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(ny>=0 && ny<M && nx>=0 && nx<N && !paper[ny][nx]) {
					dfs(ny, nx);
				}
			}
		} else return;
	}
}
