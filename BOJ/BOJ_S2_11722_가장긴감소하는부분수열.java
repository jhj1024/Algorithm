import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_11722_가장긴감소하는부분수열
* @date 2020.10.22
* @link https://www.acmicpc.net/problem/11722
* 
* [입력사항] 수열 A의 크기 N / N개의 수열 A의 정보
* [출력사항] 가장 긴 감소하는 부분 수열의 길이
*/

public class BOJ_S2_11722_가장긴감소하는부분수열 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        
        int[] S = new int[N];
        tokens = new StringTokenizer(input.readLine());
        for(int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(tokens.nextToken());
        }
        
        //알고리즘
        int[] LDS = new int[N];
        for(int i = 0; i < N; i++) {
            LDS[i] = 1;
            for(int j = 0; j < i; j++) {
                if(S[j] > S[i] && LDS[i] <= LDS[j]+1) {
                    LDS[i] = LDS[j]+1;
                }
            }
            Answer = Math.max(Answer, LDS[i]);
        }

        //출력
        System.out.println(Answer);       
    }

}
