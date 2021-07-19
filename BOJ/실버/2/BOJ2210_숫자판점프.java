import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ2210_숫자판점프 {
	static int[][] map;
	static HashSet<String> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[5][5]; // 5x5 배열 생성
		list = new HashSet<>(); // 중복된 문자열은 저장X
		
		for (int i = 0; i < 5; i++) { // 맵 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String str = ""; // 6개 숫자를 문자열로 만든다.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				find(i, j, 0, str); // dfs로 찾아가기
			}
		}
		
		System.out.println(list.size());
		
	}
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	private static void find(int y, int x, int cnt, String str) {
		if(cnt == 6) { // 6개를 찾았다면 
			list.add(str); // HashSet에 넣는다.
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny<0 || ny>= 5 || nx<0 || nx>=5) continue; // 범위 밖을 벗어나면 다음
			find(ny, nx, cnt+1, str+map[y][x]); // 다음 단계로 찾아가기
		}
		
	}
}
