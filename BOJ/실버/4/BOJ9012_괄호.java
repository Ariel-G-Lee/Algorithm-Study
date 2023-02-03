import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012_괄호 {
	static int T;
	static Stack<Character> stack;
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while(T --> 0) {
			check(br.readLine());
			
		}
		
		System.out.println(sb);
	}
	private static void check(String str) {
		boolean flag = true;
		stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if(cur == '(') {
				stack.add(cur);
			} else {
				if(stack.size() == 0) {
					flag = false;
					sb.append("NO").append("\n");
					return;
					
				}
				else stack.pop();
			}
		}
		if(flag && stack.size() == 0) sb.append("YES").append("\n");
		else sb.append("NO").append("\n");
		
	}
	
	
}
