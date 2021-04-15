import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667 {
	static int N, count;
	static int[][] arr;
	static boolean[][] visit;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == 1 && !visit[i][j]) {
					count = 1;
					find(i,j);
					list.add(count);
				}
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for(int n : list) System.out.println(n);
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	static void find(int y, int x) {
		visit[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny>=0 && ny<N && nx>=0 && nx<N && arr[ny][nx]==1 && !visit[ny][nx]) {
				find(ny, nx);
				count++;
			}
		}
		return;
	}

}
