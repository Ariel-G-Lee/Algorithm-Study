import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ9519_졸려 {
	static int X, leng, c, idx;
	static String word;
	static StringBuilder sb;
	static ArrayList<String> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		X = Integer.parseInt(br.readLine());
		word = br.readLine();
		leng = word.length();
		list = new ArrayList<>(); // 패턴을 찾기 위한 list

		if (leng % 2 != 0) c = 1; // 길이 짝수 홀수 구분하기 위해서

		list.add(word); // 첫번째 단어 넣기
		
		for (int i = 0; i < X; i++) {
			sb = new StringBuilder();

			if (c == 1) { // 길이 홀수
				for (int j = 0; j < leng; j += 2) { // 홀수번째 문자를 앞에다 넣고
					sb.append(word.charAt(j));
				}
				for (int j = leng - 2; j >= 0; j -= 2) { // 짝수번째 문자를 뒤에서부터 넣어준다.
					sb.append(word.charAt(j));
				}
			} else { // 길이 짝수

				for (int j = 0; j < leng; j += 2) {
					sb.append(word.charAt(j));
				}
				for (int j = leng - 1; j >= 0; j -= 2) {
					sb.append(word.charAt(j));
				}
			}

			word = sb.toString();
			if (list.get(0).equals(word)) break; // 첫번째 요소와 같게 된다면 패턴 시작 break
			list.add(word);
		}

		idx = X % list.size(); // 입력 받은 숫자를 패턴 수로 나눠서

		System.out.println(list.get(idx)); // list에 저장한 값 출력

	}
}
