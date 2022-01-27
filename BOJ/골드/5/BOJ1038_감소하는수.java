package part3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 으렵따 으려워
public class BOJ1038_감소하는수 {
	static int N;
	static Long ans;
	static ArrayList<Long> list;
	public static void main(String[] args) throws Exception{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		
		if (N<=10) ans = (long)N;
		// 최대 10자리에서 각 자리에 올 수 있는 수
		// 첫번째 자리 9			- 10C1
		// 두번째 자리 9,8			- 10C2
		// 세번째 자리 9,8,7		- 10C3
		// ...
		// 열번째 자리 9,8,7,..,0	- 10C10
		// 합이 10C1+...+10C10 = 2^10 - 1 = 1023개
		// 0번째 수가 0번째, 9876543210이 1022번째 수가 된다.
		else if (N>1022) ans = (long)-1;
		else {
			// 모든 감소하는 수를 찾는다.
			for (int i = 0; i < 10; i++) {
				find(1, i);
			}
			Collections.sort(list);
			ans = list.get(N);
		}
		
		System.out.println(ans);
		
	}
	private static void find(int idx, long num) {
		if (idx>10) return;
		
		list.add(num);
		
		for (int i = 0; i < 10; i++) {
			// 현재 숫자의 마지막 숫자보다 작을때 돈다. (감소하는 수)
			if(i < num%10) {
				find(idx+1, (num*10)+i);
			}
		}
		
	}
}
