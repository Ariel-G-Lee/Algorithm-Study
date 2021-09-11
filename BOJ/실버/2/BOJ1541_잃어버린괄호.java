import java.io.BufferedReader;
import java.io.InputStreamReader;

// 최소가 나오기 위해서는 - 단위로 나눈 후 +로 최대한 묶어서 뺀다!!
public class BOJ1541_잃어버린괄호 {
	static int ans;
	static String input;
	static String[] list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		list = br.readLine().split("\\-");
		
		for (int i = 0; i < list.length; i++) {
			if(i == 0) ans = sum(list[i].split("\\+"));
			else ans -= sum(list[i].split("\\+"));
		}
		
		System.out.println(ans);
		
	}
	private static int sum(String[] split) {
		int tmp = 0;
		for (String n : split) {
			tmp += Integer.parseInt(n);
		}
		return tmp;
	}
}
