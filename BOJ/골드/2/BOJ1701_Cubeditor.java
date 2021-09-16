import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1701_Cubeditor {
	static String str;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine();
		
		int len = str.length();
		
		for (int i = 0; i < len; i++) {
			String pattern = str.substring(i,len);
			ans = Math.max(ans, findMaxLen(pattern));
		}
		
		System.out.println(ans);
	}
	private static int findMaxLen(String pattern) {
		int n = pattern.length();
		int maxLen = 0;
		int p[] = new int[n];
		
		for (int i = 1, j = 0; i < n; i++) {
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = p[j-1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)) {
				p[i] = ++j;
				maxLen = Math.max(maxLen, p[i]);
			}
		}
		return maxLen;
	}
}
