

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA1218 {
	static int N;
	static boolean check;
	static Stack<Character> stack = new Stack<>();
	static char[] a = {'(','[','{','<'};
	static char[] b = {')',']','}','>'};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("1218_input.txt"));
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= T; t++) {
			stack.clear();
			
			N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			int sLength = s.length();
			
			for (int i = 0; i < sLength; i++) {
				check = true;
				for (int j = 0; j < 4; j++) {
					if (s.charAt(i) == a[j]) {
						stack.push(s.charAt(i));
						break;
					}
					else if (s.charAt(i) == b[j]) {
						if(!stack.isEmpty() && stack.peek() == a[j]) {
							stack.pop();
							break;
						}
						else {
							check = false;
							break;
						}
					}
				}
				if(check==false) break;

			}
			int result = stack.isEmpty()? 1 : 0;
			System.out.println("#"+t+" "+result);

		}
	}
}
