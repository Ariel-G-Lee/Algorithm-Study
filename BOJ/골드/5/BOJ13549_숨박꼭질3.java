import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549_숨박꼭질3 {
	static int N, K, ans;
	static boolean[] visit;

	static class Info {
		int x; // 위치
		int cnt; // 이동 시간

		public Info(int x, int cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		K = Integer.parseInt(st.nextToken()); // 동생의 위치

		find();

		System.out.println(ans);
	}

	private static void find() {
		visit = new boolean[100001]; // 방문체크 배열

		PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() { // 이동시간 순으로 정렬
			@Override
			public int compare(Info o1, Info o2) {
				return o1.cnt - o2.cnt;
			}
		});

		pq.offer(new Info(N, 0)); // 수빈이의 시작 위치 넣기

		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			visit[cur.x] = true;

			if (cur.x == K) { // bfs 탐색 -> 동생을 찾았을 경우 break;
				ans = cur.cnt;
				break;
			}

			// 범위 안에 있는지, 방문한 곳인지 체크
			if (cur.x + 1 <= 100000 && !visit[cur.x + 1]) // +1 이동
				pq.offer(new Info(cur.x + 1, cur.cnt + 1)); // 1초 추가
			if (cur.x - 1 >= 0 && !visit[cur.x - 1]) // -1 이동
				pq.offer(new Info(cur.x - 1, cur.cnt + 1)); // 1초 추가
			if (cur.x * 2 <= 100000 && !visit[cur.x * 2]) // 순간이동 x2
				pq.offer(new Info(cur.x * 2, cur.cnt)); // 시간 추가하지 않음

		}

	}

}
