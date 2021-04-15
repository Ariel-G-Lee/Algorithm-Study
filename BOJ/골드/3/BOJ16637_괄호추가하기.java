import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ16637 {
	static ArrayList<Integer> num;
	static ArrayList<Character> op;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		num = new ArrayList<>();
		op = new ArrayList<>();
		
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			// 연산자와 숫자를 나눠서 넣는다
			if(i%2 == 0) num.add(s.charAt(i) - '0');
			else op.add(s.charAt(i));
		}
		
		max = Integer.MIN_VALUE;
		find(0, num.get(0));
		
		System.out.println(max);
	}
	
	public static void find( int current, int sum) {
		
		if(current >= op.size()) {
			max = Math.max(max, sum);
			return;
		}
		
		// 괄호를 안치는 경우
		// 이미 존재하는 합에 연산자 꺼내서 숫자 계산
		int nSum = cal(current, sum, num.get(current+1));
		find(current+1, nSum);
		
		// 괄호를 치는 경우
		if(current+1 < op.size()) {
			// 괄호로 쳐질 숫자 두개를 먼저 연산
			int tmp = cal(current+1, num.get(current+1), num.get(current+2));
			// 위에서 연산한 숫자랑 현재 합을 연산
			int oSum = cal(current, sum, tmp);
			find(current+2, oSum);
		}
		
		
	}
	
	public static int cal(int cur, int n1, int n2) {
		switch(op.get(cur)) {
		case '+':
			return n1+n2;
		case '-':
			return n1-n2;
		case '*':
			return n1*n2;
		}
		return 1;
	}
}
