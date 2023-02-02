import java.util.*;
class 크기가작은부분문자열 {
    public int solution(String t, String p) {
        int answer = 0;
        int tLen = t.length();
        int pLen = p.length();
        
        
        for(int i=0; i<=tLen-pLen; i++){
            String s = t.substring(i, i+pLen);
            if(s.compareTo(p) <= 0){
                answer++;
            }
        }
        return answer;
    }
}