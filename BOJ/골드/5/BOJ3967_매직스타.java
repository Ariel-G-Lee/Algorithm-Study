import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ3967_매직스타 {
	static char[][] map;
	static boolean[] selected, origin;
	static int[] input;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][9];
		
		selected = new boolean[13];
		origin = new boolean[13];
		
		input = new int[13];
		
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == 'x') {
					input[++idx] = 0;
				} else if (map[i][j] >= 'A' && map[i][j] <= 'L') {
					input[++idx] = map[i][j] - 'A' + 1;
					origin[idx] = true;
					selected[map[i][j] - 'A' + 1] = true;
				}
			}
		}
		
		find(1);
		
		
		
		
	}
	private static void find(int index) {
		
		if(index == 6) {
			if((input[2] + input[3] + input[4] + input[5]) != 26) return;
		}
		else if(index == 9) {
			if((input[1] + input[3] + input[6] + input[8]) != 26) return;
		}
		else if(index == 12) {
			if((input[1] + input[4] + input[7] + input[11]) != 26) return;
			if((input[8] + input[9] + input[10] + input[11]) != 26) return;
		}
		else if(index == 13) {
			if((input[2] + input[6] + input[9] + input[12]) != 26) return;
			if((input[5] + input[7] + input[10] + input[12]) != 26) return;
			print();
			System.exit(0);
		}
		
		if(origin[index]) find(index+1);
		
		for (int i = 1; i < 13; i++) {
			if(selected[i] || origin[index]) continue;
			selected[i] = true;
			input[index] = i;
			find(index+1);
			//if(origin[index]) continue;
			input[index] = 0;
			selected[i] = false;
		}
	}
	private static void print() {
		int c = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == '.') {
					sb.append('.');
				} else if (map[i][j] == 'x') {
					sb.append((char)(input[++c]+'A'-1));
				} else {
					sb.append(map[i][j]);
					c++;
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
