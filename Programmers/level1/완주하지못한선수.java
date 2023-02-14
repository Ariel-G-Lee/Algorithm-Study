import java.util.*;
class 완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashSet<String> set = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<participant.length; i++){
            if(map.containsKey(participant[i])){
                map.put(participant[i], map.get(participant[i])+1);
            } else {
                map.put(participant[i], 1);
                set.add(participant[i]);
            }
        }
        
        for(int i=0; i<completion.length; i++){
            String tmp = completion[i];
            int n = map.get(tmp)-1;
            map.put(tmp, n);
            if(n == 0)
            set.remove(tmp);
        }
        
        List<String> list = new ArrayList<>(set);
        return list.get(0);
    }
}