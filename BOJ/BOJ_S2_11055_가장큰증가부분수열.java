import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_11055_가장큰증가부분수열
* @date 2020.10.14
* @link https://www.acmicpc.net/problem/11055
* 
* [입력사항] 수열 A의 크기 N / 수열 A를 이루고 있는 요소
* [출력사항] 수열 A의 합이 가장 큰 증가 부분 수열의 합
*/

public class BOJ_S2_11055_가장큰증가부분수열 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        
        int[] S = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(tokens.nextToken());
        }
        
        Answer = Integer.MIN_VALUE;
        int[] LIS = new int[N];
        for(int i = 0; i < N; i++) {
            LIS[i] = S[i];
            for(int j = 0; j < i; j++) {
                if((S[j] < S[i]) && (LIS[i] < LIS[j]+S[i])) {
                    LIS[i] = LIS[j] + S[i];
                }              
            }
            Answer = Math.max(Answer, LIS[i]);
        }
        System.out.println(Answer);
    }
}
