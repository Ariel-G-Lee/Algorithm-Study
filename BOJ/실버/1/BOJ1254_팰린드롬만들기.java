import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1254_팰린드롬만들기 {
	static String inputStr;
	static int ans, len, subLen;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		inputStr = br.readLine();
		
		len = inputStr.length();
		
		find();
		
		System.out.println(ans);
	}
	private static void find() {
		
		// 맨 뒤에 붙인다고 했으니까
		// 앞에서부터 하나씩 자르면서 팰린드롬 문자열이 있는지 확인
		for (int i = 0; i < len; i++) {
			if(isPalindrome(inputStr.substring(i))) {
				// 잘라서 팰린드롬 문자열이 있으면
				// 전체 문자열에 i까지 이동한 문자열을 반대로 붙이면 전체가 팰린드롬이 된다.
				ans = i+len;
				return;
			}
		}
	}
	
	// 맨 앞과 맨 뒤에서 하나씩 양쪽으로 이동하면서 일치하는지 확인
	private static boolean isPalindrome(String subStr) {
		subLen = subStr.length();
		for (int i = 0; i < subLen/2; i++) {
			if(subStr.charAt(i) != subStr.charAt(subLen-1-i)) {
				return false;
			}
		}
		return true;
	}
}
