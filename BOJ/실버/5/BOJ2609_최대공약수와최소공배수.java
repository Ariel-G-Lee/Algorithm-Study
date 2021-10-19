import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2609_최대공약수와최소공배수 {
	static int n1, n2, gcd, lcm;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n1 = Integer.parseInt(st.nextToken());
		n2 = Integer.parseInt(st.nextToken());
		
		if(n1 >= n2) findGCD(n1, n2);
		else findGCD(n2, n1);
		
		lcm = (n1*n2)/gcd;
		System.out.println(gcd);
		System.out.println(lcm);
		
		
	}
	private static void findGCD(int n1, int n2) {
		while(n2 != 0) {
			int r = n1%n2;
			n1 = n2;
			n2 = r;
		}
		gcd = n1;
	}
}
