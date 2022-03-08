package Preq;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2023_신기한소수 {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		
		find("", 0);
		
		System.out.println(sb);
		
	}
	private static void find(String str, int cnt) {
		if(cnt == N) {
			sb.append(str+'\n');
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if(isPrime(Integer.parseInt(str+i))) {
				find(str+i, cnt+1);
			}
		}
		
	}
	private static boolean isPrime(int num) {
		if(num == 1) return false;
		// 모든 수를 두 수의 곱으로 표현했을 때
		// 두 수 중 하나는 반드시 제곱근 보다 작거나 같다!
		int tmp = (int) Math.sqrt(num);
		
		for (int i = 2; i <= tmp; i++) {
			if(num%i == 0) return false;
		}
		
		return true;
	}
}
