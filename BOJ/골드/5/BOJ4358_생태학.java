import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ4358_생태학 {
	static HashMap<String, Integer> list;
	static int total;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		list = new HashMap<>();
	
		String str = "";
		while(true) {
			str = br.readLine();
			if(str == null || str.equals("")) break;
			// getOrDefault(Object key, V DefaultValue)
			// defaultValue : 지정된 키로 매핑된 값이 없는 경우 반환되어야 하는 기본값
			list.put(str, list.getOrDefault(str, 0) + 1);
			total++;
		}
		
		Object[] keyList = list.keySet().toArray();
		Arrays.sort(keyList);
		
		for (Object key : keyList) {
			sb.append((String) key).append(" ");
			sb.append(String.format("%.4f", list.get((String) key) * 100.0f / total));
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
