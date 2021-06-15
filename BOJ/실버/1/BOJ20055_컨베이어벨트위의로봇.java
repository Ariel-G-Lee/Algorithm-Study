import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055_컨베이어벨트위의로봇 {
	static int N, K, size, cnt; // size = N*2, cnt 내구도가 0이 칸 개수
	static int[] dNum; // 컨베이어 벨트 칸의 내구도
	static boolean[] robot; // 칸에 로봇이 존재하는지 여부
	static StringTokenizer st;
	static class CB{
		int dNum; // 내구성
		boolean robot; // 로봇 존재 여부
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		size = 2*N;
		
		dNum = new int[size];
		robot = new boolean[size];
		
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < size; i++) {
			dNum[i] = Integer.parseInt(st.nextToken());
		}
		

		int step = 0;
		while(cnt < K) {
			rotateCB(); // 컨베이어 벨트 회전
			moveRobot(); // 로봇 이동
			putRobot(); // 맨 앞에 새로운 로봇 놓기
			step++; // 단계 증가
		}
		
		System.out.println(step);
		
	}
	
	private static void putRobot() {
		if(!robot[0] && dNum[0] > 0) { // 첫번째에 0이 없고 내구도가 0 이상인 경우만 새로 올린다.
			robot[0] = true;
			dNum[0]--; // 내구도 감소
			if(dNum[0] == 0) cnt++; // 내구도 0이 되면 카운트 증가
		}
		
	}

	private static void moveRobot() {
		for (int i = N-2; i >= 0; i--) {
			if(robot[i]) { // 움직여야할 로봇이 존재할 때
				 // 이동해야할 곳에 로봇이 있거나 내구도가 0 -> 이동 못함
				if(robot[i+1] || dNum[i+1] == 0) continue;
				if(i+1 == N-1) { // 이동할 곳이 N-1 이라면 이동하고 로봇 내려야함
					// 어차피 robot[i+1]은 올렸다 내릴 곳이니까 따로 표기하지 않는다.
					robot[i] = false; // 이동
				} else {
					robot[i+1] = true; // 이동
					robot[i] = false;
				}
				dNum[i+1]--; // 내구도 감소
				if(dNum[i+1] == 0) cnt++; // 내구도 0이 되면 카운트 증가
				
				
			}
		}
		
	}
	
	private static void rotateCB() {
		int tmpD = dNum[size-1];
		boolean tmpR = robot[size-1];
		for (int i = size-2; i >= 0; i--) { // 뒤에서 앞에 것을 당겨온다.
			dNum[i+1] = dNum[i];
			robot[i+1] = robot[i];
		}
		dNum[0] = tmpD;
		robot[0] = tmpR;
		
		robot[N-1] = false; // n번째 있는 로봇은 내린다.
		
	}


}
