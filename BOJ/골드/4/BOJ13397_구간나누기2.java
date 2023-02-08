import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13397_구간나누기2 {
	static int N, M, maxN;
	static int[] num;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			maxN = Math.max(maxN, num[i]);
		}
		
		find();
		
	}
	private static void find() {
		int left = 0; // 1개 만 존재할 경우
		int right = maxN;
		while(left<right) {
			int mid = (left+right)/2;
			if(devide(mid) > M) { // 구간 수가 M보다 많다면 올릴 수 있음 
				left = mid+1;
			} else { // 구간 수가 M보다 적거나 같다면 낮출 수 있음
				right = mid;
			}
		}
		
		
		// left와 right 가 같아지면 최대값
		System.out.println(right);
	}
	private static int devide(int mid) {
		int cnt = 1;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, num[i]);
			min = Math.min(min, num[i]);
			// 차이가 mid보다 크다면 끊는다.
			if(max-min > mid) {
				cnt++;
				max = Integer.MIN_VALUE;
				min = Integer.MAX_VALUE;
				i--; // 인덱스는 하나 줄여줌(지금 인덱스 값은 같은 배열에 들어갈 수 없음)
			}
		}
		return cnt;
	}
}
