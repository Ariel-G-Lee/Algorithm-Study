import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16916_부분문자열 {
	static int ans;
	static int[] pi;
	static String inputStr, subStr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		inputStr = br.readLine();
		
		subStr = br.readLine();
		
		// 실패함수 pi 만들기
		int n = subStr.length();
		pi = new int[n];
		
		for (int i = 1, j = 0; i < n; i++) {
			while(j>0 && subStr.charAt(i) != subStr.charAt(j)) {
				j = pi[j-1];
			}
			if(subStr.charAt(i) == subStr.charAt(j)) {
				pi[i] = ++j;
			}
		}
		// 비교하기
		int n2 = inputStr.length();
		for (int i = 0, j=0; i < n2; i++) {
			while(j>0 && inputStr.charAt(i) != subStr.charAt(j)) {
				j = pi[j-1];
			}
			if(inputStr.charAt(i) == subStr.charAt(j)) { //두 글자 일치
				if(j == n-1) { // j가 패턴의 마지막 인덱스라면
					// 부분문자열이 포함된다는 것
					ans = 1;
					break;
				}else { 
					j++;
				}
			}
		}
		System.out.println(ans);
	}
}
