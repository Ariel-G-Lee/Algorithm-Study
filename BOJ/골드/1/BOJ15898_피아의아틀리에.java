import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15898_피아의아틀리에 {
	static int N, ans;
	static StringTokenizer st;
	static boolean[] visited;
	static Info[][] map = new Info[5][5];
	static ArrayList<Info[][][]> list = new ArrayList<>();
	static class Info{
		int quality;
		char color;
		public Info(int quality, char color) {
			super();
			this.quality = quality;
			this.color = color;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 초기화
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = new Info(0, 'W'); 
			}
		}
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			list.add(new Info[4][4][4]);
		}
		
		// 입력
		for (int n = 0; n < N; n++) {
			Info[][][] arr = list.get(n);
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					arr[0][i][j] = new Info(Integer.parseInt(st.nextToken()), 'W');
				}
			}
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					arr[0][i][j].color = st.nextToken().charAt(0);
				}
			}
			
			// 각 배열 회전해서 미리 저장해두기
			for (int r = 1; r < 4; r++) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						arr[r][j][3-i]= new Info(arr[r-1][i][j].quality, arr[r-1][i][j].color);
					}
				}
			}
		}
		
		find(map, 0);
		
		System.out.println(ans);
		
	}
	private static void find(Info[][] curMap, int cnt) {
		if(cnt == 3) {
			ans = Math.max(ans, cal(curMap));
			return;
		}

		for (int c = 0; c < N; c++) {
			if(visited[c]) continue;
			visited[c] = true;
			Info[][][] plus = list.get(c);
			for (int i = 0; i <= 1; i++) {
				for (int j = 0; j <= 1; j++) {
					for (int d = 0; d < 4; d++) {
						Info[][] nextMap = updateMap(curMap, i, j, plus, d);
						find(nextMap, cnt+1);
					}
				}
			}
			visited[c] = false;

		}
		


		
	}
	private static int cal(Info[][] curMap) {
		int R = 0, B = 0, G = 0, Y = 0, W = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(curMap[i][j].color == 'R') R += curMap[i][j].quality;
				else if(curMap[i][j].color == 'B') B += curMap[i][j].quality;
				else if(curMap[i][j].color == 'G') G += curMap[i][j].quality;
				else if(curMap[i][j].color == 'Y') Y += curMap[i][j].quality;
			}
		}
		return 7*R + 5*B + 3*G + 2*Y;
	}
	private static Info[][] updateMap(Info[][] curMap, int y, int x, Info[][][] plus,int d) {
		Info[][] newMap = new Info[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				newMap[i][j] = new Info(curMap[i][j].quality, curMap[i][j].color);
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newMap[i+y][j+x].quality += plus[d][i][j].quality;
				if(newMap[i+y][j+x].quality < 0) newMap[i+y][j+x].quality = 0;
				else if (newMap[i+y][j+x].quality > 9) newMap[i+y][j+x].quality = 9;
				
				if(plus[d][i][j].color != 'W') newMap[i+y][j+x].color = plus[d][i][j].color;
			}
		}
		return newMap;
	}
}
