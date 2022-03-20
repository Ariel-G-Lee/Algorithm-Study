import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ7682_틱택토 {
	static String str;
	static char[][] map;
	static int cntX, cntO, cntD;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		while(!(str=br.readLine()).equals("end")) {
			
			int idx = 0;
			cntX = cntO = cntD = 0;
			
			map = new char[3][3];
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = str.charAt(idx++);
					if(map[i][j]=='X') cntX++;
					else if(map[i][j] == 'O') cntO++;
				}
			}
			
			if(cntO>cntX || cntX-cntO>1) {
				sb.append("invalid").append("\n");
				continue;
			}
			
			int XBingo = find('X');
			int OBingo = find('O');
			
			if(XBingo>=1 && OBingo>=1) sb.append("invalid").append("\n");
			else if(cntX==cntO+1 && XBingo>=1) sb.append("valid").append("\n");
			else if(cntX==cntO && OBingo>=1) sb.append("valid").append("\n");
			else if(cntX+cntO==9 && cntX==cntO+1 && XBingo+OBingo==0) sb.append("valid").append("\n");
			else sb.append("invalid").append("\n");
			
		}
		System.out.println(sb);
	}
	private static int find(char c) {
		int cnt = 0;

		if (map[0][0] == c && map[1][0] == c && map[2][0] == c) {
			cnt++;
		}
		if (map[0][1] == c && map[1][1] == c && map[2][1] == c) {
			cnt++;
		}
		if (map[0][2] == c && map[1][2] == c && map[2][2] == c) {
			cnt++;
		}
		
		if (map[0][0] == c && map[0][1] == c && map[0][2] == c) {
			cnt++;
		}
		if (map[1][0] == c && map[1][1] == c && map[1][2] == c) {
			cnt++;
		}
		if (map[2][0] == c && map[2][1] == c && map[2][2] == c) {
			cnt++;
		}
		

		if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {
			cnt++;
		}
		if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {
			cnt++;
		}
		return cnt;
	}
}
