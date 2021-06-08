import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1339_단어수학 {
	static int N, ans;
	static int[] alpha;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		alpha = new int[26];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();			// 문자열 하나 입력
			int powNum = (int) Math.pow(10, str.length()-1);		// 곱할 자리수
			for (int j = 0; j < str.length(); j++) {
				alpha[str.charAt(j)-65] += powNum;		// 해당 알파벳의 자리수를 더한다. 100, 10, 1
				powNum /= 10;		// 자리수 감소
			}
			
		}
		
		Arrays.sort(alpha);			// 합한 자리수 정렬
		
		int num = 9;		// 합한 자리수가 큰 알파벳부터 9->8->7-> 순으로 정해서 곱한다.
		for (int i = alpha.length-1; i > 0; i--) {
			if(num == 0 || alpha[i] == 0) break;
			ans += alpha[i]*num;
			num--;		// 곱할 수 감소
		}
		System.out.println(ans);
		
		
	}

}
