package self;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4041_활주로건설 {
   
   static int N, X, ans;
   static int[][] map;
   
   public static void main(String[] args) throws NumberFormatException, IOException {
//      System.setIn(new FileInputStream("4041_input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int T = Integer.parseInt(br.readLine());
      
      for (int t = 1; t <= T; t++) {
         ans = 0;
         StringTokenizer st = new StringTokenizer(br.readLine());
         N = Integer.parseInt(st.nextToken());
         X = Integer.parseInt(st.nextToken());
         
         map = new int[N][N];
         
         for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         // 가로 줄 검사
         for (int i = 0; i < N; i++) {
            ans += check(map[i]);
         }

         
         // 세로 줄 검사
         for (int i = 0; i < N; i++) {
            int[] tmp = new int[N];
            for (int j = 0; j < N; j++) {
               tmp[j] = map[j][i];
            }
            ans += check(tmp);
         }
         
         
         System.out.println("#"+t+" "+ans);
         
      }
   }

   private static int check(int[] row) {
      // 이미 경사로를 만들기 위해서 사용한 지점인지를 체크하기 위한 배열
      boolean[] select = new boolean[N];
      
      for (int i = 0; i < N-1; i++) {
         // 높이가 바뀌고
         if(row[i] != row[i+1]) {

            // 현재 높이보다 작아진다면
            if(row[i] > row[i+1]) {
               if(i+X >= N) return 0;
               // 작아지는 부분부터 +x만큼 활주로를 만들 수 있는지 확인한다.
               for (int j = i+1; j <= i+X; j++) {
                  // 이전 높이-1 과 같다면 사용 표시
                  if(row[j] == row[i]-1) select[j] = true;
                  // 아니라면 해당 행은 활주로 만들 수 없음 return 0
                  else return 0;
               }
            } else { // 현재 높이보다 높다면 (뒤를 봐야함!)
               // 현재 높이에서 x만큼 뺀 부분이 범위를 벗어난다면 활주로 만들 수 없음
               if(i+1-X < 0) return 0;
               
               for (int j = i; j > i-X; j--) {
                  if(row[j] == row[i+1]-1 && !select[j]) select[j] = true;
                  else return 0;
               }
               
            }
         }
      }
      
      return 1;
      
   }
}