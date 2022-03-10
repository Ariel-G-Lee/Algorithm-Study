import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935_문자열폭발 {
	static String inputStr, boomStr;
	static int inputLen, boomLen;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		inputStr = br.readLine();
		inputLen = inputStr.length();
		boomStr = br.readLine();
		boomLen = boomStr.length();
		
		Stack<Character> stack = new Stack<>(); 
		for (int i = 0; i < inputLen; i++) {
			stack.push(inputStr.charAt(i));
			
			if(boomLen <= stack.size()) {
				boolean flag = true;
				for (int j = 0; j < boomLen; j++) {
					if(stack.get(stack.size()-boomLen+j) != boomStr.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for (int j = 0; j < boomLen; j++) {
						stack.pop();
					}
				}
				
			}
		}
		
		// stack에서 pop하면서 sb에 append를 진행하니 시간 초과 나옴
		StringBuilder sb = new StringBuilder();
		for(char c : stack) {
			sb.append(c);
		}
		
		System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
	}
}
