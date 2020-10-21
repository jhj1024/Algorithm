import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_2491_수열
* @date 2020.10.20
* @link https://www.acmicpc.net/problem/2491
* 
* [입력사항] 수열의 길이 N, N개의 수열 정보
* [출력사항] 연속해서 커지거나 작아지는 (같은 것 포함) 수열 중 가장 긴 길이
*/

public class BOJ_S2_2491_수열 {
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
        int inc = 1, dec = 1;
        
        for(int i = 0; i < N-1; i++) {
            if(S[i] < S[i+1]) { //증가
                Answer = Math.max(Answer, dec);
                dec = 1;
                inc++;                
            }else if(S[i] > S[i+1]) { //감소
                Answer = Math.max(Answer, inc);
                inc = 1;
                dec++;                 
            }else { //같음
                inc++;
                dec++;
            }
        }
        
        Answer = Math.max(Answer, Math.max(inc, dec)); //마지막으로 최대값 업데이트
        
        //출력
        System.out.println(Answer);
    }

}
