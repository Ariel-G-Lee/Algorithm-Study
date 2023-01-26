import java.util.*;
class 귤고르기{
    static HashMap<Integer, Integer> map;
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        map = new HashMap<>();
        for(int i=0; i<tangerine.length; i++){
            if(map.containsKey(tangerine[i])){
                map.put(tangerine[i], map.get(tangerine[i])+1);
            } else {
                map.put(tangerine[i], 1);
            }
        }
        
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));
        
        for(Integer key : keys){
            answer++;
            k-=map.get(key);
            if(k<=0) break;
        }
        return answer;
    }
}