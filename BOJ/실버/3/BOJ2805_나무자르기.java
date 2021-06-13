import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805_나무자르기 {
   static int N, M;
   static StringTokenizer st;
   static int[] arr;
   public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      arr = new int[N];
      
      
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
         arr[i] = Integer.parseInt(st.nextToken());
      }
      
      // binary search -> 잘라야하는 위치 찾기
      Arrays.sort(arr);    // binary search를 위해선른 정렬되어있어야함
      int minH = 0;
      int maxH = arr[N-1];
      
      while(minH <= maxH) { // 시작보다 끝이 크거나 같은 동안에만
         int mid = (minH+maxH)/2; // 자르는 위치 선정
         long sum = 0;
         for (int i = 0; i < N; i++) {
            if(arr[i] > mid) sum += arr[i]-mid; // 해당 위치에서 잘랐을 때의 합 계산
         }
         
         if(sum < M) { // 합이 최소 값보다 작다면 잘라야하는 최고 높이를 낮춘다.
            maxH = mid-1;
         } else // 합이 최소 값보다 크다면 잘라야하는 최저 높이를 높인다.
            minH = mid+1; 
      }
      
      System.out.println(maxH);
      
   }
}