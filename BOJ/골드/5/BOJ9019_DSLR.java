package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019_DSLR {
	static class tStr{
		int num;
		String op;
		public tStr(int str, String op) {
			super();
			this.num = str;
			this.op = op;
		}
	}
	static int A, B;
	static String ans;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			visit = new boolean[10000];
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			find();
			
			System.out.println(ans);
		}
	}
	private static void find() {
		Queue<tStr> q = new LinkedList<>();
		
		q.offer(new tStr(A,""));
		visit[A] = true;
		
		while(!q.isEmpty()) {
			tStr cur = q.poll();
			if(cur.num==B) {
				ans = cur.op;
				return;
			}

			
			// 각 숫자에 대한 방문처리로 한번 만들어졌던 숫자에 대해서는 다시 하지 않는다!
			
			// D
			int D = (cur.num*2)%10000;
			if(!visit[D]) {
				q.offer(new tStr(D,cur.op+"D"));
				visit[D] = true;;
			}
			// S
			int S = cur.num == 0 ? 9999 : cur.num-1;
			if(!visit[S]) {
				q.offer(new tStr(S,cur.op+"S"));
				visit[S] = true;
			}
			// L
			int L = (cur.num%1000)*10+cur.num/1000;
			if(!visit[L]) {
				q.offer(new tStr(L,cur.op+"L"));
				visit[L] = true;
			}
			// R
			int R = (cur.num%10)*1000+cur.num/10;
			if(!visit[R]) {
				q.offer(new tStr(R,cur.op+"R"));
				visit[R] = true;
			}
		}
		
	}
}
