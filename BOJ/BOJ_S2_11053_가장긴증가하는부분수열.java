import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_11053_가장긴증가하는부분수열
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/11053
* 
* [입력사항] 수열 A의 크기 N, 수열 A를 이루는 요소 정보
* [출력사항] 수열 A의 가장 긴 증가하는 부분 수열의 길이
*/

public class BOJ_S2_11053_가장긴증가하는부분수열 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static int[] A, LIS;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        A = new int[N];
        LIS = new int[N];
        
        tokens = new StringTokenizer(input.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
        }        
        
        //알고리즘
        Answer = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            LIS[i] = 1;
            
            for(int j = 0; j < i; j++) {
                if(A[j] < A[i] && LIS[i] < LIS[j]+1) {
                    LIS[i] = LIS[j]+1;
                }                
            }   
            Answer = Math.max(Answer, LIS[i]);
        }
  
        //출력
        System.out.println(Answer);
    }

}
