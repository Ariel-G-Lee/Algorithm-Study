import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class SWEA1244 {
    static int T, len, chance, ans;
    static String str;
    static String[] num;
    public static void main(String[] args) throws Exception{
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        T = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
         
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            num = st.nextToken().split("");
             
            len = num.length;
             
            chance = Integer.parseInt(st.nextToken());
            if(chance > len) chance = len;
             
            ans = 0;
             
            find(0, 0);
             
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
         
        System.out.println(sb.toString());
    }
     
    private static void find(int idx, int cnt) {
 
        if(cnt == chance) {
            // find max num
            str = "";
            Arrays.stream(num).forEach(x -> str += String.valueOf(x));
            ans = Math.max(ans, Integer.parseInt(str));
            return;
        }
         
        for (int i = idx; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                String tmp = num[i];
                num[i] = num[j];
                num[j] = tmp;
                find(idx, cnt+1);
                tmp = num[i];
                num[i] = num[j];
                num[j] = tmp;
            }
        }
    }
}