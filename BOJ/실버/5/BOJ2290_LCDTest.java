package Simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2290_LCDTest {
	static int s, width, height;
	static int[] n;
	static String[] pattern;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		String str = st.nextToken();
		int size = str.length();
		n = new int[size];
		
		for (int i = 0; i < size; i++) {
			n[i] = str.charAt(i) - '0';
		}
		
		width = s+2;
		height = 2*s + 3;
		
		pattern = new String[5];
		pattern[0] = pattern0();
		pattern[1] = pattern1();
		pattern[2] = pattern2();
		pattern[3] = pattern3();
		pattern[4] = pattern4();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < size; j++) {
				if(i == 0) {					
					sb.append(p1(n[j]));
				} else if (i>=1 && i<=s) {
					sb.append(p2(n[j]));
				} else if (i == s+1) {
					sb.append(p3(n[j]));
				} else if (i>=s+2 && i<=2*s+1) {
					sb.append(p4(n[j]));
				} else {
					sb.append(p5(n[j]));
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static Object p5(int num) {
		if (num == 1 || num == 4 || num == 7) {
			return pattern[0];
		} else {
			return pattern[1];
		}
	}
	private static Object p4(int num) {
		if (num == 2) {
			return pattern[3];
		} else if (num == 6 || num == 8 || num == 0) {
			return pattern[4];
		} else {
			return pattern[2];
		}
	}
	private static Object p3(int num) {
		if(num == 1 || num == 7 || num == 0) {
			return pattern[0];
		} else {
			return pattern[1];
		}
	}
	private static Object p2(int num) {
		if(num == 1 || num == 2 || num == 3 || num==7) {
			return pattern[2];
		} else if (num == 5 || num == 6) {
			return pattern[3];
		} else {
			return pattern[4];
		}
	}
	private static String p1(int num) {
		if(num == 1 || num == 4) {
			return pattern[0];
		} else {
			return pattern[1];
		}
	}
	private static String pattern4() { // |__|
		StringBuilder tmp = new StringBuilder();
		tmp.append("|");
		int n = 1;
		while(true) {
			if(n==width-1) break;
			tmp.append(" ");
			n++;
		}
		tmp.append("|");
		return tmp.toString();
	}
	private static String pattern3() { // |__
		StringBuilder tmp = new StringBuilder();
		tmp.append("|");
		int n = 1;
		while(true) {
			if(n==width) break;
			tmp.append(" ");
			n++;
		}
		return tmp.toString();
	}
	private static String pattern2() { // __|
		StringBuilder tmp = new StringBuilder();
		int n = 0;
		while(true) {
			if(n==width-1) break;
			tmp.append(" ");
			n++;
		}
		tmp.append("|");
		return tmp.toString();
	}
	private static String pattern1() { //  _--_ 
		StringBuilder tmp = new StringBuilder();
		tmp.append(" ");
		int n = 1;
		while(true) {
			if(n==width-1) break;
			tmp.append("-");
			n++;
		}
		tmp.append(" ");
		return tmp.toString();
	}
	private static String pattern0() { // ____
		StringBuilder tmp = new StringBuilder();
		int n = 0;
		while(true) {
			if(n==width) break;
			tmp.append(" ");
			n++;
		}
		return tmp.toString();
	}
}
