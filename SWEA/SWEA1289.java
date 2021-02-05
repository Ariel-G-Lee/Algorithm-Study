import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1289 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= N; t++) {
			
			int count = 0;
			
			String s = br.readLine(); // 한줄을 읽어와서
			char[] input = s.toCharArray(); // char 배열로 넣어준다.
			
			char[] tmp = new char[input.length]; // input과 같은 크기의 배열 생성
			
			for (int i = 0; i < tmp.length; i++) tmp[i] = '0'; // '0'으로 초기화

			for (int i = 0; i < input.length; i++) {
				
				if(input[i] != tmp[i]) { // 입력받은 문자와 같지 않다면
					for (int j = i; j < tmp.length; j++) { // 그 index부터 끝까지 해당 문자로 변경
						tmp[j] = input[i];
					}
					
					count++;
				}
			}
			System.out.println("#"+t+" "+count);
		}
	}
}
