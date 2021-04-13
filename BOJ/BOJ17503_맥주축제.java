package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ17503_맥주축제 {
	static class Beer{
		int p; // 선호도
		int a; // 도수레벨
		public Beer(int p, int a) {
			super();
			this.p = p;
			this.a = a;
		}
	}
	// 축제가 열리는 기간, 선호도 합, 맥주 종류 수
	static int N, M, K;
	static ArrayList<Beer> beer;
	static PriorityQueue<Beer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		beer = new ArrayList<>();
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			beer.add(new Beer(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(beer, new Comparator<Beer>() {

			@Override
			public int compare(Beer o1, Beer o2) {
				return o1.a - o2.a;
			}
		});


		int liver = find();
		
		System.out.println(liver);
	}

	private static int find() {
		
		list = new PriorityQueue<>(new Comparator<Beer>() {

			@Override
			public int compare(Beer o1, Beer o2) {
				return o1.p - o2.p;
			}
		});
		
		int sum = 0;
		int idx = 0;
		int min = 0;
		while(idx < K) {
			// 선호도 저장
			sum += beer.get(idx).p;
			// 현재 간 레벨 저장
			min = beer.get(idx).a;
			list.offer(new Beer(beer.get(idx).p, beer.get(idx).a));
			if(list.size()>N) {
				Beer tmp = list.poll();
				sum -= tmp.p;
			}
			if(list.size()==N && sum>=M) return min;
			idx++;
		}
		
		return -1;
		
		
	}

	
}
