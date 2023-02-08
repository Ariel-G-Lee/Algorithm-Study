import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class BOJ1302_베스트셀러 {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, Integer> map = new HashMap<>();
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			if(map.containsKey(str)) {
				map.put(str, map.get(str)+1);
			} else {
				map.put(str, 1);
			}
		}
		
		List<Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
		
		list.sort((o1, o2) -> {
			int tmp = o2.getValue() - o1.getValue();
			if (tmp == 0) {
				tmp = o1.getKey().compareTo(o2.getKey());
			}
			return tmp;
		});	
		
		System.out.println(list.get(0).getKey());
	}
}
