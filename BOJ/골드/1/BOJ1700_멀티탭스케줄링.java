package part2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1700_멀티탭스케줄링 {
	static int N, K, ans;
	static int[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < K; i++) {
			int n = list[i];
			// 이미 꼽혀있다면 넘어간다.
			if(set.contains(n)) continue;
			// 꼽을 수 있는 자리가 있다면 꼽는다.
			else if(set.size() < N) {
				set.add(n);
				continue;
			}
			List<Integer> willbeUsed = new ArrayList<>();
			// 꼽을 자리가 없다면 빼고 꼽는다.
			for (int j = i+1; j < K; j++) {
				// 현재 꽂혀있는 기기이면서 나중에도 사용되는 기기를 list에 넣는다.
				if(set.contains(list[j]) && !willbeUsed.contains(list[j])) {
					willbeUsed.add(list[j]);
				}
			}
			boolean change = false;
			// 지금 꽂혀진 애들 중에 나중에 사용되는 애가 없다면
			List<Integer> tmp = new ArrayList<>(set);
			for (int j = 0; j < N; j++) {
				if(!willbeUsed.contains(tmp.get(j))) {
					set.remove(tmp.get(j));
					change = true;
					break;
				}
			}
			if(!change) {
				int idx = willbeUsed.size()-1;
				set.remove(willbeUsed.get(idx));
			}
			ans++;
			set.add(list[i]);
			
		}
		System.out.println(ans);
	}
}
