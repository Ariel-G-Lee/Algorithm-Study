import java.util.*;
class 둘만의암호 {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        int size = skip.length();
        char[] skipNum = skip.toCharArray();
        
        
        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            char next = cur;
            int cnt = index;
            while(true){
                next += cnt;
                cnt = 0;
                if(next > 'z') {
                    next -= 26;
                    for(int j=0; j<size; j++){
                        if(cur < skipNum[j] || skipNum[j] <= next){
                            cnt++;
                        }
                    } 
                } else {
                    for(int j=0; j<size; j++){
                        if(cur < skipNum[j] && skipNum[j] <= next){
                            cnt++;
                        }
                    } 
                }
                    
                cur = next;
                if(cnt == 0) {
                    break;
                }


            }

            answer.append(next);
        }
        
        return answer.toString();
    }
}