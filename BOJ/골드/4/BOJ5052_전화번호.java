import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ5052_전화번호 {
	static int T, N;
	static String text;
	static String[] list;
	static Set<String> set; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while (T --> 0) {
			N = Integer.parseInt(br.readLine());
			
			list = new String[N];
			
			set = new HashSet<>();
			
			for (int i = 0; i < N; i++) {
				list[i] = br.readLine();
				set.add(list[i]);
			}
			Arrays.sort(list);
			
			//if(find()) sb.append("YES\n");
			if(findByMap()) sb.append("YES\n");
			else sb.append("NO\n");
			
		}
		
		System.out.println(sb);
		
	}
	private static boolean findByMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < list[i].length(); j++) {
				if(set.contains(list[i].substring(0, j))) return false;
			}
		}
		return true;
	}
	private static boolean find() {
		// 정렬되어 있는 상태에서는 바로 뒤에 접두어가 있게된다.
		for (int i = 0; i < N-1; i++) {
			if(list[i+1].startsWith(list[i])) return false;
		}
		return true;
		
	}
}
