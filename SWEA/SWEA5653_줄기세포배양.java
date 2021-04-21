package self;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA5653_줄기세포배양 {
    static int T, N, M, K;
    static int[][] map;
    static boolean[][] visit;
     
    static class Cell{
        int y;
        int x;
        int life;
        int act; // 활성화되는 시간
        int die; // 죽는 시간
        public Cell(int y, int x, int life, int act, int die) {
            super();
            this.y = y;
            this.x = x;
            this.life = life;
            this.act = act;
            this.die = die;
        }
 
    }
     
    static Queue<Cell> q; // 모든 세포를 포함한 queue
    static PriorityQueue<Cell> pq; // 퍼뜨리기 위한 queue
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
             
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken()); // 세로크기
            M = Integer.parseInt(st.nextToken()); // 가로크기
            K = Integer.parseInt(st.nextToken()); // 시간
             
            map = new int[N+K][M+K];
            visit = new boolean[N+K][M+K];
             
            q = new LinkedList<>();
            pq = new PriorityQueue<>((o1, o2) -> o2.life - o1.life);
             
            for (int i = K/2; i < K/2+N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = K/2; j < K/2+M; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp != 0) {
                        map[i][j] = tmp;
                        q.offer(new Cell(i,j, tmp, tmp, tmp*2));
                        visit[i][j] = true;
                    }
                }
            }
             
            for (int k = 1; k <= K; k++) {
                check(k);
                spread(k);
                 
            }
             
            System.out.println("#"+t+" "+q.size());
        }
         
         
    }
     
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
 
 
    private static void spread(int t) {
        while(!pq.isEmpty()) {
            Cell c = pq.poll();
             
            if(t < c.die) q.add(c); // 죽은 애들은 q에 넣는다
             
            for (int d = 0; d < 4; d++) {
                int ny = c.y + dy[d];
                int nx = c.x + dx[d];
                 
                if(visit[ny][nx]) continue;
                visit[ny][nx] = true;
                map[ny][nx] = c.life;
                q.offer(new Cell(ny, nx, c.life, t+c.life, t+c.life*2));
                 
            }
             
        }
         
    }
 
     
    private static void check(int t) {
        int size = q.size();
         
        for (int i = 0; i < size; i++) {
            Cell c = q.poll();
             
            if(c.act >= t) q.add(c); // 활성화 되기 전
            else if(c.act+1 == t) pq.add(c); // 활성화
            else if(c.act < t && c.die > t) q.add(c); // 활성화 되서 죽기 전
        }
         
    }
     
}