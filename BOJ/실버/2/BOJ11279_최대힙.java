import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ11279_최대힙 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1); // 우선순위 큐 - 큰수부터 정렬
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine()); // 숫자 입력
			if(num == 0) { // 0일 때
				if(pq.isEmpty()) sb.append(0+"\n"); // pq가 비었으면 0 출력
				else sb.append(pq.poll()+"\n"); // 아니라면 가장 큰 수 출력(맨 앞)
			} else { // 0이 아니라면(자연수 입력)
				pq.offer(num); // pq에 추가
			}
		}
		System.out.println(sb.toString()); // 출력
	}
}
