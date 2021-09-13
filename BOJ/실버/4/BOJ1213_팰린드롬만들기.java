import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1213_팰린드롬만들기 {
	static int[] letter;
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		letter = new int[26];
		String str = br.readLine();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			letter[str.charAt(i) - 'A']++;
		}
		
		// 홀수인 문자가 몇개 있는지 파악한다.
		char center = 0;
		int cnt = 0;
		for (int i = 0; i < 26; i++) {
			if(letter[i] % 2 == 1) {
				center = (char) (i+'A');
				cnt++;
			}
		}
		
		// 홀수인 문자가 1개 이상이거나, 문자열 길이가 짝수인데 홀수인 문자가 있다면 불가
		if(cnt>1 || (len%2 == 0 && cnt>0)) sb.append("I'm Sorry Hansoo");
		else {
			for (int i = 0; i < 26; i++) {
				// 각 문자열 짝수 개수만큼 붙인다.
				for (int j = 0; j < letter[i]/2; j++) {
					sb.append((char)(i+'A'));
					sb2.insert(0,(char)(i+'A'));
				}
			}
			// 홀수라면 중간 문자를 붙인다.
			if(len%2 == 1) sb.append(center);
			sb.append(sb2);
		}
		
		System.out.println(sb);
	}
}
