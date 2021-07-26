import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715_카드정렬하기 {
	static int N, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->o1-o2); // 오름차순 정렬
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		
		while(pq.size() > 1) { // 1개 이상이면
			int n1 = pq.poll(); // 숫자 2개 빼서
			int n2 = pq.poll();
			
			ans += n1+n2; // 답에 추가
			pq.offer(n1+n2); // q에 넣기
		}
		
		System.out.println(ans);
		
		
	}
}
