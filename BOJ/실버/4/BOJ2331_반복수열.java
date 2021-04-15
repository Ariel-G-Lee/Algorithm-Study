import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2331 {
	static int A, P, result;
	static List<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		list.add(A);

		while(true) {
			int n = list.get(list.size()-1);
			
			int sum = 0;
			while(n!=0) {
				sum+=(int)Math.pow(n%10, P);
				n/=10;
			}
			if(list.contains(sum)) {
				result = list.indexOf(sum);
				System.out.println(result);
				break;
			}
			list.add(sum);
		}
	}

}
