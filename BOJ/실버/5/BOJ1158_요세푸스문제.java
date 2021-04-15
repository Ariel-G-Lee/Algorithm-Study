import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {
	static int N, K;
	static Queue<Integer> person = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			person.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		int count = 0;
		while(!person.isEmpty()) {
			count++;
			if(count == K) {
				sb.append(person.poll()+", ");
				count = 0;
			} else {
				person.offer(person.poll());
				
			}
//			for (int i = 1; i < K; i++) {
//				person.offer(person.poll());
//			}
//			sb.append(person.poll()+", ");
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		System.out.println(sb);
	}

}
