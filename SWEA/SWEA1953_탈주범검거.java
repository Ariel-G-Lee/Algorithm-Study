package self;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_탈주범검거 {
   static int  N,M,R,C,L,ans;
   static int[][] map;
   static boolean[][] visit;
   // 상 하 좌 우
   static int[] dy = {-1, 1, 0, 0};
   static int[] dx = {0, 0, -1, 1};
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int T = Integer.parseInt(br.readLine());
      
      
      for (int t = 1; t <= T; t++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());
         R = Integer.parseInt(st.nextToken());
         C = Integer.parseInt(st.nextToken());
         L = Integer.parseInt(st.nextToken());
         
         map = new int[N][M];
         visit = new boolean[N][M];
         ans = 0;
         for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         find();
         
         
         System.out.println("#"+t+" "+ans);
         
      }
   }
   
   static void find() {
      Queue<Pos> q = new LinkedList<>();
      int cnt = 0;
      
      q.offer(new Pos(R,C));
      visit[R][C] = true;
      ans++;      
      
      while(!q.isEmpty()) {
         
         cnt++;
         if(cnt >= L) return;
         int size = q.size();
         
         for (int i = 0; i < size; i++) {
            Pos cur = q.poll();
            
            int type = map[cur.y][cur.x];
            

            for (int d = 0; d < 4; d++) {
               int ny = cur.y+dy[d];
               int nx = cur.x+dx[d];
               
               if(ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx]==0 || visit[ny][nx]) continue;
               
               int next = map[ny][nx];
               
               switch(d) {
               case 0:
                  if(type == 1 || type == 2 || type == 4 || type == 7) {
                     if(next == 1 || next == 2 || next == 5 || next == 6) {
                        q.offer(new Pos(ny, nx));
                        visit[ny][nx] = true;
                        ans++;
                     }
                  }
                  break;
               case 1:
                  if(type == 1 || type == 2 || type == 5 || type == 6) {
                     if(next == 1 || next == 2 || next == 4 || next == 7) {
                        q.offer(new Pos(ny, nx));
                        visit[ny][nx] = true;
                        ans++;
                     }
                  }
                  break;
               case 2:
                  if(type == 1 || type == 3 || type == 6 || type == 7) {
                     if(next == 1 || next == 3 || next == 4 || next == 5) {
                        q.offer(new Pos(ny, nx));
                        visit[ny][nx] = true;
                        ans++;
                     }
                  }
                  break;
               case 3:
                  if(type == 1 || type == 3 || type == 4 || type == 5) {
                     if(next == 1 || next == 3 || next == 6 || next == 7) {
                        q.offer(new Pos(ny, nx));
                        visit[ny][nx] = true;
                        ans++;
                     }
                  }
                  break;
               }
            }
         }
         
         
         
      }
      
   }
   
   static class Pos{
      int y;
      int x;
      public Pos(int y, int x) {
         super();
         this.y = y;
         this.x = x;
      }
      
   }
}