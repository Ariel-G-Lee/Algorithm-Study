import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 프린터 {
	public static void main(String[] args) {
		int[] arr = {1, 1, 9, 1, 1, 1};
		System.out.println(solution(arr, 0));
	}
	
	
	public static int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
        Queue<int[]> q = new LinkedList<>(); // 위치 정보와 우선순위가 같이 들어감.
        
        for (int i = 0; i < priorities.length; i++) {
			pq.offer(priorities[i]);
			q.offer(new int[]{i, priorities[i]});
		}
        
        answer = find(pq, q, location);
        
        return answer;
    }
	
	public static int find(PriorityQueue<Integer> pq, Queue<int[]> q, int location) {
		int idx = 0;
		while(!pq.isEmpty()) {
        	int max = pq.poll();
        	while(true) {
        		int[] tmp = q.poll();
        		if(tmp[1] == max) {
        			idx++;
        			if(tmp[0] == location) {
        				return idx;
        			}
        			break;
        		} else {
        			q.offer(tmp);
        		}
        	}
        }
		return -1;
	}
}
