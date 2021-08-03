import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ7507_올림픽게임 {
	static int n, m, ans;
	static class Game{
		int d; // 날짜
		int s; // 시작 시간
		int e; // 종료 시간
		public Game(int d, int s, int e) {
			super();
			this.d = d;
			this.s = s;
			this.e = e;
		}
		
	}
	static StringBuilder sb;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= n; t++) {
			
			m = Integer.parseInt(br.readLine());
			
			ArrayList<Game> list = new ArrayList<>();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Game(Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			Collections.sort(list, new Comparator<Game>() {
				@Override
				public int compare(Game o1, Game o2) {
					int tmp = o1.d - o2.d;
					if(tmp == 0) tmp = o1.s - o2.s;
					if(tmp == 0) tmp = o1.e - o2.e;
					return tmp;
				}
			});
			
			ans = 1; // 맨 처음 경기는 본다!
			int idx = 0;
			for (int i = 1; i < m; i++) {
				// 날짜가 같지 않거나
				// 현재 게임의 종료 시간보다 다음 시작 시간이 크다면 idx 이동
				if(list.get(idx).d != list.get(i).d || list.get(idx).e <= list.get(i).s) {
					ans++;
					idx = i;
				} else if(list.get(idx).e > list.get(i).e) { // 끝나는 시간이 더 늦는다면 idx 이동
					idx = i;
				}
			}
			
			sb.append("Scenario #"+t+":").append("\n").append(ans).append("\n\n");
			
			
		}
		System.out.println(sb);
		
	}

}
