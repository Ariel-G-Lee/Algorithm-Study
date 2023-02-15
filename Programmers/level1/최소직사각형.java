import java.util.*;
class 최소직사각형{
    public int solution(int[][] sizes) {
        int answer = 0;
        Queue<Integer> X = new PriorityQueue<>((o1, o2)->o2-o1);
        Queue<Integer> Y = new PriorityQueue<>((o1, o2)->o2-o1);
        for(int i=0; i<sizes.length; i++){
            X.offer(Math.max(sizes[i][0], sizes[i][1]));
            Y.offer(Math.min(sizes[i][0], sizes[i][1]));
        }
        
        answer = X.poll()*Y.poll();
        return answer;
    }
}