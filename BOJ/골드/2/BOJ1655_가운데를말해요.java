package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// minHeap, maxHeap
public class BOJ1655_가운데를말해요 {
	static int N;
	static PriorityQueue<Integer> minHeap, maxHeap;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 최댓값이 가장 앞에
		minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); // 최솟값이 가장 앞에

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			// 중간값을 찾아야하므로 번갈아가면서 한번씩 넣어준다!
//			if(maxHeap.size() == minHeap.size()) maxHeap.offer(n);
//			else minHeap.offer(n);
			if(i%2 == 0) { 
				maxHeap.add(n);
			} else { 
				minHeap.add(n);
			}
			
			// 양쪽이 비어있지 않을 경우(한쪽이라도 비어있으면 정렬 무쓸모)
			// 값이 들어갔을 때 양쪽의 대소관계를 비교해서 swap
			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				// maxHeap의 root가 minHeap의 root 보다 크면 swap
				if(maxHeap.peek() > minHeap.peek()){
					int tmp = maxHeap.poll();
					int tmp2 = minHeap.poll();
					minHeap.offer(tmp);
					maxHeap.offer(tmp2);
				}
				
			}
			
			// 중간값 중에 작은 값을 알면 되니까 maxHeap에서 찾아준다!
			sb.append(maxHeap.peek()+"\n");
		}
		System.out.println(sb.toString());
	}
}
