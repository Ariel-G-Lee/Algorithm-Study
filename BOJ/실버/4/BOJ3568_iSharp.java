package Simul;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ3568_iSharp {
	static String standard, tmp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ' ') {
				standard = str.substring(0, i);
				tmp = str.substring(i+1, str.length()-1);
				break;
			}
		}
		
		String[] list = tmp.split(", ");
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.length; i++) {
			sb.append(standard);
			
			StringBuilder tmpSb = new StringBuilder();
			
			tmp = list[i];
			for (int j = tmp.length()-1; j >= 0; j--) {
				char tmpJ = tmp.charAt(j);
				if((tmpJ>='a' && tmpJ<='z') || (tmpJ>='A' && tmpJ<='Z')) {					
					tmpSb.insert(0, tmpJ);
				} else {
					if(tmpJ == ']') {
						sb.append("[]");
						j--;
						continue;
					}
					sb.append(tmpJ);
				}
			}
			sb.append(" ").append(tmpSb.toString());
			sb.append(";").append("\n");
		}
		
		System.out.println(sb);
		
	}

}
