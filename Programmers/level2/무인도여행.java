import java.util.*;
class 무인도여행 {
    static ArrayList<Integer> list;
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static class Pos{
        int y;
        int x;
        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        visited = new boolean[N][M];
        list = new ArrayList<>();
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = maps[i].charAt(j);
            }
        }
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && map[i][j] != 'X'){
                    find(i, j);
                }
            }
        }
        int[] answer = null;
        if(list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[list.size()];
            Collections.sort(list);
        
            for(int i=0; i<list.size(); i++){
                answer[i] = list.get(i);
            }
        }
        
        return answer;
    }
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    public void find(int y, int x){
        int sum = 0;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(y, x));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            sum += map[cur.y][cur.x] - '0';
            
            for(int d=0; d<4; d++){
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];
                if(ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx]) continue;
                if(map[ny][nx] == 'X') continue;
                q.offer(new Pos(ny, nx));
                visited[ny][nx] = true;
            }
        }
        
        if(sum != 0) list.add(sum);
    }
}