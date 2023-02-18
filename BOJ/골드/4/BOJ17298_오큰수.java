import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298_오큰수 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] ans = new int[n];
		
		// 스택에는 인덱스를 입력
		for (int i = 0; i < n; i++) {
			// stak이 비어있지 않고 지금 뽑은 숫자가보다 작다면
			while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
				// 나보다 큰 왼쪽 숫자가 되므로 뽑으면서 지금 뽑은 숫자입력
				ans[stack.pop()] = arr[i];
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			ans[stack.pop()] = -1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
		
	}
}
