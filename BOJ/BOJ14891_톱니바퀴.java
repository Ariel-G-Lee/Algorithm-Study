package self;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14891_톱니바퀴 {
	static int K, num, dir;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N극 0 S극 1
		list = new ArrayList[4];
		
		for (int i = 0; i < 4; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				list[i].add(s.charAt(j)-'0');
			}
		}

		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			int[] chk = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken()) - 1;
			// 1 시계방향 -1 반시계방향
			dir = Integer.parseInt(st.nextToken());
		
			// 시작 
			int tmpd = dir; // 방향
			int tmps = list[num].get(2);
			
			chk[num] = dir;

			// 기준 번호 톱니에서 뒤로 가면서
			for (int j = num+1; j < 4; j++) {
				if(tmps != list[j].get(6)) {
					// 돌아야하는 방향 저장(반대방향)
					chk[j] = -tmpd;
					tmpd = -tmpd; // 현재 방향 저장
					tmps = list[j].get(2); // 현자 극 저장
				} else { // 다르지 않다면 회전하지 않음 break
					break;
				}
				
			}
			
			tmpd = dir; // 방향
			tmps = list[num].get(6);
			// 기준번호에서 앞쪽으로 가면서 체크!
			for (int j = num-1; j >= 0; j--) {
				if(tmps != list[j].get(2)) {
					// 돌아야하는 방향 저장(반대방향)
					chk[j] = -tmpd;
					tmpd = -tmpd; // 현재 방향 저장
					tmps = list[j].get(6); // 현자 극 저장
				} else { // 다르지 않다면 회전하지 않음 break
					break;
				}
			}
//			
//			System.out.println(Arrays.toString(chk));
			for (int j = 0; j < 4; j++) {
				if(chk[j] == 0) continue;
				if(chk[j] == 1) {
						int tmp = list[j].get(7);
	
					list[j].remove(7);
					list[j].add(0,tmp);
				} else {
					int tmp = list[j].get(0);
					list[j].remove(0);
					list[j].add(tmp);
				}
			}
		}
		
		

		
		int ans = list[0].get(0) == 0? 0 : 1;
		ans += list[1].get(0) == 0? 0 : 2;
		ans += list[2].get(0) == 0? 0 : 4;
		ans += list[3].get(0) == 0? 0 : 8;
		
		System.out.println(ans);
	}
	
	
}
